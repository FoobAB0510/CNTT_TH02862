<template>
  <div class="container mt-5">
    <div class="row">
      <!-- Form đăng ký -->
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <h3>Form Đăng Ký Người Dùng</h3>
          </div>
          <div class="card-body">
            <form @submit.prevent="register">
              <!-- Họ tên -->
              <div class="mb-3">
                <label for="fullName" class="form-label">Họ tên:</label>
                <input
                  type="text"
                  class="form-control"
                  id="fullName"
                  v-model="user.fullName"
                  placeholder="Nhập họ tên"
                  required
                />
              </div>

              <!-- Email -->
              <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  v-model="user.email"
                  placeholder="Nhập email"
                  required
                />
              </div>

              <!-- Mật khẩu -->
              <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu:</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  v-model="user.password"
                  placeholder="Nhập mật khẩu"
                  required
                />
              </div>

              <!-- Ngày sinh -->
              <div class="mb-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input
                  type="date"
                  class="form-control"
                  id="birthday"
                  v-model="user.birthday"
                  required
                />
              </div>

              <!-- Giới tính -->
              <div class="mb-3">
                <label class="form-label">Giới tính:</label>
                <div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="male"
                      value="Nam"
                      v-model="user.gender"
                    />
                    <label class="form-check-label" for="male">Nam</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="female"
                      value="Nữ"
                      v-model="user.gender"
                    />
                    <label class="form-check-label" for="female">Nữ</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="other"
                      value="Khác"
                      v-model="user.gender"
                    />
                    <label class="form-check-label" for="other">Khác</label>
                  </div>
                </div>
              </div>

              <!-- Ngôn ngữ -->
              <div class="mb-3">
                <label for="languages" class="form-label">Ngôn ngữ:</label>
                <div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="vietnamese"
                      value="Tiếng Việt"
                      v-model="user.languages"
                    />
                    <label class="form-check-label" for="vietnamese">Tiếng Việt</label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="english"
                      value="Tiếng Anh"
                      v-model="user.languages"
                    />
                    <label class="form-check-label" for="english">Tiếng Anh</label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="chinese"
                      value="Tiếng Trung"
                      v-model="user.languages"
                    />
                    <label class="form-check-label" for="chinese">Tiếng Trung</label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="japanese"
                      value="Tiếng Nhật"
                      v-model="user.languages"
                    />
                    <label class="form-check-label" for="japanese">Tiếng Nhật</label>
                  </div>
                </div>
              </div>

              <button type="submit" class="btn btn-primary">Đăng Ký</button>
            </form>
          </div>
        </div>
      </div>

      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <h3>{{ isRegistered ? 'Thông Tin Đã Đăng Ký' : 'Thông Tin Đã Đăng Ký' }}</h3>
          </div>
          <div class="card-body">
            <div class="mb-3">
              <strong>Họ tên:</strong>
              <span v-if="user.fullName">{{ user.fullName }}</span>
              <span v-else class="text-muted">Chưa nhập họ tên</span>
            </div>
            <div class="mb-3">
              <strong>Email:</strong>
              <span v-if="user.email">{{ user.email }}</span>
              <span v-else class="text-muted">Chưa nhập email</span>
            </div>
            <div class="mb-3">
              <strong>Mật khẩu:</strong>
              <span v-if="user.password" class="text-success">*** (Đã nhập)</span>
              <span v-else class="text-muted">Chưa nhập mật khẩu</span>
            </div>
            <div class="mb-3">
              <strong>Ngày sinh:</strong>
              <span v-if="user.birthday">{{ formatDate(user.birthday) }}</span>
              <span v-else class="text-muted">Chưa chọn ngày sinh</span>
            </div>
            <div class="mb-3">
              <strong>Giới tính:</strong>
              <span v-if="user.gender">{{ user.gender }}</span>
              <span v-else class="text-muted">Chưa chọn giới tính</span>
            </div>
            <div class="mb-3">
              <strong>Ngôn ngữ:</strong>
              <span v-if="user.languages.length > 0">{{ user.languages.join(', ') }}</span>
              <span v-else class="text-muted">Chưa chọn ngôn ngữ</span>
            </div>

            <div v-if="isRegistered" class="mt-4">
              <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Đăng ký thành công!
              </div>
              <button @click="resetForm" class="btn btn-secondary">Đăng Ký Mới</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const user = ref({
  fullName: '',
  email: '',
  password: '',
  birthday: '',
  gender: '',
  languages: [],
})

const isRegistered = ref(false)

const register = () => {
  isRegistered.value = true
}

const resetForm = () => {
  user.value = {
    fullName: '',
    email: '',
    password: '',
    birthday: '',
    gender: '',
    languages: [],
  }
  isRegistered.value = false
}

const formatDate = (dateString) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString('vi-VN')
}
</script>

<style scoped>
.card {
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #f8f9fa;
  border-bottom: 1px solid #dee2e6;
}

.text-muted {
  color: #6c757d !important;
  font-style: italic;
}

.text-success {
  color: #198754 !important;
}

.alert {
  padding: 0.75rem 1.25rem;
  margin-bottom: 1rem;
  border: 1px solid transparent;
  border-radius: 0.375rem;
}

.alert-success {
  color: #0f5132;
  background-color: #d1e7dd;
  border-color: #badbcc;
}
</style>
