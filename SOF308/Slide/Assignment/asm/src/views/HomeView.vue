<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { dataService } from '../services/dataService'

const loading = ref(true)
const posts = computed(() => dataService.getPosts())
const totalPosts = computed(() => posts.value.length)
const totalComments = computed(() =>
  posts.value.reduce((total, post) => total + post.comments.length, 0),
)

// Tải dữ liệu khi component được mount
onMounted(async () => {
  loading.value = true
  try {
    // Luôn tải lại dữ liệu để đảm bảo có data mới nhất
    await dataService.reloadAllData()
    console.log('✅ HomeView: Data loaded successfully')
  } catch (error) {
    console.error('❌ HomeView: Error loading data:', error)
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="home-page">
    <!-- Loading State -->
    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary mb-3" role="status">
        <span class="visually-hidden">Đang tải...</span>
      </div>
      <p class="text-muted">Đang tải dữ liệu...</p>
    </div>

    <!-- Main Content -->
    <div v-else>
      <!-- Welcome Section -->
      <div class="text-center mb-5">
        <h1 class="display-4 fw-bold text-primary mb-3">Chào mừng đến với Blog của tôi</h1>
        <p class="lead text-muted">Đăng xàm nhưng hay</p>
      </div>

      <!-- Statistics Section -->
      <div class="row g-4 mb-5">
        <div class="col-md-6">
          <div class="card border-0 shadow-sm h-100 stat-card">
            <div class="card-body text-center p-4">
              <div class="text-primary mb-3">
                <i class="bi bi-file-text" style="font-size: 3rem"></i>
              </div>
              <h3 class="card-title fw-bold text-primary">{{ totalPosts }}</h3>
              <p class="card-text text-muted">Bài viết đã đăng</p>
            </div>
          </div>
        </div>

        <div class="col-md-6">
          <div class="card border-0 shadow-sm h-100 stat-card">
            <div class="card-body text-center p-4">
              <div class="text-success mb-3">
                <i class="bi bi-chat-dots" style="font-size: 3rem"></i>
              </div>
              <h3 class="card-title fw-bold text-success">{{ totalComments }}</h3>
              <p class="card-text text-muted">Bình luận</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Latest Posts Preview -->
      <div v-if="posts.length > 0" class="latest-posts">
        <h2 class="h3 mb-4 text-center">Bài viết mới nhất</h2>
        <div class="row g-4">
          <div v-for="post in posts.slice(0, 3)" :key="post.id" class="col-lg-4 col-md-6">
            <div class="card border-0 shadow-sm h-100 post-preview-card">
              <div v-if="post.image" class="card-img-top-container">
                <img :src="post.image" class="card-img-top" alt="Post image" />
              </div>
              <div class="card-body d-flex flex-column">
                <h5 class="card-title fw-bold">{{ post.title }}</h5>
                <p class="card-text text-muted flex-grow-1">
                  {{ post.content.substring(0, 100) }}...
                </p>
                <div class="d-flex justify-content-between align-items-center mt-auto">
                  <small class="text-muted">
                    <i class="bi bi-person"></i>
                    {{ post.authorName }}
                  </small>
                  <small class="text-muted">
                    <i class="bi bi-chat"></i>
                    {{ post.comments.length }}
                  </small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Empty State -->
      <div v-else class="text-center py-5">
        <div class="text-muted mb-4">
          <i class="bi bi-file-text" style="font-size: 4rem"></i>
        </div>
        <h3 class="text-muted">Chưa có bài viết nào</h3>
        <p class="text-muted">Hãy đăng nhập và tạo bài viết đầu tiên!</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-img-top-container {
  height: 200px;
  overflow: hidden;
}

.card-img-top {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.bi {
  font-family: 'Bootstrap Icons' !important;
}
</style>
