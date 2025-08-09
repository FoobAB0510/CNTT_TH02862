<script setup lang="ts">
import { ref } from 'vue'
import { authService } from '../services/authService'
import { dataService } from '../services/dataService'

const user = authService.user
const isEditing = ref(false)
const loading = ref(false)
const message = ref('')
const messageType = ref<'success' | 'error'>('success')

const form = ref({
  email: user.value?.email || '',
  username: user.value?.username || '',
  password: '',
  confirmPassword: '',
  avatar: user.value?.avatar || '',
})

const initForm = () => {
  if (user.value) {
    form.value = {
      email: user.value.email,
      username: user.value.username,
      password: '',
      confirmPassword: '',
      avatar: user.value.avatar,
    }
  }
}

const toggleEdit = () => {
  if (isEditing.value) {
    initForm()
    isEditing.value = false
    message.value = ''
  } else {
    isEditing.value = true
    message.value = ''
  }
}

const handleAvatarUpload = (event: Event) => {
  const target = event.target as HTMLInputElement
  const file = target.files?.[0]

  if (file) {
    if (file.size > 2 * 1024 * 1024) {
      message.value = 'Kích thước ảnh không được vượt quá 2MB'
      messageType.value = 'error'
      return
    }

    const reader = new FileReader()
    reader.onload = (e) => {
      const result = e.target?.result as string
      form.value.avatar = result
    }
    reader.readAsDataURL(file)
  }
}

const removeAvatar = () => {
  form.value.avatar = ''
}

const saveProfile = async () => {
  if (!form.value.email || !form.value.username) {
    message.value = 'Email và tên người dùng không được để trống'
    messageType.value = 'error'
    return
  }

  if (form.value.password && form.value.password !== form.value.confirmPassword) {
    message.value = 'Mật khẩu xác nhận không khớp'
    messageType.value = 'error'
    return
  }

  if (form.value.password && form.value.password.length < 6) {
    message.value = 'Mật khẩu phải có ít nhất 6 ký tự'
    messageType.value = 'error'
    return
  }

  loading.value = true
  message.value = ''

  try {
    const updateData: any = {
      email: form.value.email,
      username: form.value.username,
      avatar: form.value.avatar,
    }

    if (form.value.password) {
      updateData.password = form.value.password
    }

    const result = await authService.updateProfile(updateData)

    if (result.success) {
      message.value = result.message
      messageType.value = 'success'
      isEditing.value = false
      form.value.password = ''
      form.value.confirmPassword = ''
    } else {
      message.value = result.message
      messageType.value = 'error'
    }
  } catch (error) {
    message.value = 'Có lỗi xảy ra khi cập nhật thông tin'
    messageType.value = 'error'
  } finally {
    loading.value = false
  }
}

initForm()
</script>

<template>
  <div class="profile-page">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-xl-6">
        <div class="card">
          <div class="card-header text-center py-4">
            <div class="mb-3">
              <img v-if="user?.avatar" :src="user.avatar" class="avatar-img" alt="Avatar" />
              <div v-else class="avatar-placeholder">
                <i class="bi bi-person-circle"></i>
              </div>
            </div>
            <h2 class="mb-0">{{ user?.username }}</h2>
            <p class="mb-0 text-muted">{{ user?.email }}</p>
          </div>

          <div class="card-body p-4">
            <div
              v-if="message"
              :class="['alert', messageType === 'success' ? 'alert-success' : 'alert-danger']"
              role="alert"
            >
              {{ message }}
            </div>

            <div v-if="!isEditing">
              <h5 class="mb-3">Thông tin cá nhân</h5>

              <div class="row g-3">
                <div class="col-sm-6">
                  <label class="form-label text-muted">Email</label>
                  <p>{{ user?.email }}</p>
                </div>

                <div class="col-sm-6">
                  <label class="form-label text-muted">Tên người dùng</label>
                  <p>{{ user?.username }}</p>
                </div>
              </div>

              <div class="mt-4">
                <button class="btn btn-primary me-2" @click="toggleEdit">
                  Chỉnh sửa thông tin
                </button>
              </div>
            </div>

            <div v-else>
              <div class="d-flex justify-content-between align-items-center mb-3">
                <h5 class="mb-0">Chỉnh sửa thông tin</h5>
                <button
                  class="btn btn-outline-secondary btn-sm"
                  @click="toggleEdit"
                  :disabled="loading"
                >
                  Hủy
                </button>
              </div>

              <form @submit.prevent="saveProfile">
                <div class="mb-4 text-center">
                  <label class="form-label">Ảnh đại diện</label>
                  <div class="mb-2">
                    <img
                      v-if="form.avatar"
                      :src="form.avatar"
                      class="avatar-edit-img"
                      alt="Avatar"
                    />
                    <div v-else class="avatar-edit-placeholder">
                      <i class="bi bi-person-circle"></i>
                    </div>
                  </div>
                  <div>
                    <input
                      type="file"
                      id="avatarUpload"
                      class="d-none"
                      accept="image/*"
                      @change="handleAvatarUpload"
                    />
                    <label for="avatarUpload" class="btn btn-outline-primary btn-sm me-2">
                      Tải ảnh
                    </label>
                    <button
                      v-if="form.avatar"
                      type="button"
                      class="btn btn-outline-danger btn-sm"
                      @click="removeAvatar"
                    >
                      Xóa
                    </button>
                  </div>
                </div>

                <div class="row g-3">
                  <div class="col-sm-6">
                    <label for="email" class="form-label">Email</label>
                    <input
                      type="email"
                      class="form-control"
                      id="email"
                      v-model="form.email"
                      required
                    />
                  </div>

                  <div class="col-sm-6">
                    <label for="username" class="form-label">Tên người dùng</label>
                    <input
                      type="text"
                      class="form-control"
                      id="username"
                      v-model="form.username"
                      required
                    />
                  </div>
                </div>

                <hr class="my-4" />

                <h6 class="mb-3">Thay đổi mật khẩu (tùy chọn)</h6>
                <div class="row g-3">
                  <div class="col-sm-6">
                    <label for="password" class="form-label">Mật khẩu mới</label>
                    <input
                      type="password"
                      class="form-control"
                      id="password"
                      v-model="form.password"
                      placeholder="Để trống nếu không muốn thay đổi"
                    />
                  </div>

                  <div class="col-sm-6">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                    <input
                      type="password"
                      class="form-control"
                      id="confirmPassword"
                      v-model="form.confirmPassword"
                      placeholder="Nhập lại mật khẩu mới"
                    />
                  </div>
                </div>

                <div class="mt-4">
                  <button type="submit" class="btn btn-primary" :disabled="loading">
                    <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
                    {{ loading ? 'Đang cập nhật...' : 'Lưu thay đổi' }}
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.avatar-img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
}

.avatar-placeholder {
  font-size: 5rem;
  color: #6c757d;
}

.avatar-edit-img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
}

.avatar-edit-placeholder {
  font-size: 4rem;
  color: #6c757d;
}

.card {
  border: 1px solid #dee2e6;
  border-radius: 0.5rem;
}
</style>
