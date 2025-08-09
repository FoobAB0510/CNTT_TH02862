<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'

const router = useRouter()

const form = ref({
  email: '',
  username: '',
  password: '',
  confirmPassword: '',
})

const loading = ref(false)
const error = ref('')

const handleSubmit = async () => {
  if (
    !form.value.email ||
    !form.value.username ||
    !form.value.password ||
    !form.value.confirmPassword
  ) {
    error.value = 'Vui lòng nhập đầy đủ thông tin'
    return
  }

  if (form.value.password !== form.value.confirmPassword) {
    error.value = 'Mật khẩu xác nhận không khớp'
    return
  }

  if (form.value.password.length < 6) {
    error.value = 'Mật khẩu phải có ít nhất 6 ký tự'
    return
  }

  loading.value = true
  error.value = ''

  try {
    const result = await authService.register(
      form.value.email,
      form.value.username,
      form.value.password,
    )

    if (result.success) {
      router.push('/')
    } else {
      error.value = result.message
    }
  } catch (err) {
    error.value = 'Có lỗi xảy ra khi đăng ký'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="register-page">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="card border-0 shadow-lg">
          <div class="card-body p-5">
            <div class="text-center mb-4">
              <h2 class="fw-bold text-primary">Đăng ký</h2>
              <p class="text-muted">Tạo tài khoản mới để bắt đầu</p>
            </div>

            <div v-if="error" class="alert alert-danger" role="alert">
              {{ error }}
            </div>

            <form @submit.prevent="handleSubmit">
              <div class="mb-3">
                <label for="email" class="form-label fw-semibold">Email</label>
                <input
                  type="email"
                  class="form-control form-control-lg"
                  id="email"
                  v-model="form.email"
                  placeholder="Nhập email của bạn"
                  required
                />
              </div>

              <div class="mb-3">
                <label for="username" class="form-label fw-semibold">Tên người dùng</label>
                <input
                  type="text"
                  class="form-control form-control-lg"
                  id="username"
                  v-model="form.username"
                  placeholder="Nhập tên người dùng"
                  required
                />
              </div>

              <div class="mb-3">
                <label for="password" class="form-label fw-semibold">Mật khẩu</label>
                <input
                  type="password"
                  class="form-control form-control-lg"
                  id="password"
                  v-model="form.password"
                  placeholder="Nhập mật khẩu (ít nhất 6 ký tự)"
                  required
                />
              </div>

              <div class="mb-4">
                <label for="confirmPassword" class="form-label fw-semibold"
                  >Xác nhận mật khẩu</label
                >
                <input
                  type="password"
                  class="form-control form-control-lg"
                  id="confirmPassword"
                  v-model="form.confirmPassword"
                  placeholder="Nhập lại mật khẩu"
                  required
                />
              </div>

              <button type="submit" class="btn btn-primary btn-lg w-100 mb-3" :disabled="loading">
                <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
                {{ loading ? 'Đang đăng ký...' : 'Đăng ký' }}
              </button>
            </form>

            <div class="text-center">
              <p class="text-muted">
                Đã có tài khoản?
                <router-link to="/login" class="text-primary text-decoration-none fw-semibold">
                  Đăng nhập ngay
                </router-link>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.register-page {
  padding-top: 2rem;
}

.card {
  border-radius: 1rem;
  overflow: hidden;
}

.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}

.btn-primary {
  border-radius: 0.5rem;
  transition: all 0.3s ease;
}

.btn-primary:hover {
  transform: translateY(-1px);
  box-shadow: 0 0.5rem 1rem rgba(13, 110, 253, 0.3);
}
</style>
