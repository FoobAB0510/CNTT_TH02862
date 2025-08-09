<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'

const router = useRouter()

const form = ref({
  email: '',
  password: '',
})

const loading = ref(false)
const error = ref('')

const handleSubmit = async () => {
  if (!form.value.email || !form.value.password) {
    error.value = 'Vui lòng nhập đầy đủ thông tin'
    return
  }

  loading.value = true
  error.value = ''

  try {
    const result = await authService.login(form.value.email, form.value.password)

    if (result.success) {
      router.push('/')
    } else {
      error.value = result.message
    }
  } catch (err) {
    error.value = 'Có lỗi xảy ra khi đăng nhập'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="login-page">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-4">
        <div class="card border-0 shadow-lg">
          <div class="card-body p-5">
            <div class="text-center mb-4">
              <h2 class="fw-bold text-primary">Đăng nhập</h2>
              <p class="text-muted">Chào mừng bạn quay trở lại!</p>
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

              <div class="mb-4">
                <label for="password" class="form-label fw-semibold">Mật khẩu</label>
                <input
                  type="password"
                  class="form-control form-control-lg"
                  id="password"
                  v-model="form.password"
                  placeholder="Nhập mật khẩu"
                  required
                />
              </div>

              <button type="submit" class="btn btn-primary btn-lg w-100 mb-3" :disabled="loading">
                <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
                {{ loading ? 'Đang đăng nhập...' : 'Đăng nhập' }}
              </button>
            </form>

            <div class="text-center">
              <p class="text-muted">
                Chưa có tài khoản?
                <router-link to="/register" class="text-primary text-decoration-none fw-semibold">
                  Đăng ký ngay
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
.login-page {
  padding-top: 3rem;
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
