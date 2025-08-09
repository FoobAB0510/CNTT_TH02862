<script setup lang="ts">
import { authService } from './services/authService'
import { useRouter } from 'vue-router'

const router = useRouter()
const user = authService.user
const isAuthenticated = authService.isAuthenticated

const logout = () => {
  authService.logout()
  router.push('/')
}
</script>

<template>
  <div id="app" class="min-vh-100 d-flex flex-column" style="background-color: #fefefe">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg py-3" style="background-color: transparent">
      <div class="container">
        <!-- Brand -->
        <router-link to="/" class="navbar-brand fw-bold fs-4 text-dark navbar-link">
          TH02862
        </router-link>

        <!-- Toggle button for mobile -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar content -->
        <div class="collapse navbar-collapse" id="navbarNav">
          <!-- Center links -->
          <ul class="navbar-nav mx-auto">
            <li class="nav-item">
              <router-link
                to="/posts"
                class="nav-link px-3 py-2 rounded navbar-link"
                v-if="isAuthenticated"
              >
                Bài viết
              </router-link>
            </li>
          </ul>

          <!-- Right links -->
          <ul class="navbar-nav">
            <template v-if="!isAuthenticated">
              <li class="nav-item me-2">
                <router-link to="/login" class="nav-link px-3 py-2 rounded navbar-link">
                  Đăng nhập
                </router-link>
              </li>
              <li class="nav-item">
                <router-link to="/register" class="nav-link px-3 py-2 rounded navbar-link">
                  Đăng ký
                </router-link>
              </li>
            </template>
            <template v-else>
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle px-3 py-2 rounded navbar-link"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                >
                  {{ user?.username }}
                </a>
                <ul class="dropdown-menu">
                  <li>
                    <router-link to="/profile" class="dropdown-item">
                      Thông tin cá nhân
                    </router-link>
                  </li>
                  <li><hr class="dropdown-divider" /></li>
                  <li>
                    <a class="dropdown-item" href="#" @click.prevent="logout"> Đăng xuất </a>
                  </li>
                </ul>
              </li>
            </template>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Main content -->
    <main class="flex-grow-1">
      <div class="container py-4">
        <router-view />
      </div>
    </main>

    <!-- Footer -->
    <footer class="text-center py-4 border-top" style="background-color: #f8f9fa">
      <div class="container">
        <p class="mb-0 text-muted">© 2025 TH02862</p>
      </div>
    </footer>
  </div>
</template>

<style scoped>
.navbar-link {
  text-decoration: none !important;
}

.navbar-link:hover {
  background-color: rgba(13, 110, 253, 0.1) !important;
  color: #0d6efd !important;
}

#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>
