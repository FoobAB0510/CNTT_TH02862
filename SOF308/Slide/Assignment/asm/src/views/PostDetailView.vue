<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { dataService, type Post, type Comment } from '../services/dataService'
import { authService } from '../services/authService'

const route = useRoute()
const router = useRouter()
const user = authService.user

const post = ref<Post | null>(null)
const commentForm = ref({
  content: '',
})

const postId = computed(() => route.params.id as string)

const loadPost = () => {
  const foundPost = dataService.getPostById(postId.value)
  if (foundPost) {
    post.value = foundPost
  } else {
    router.push('/posts')
  }
}

const addComment = async () => {
  if (!commentForm.value.content.trim()) {
    alert('Vui lòng nhập nội dung bình luận')
    return
  }

  if (!user.value) {
    alert('Bạn cần đăng nhập để bình luận')
    return
  }

  try {
    const newComment = await dataService.addComment(postId.value, {
      content: commentForm.value.content,
      authorName: user.value.username,
    })

    if (newComment) {
      commentForm.value.content = ''
      loadPost() // Reload to get updated comments
    }
  } catch (error) {
    alert('Có lỗi xảy ra khi thêm bình luận')
  }
}

const deleteComment = async (commentId: number) => {
  if (confirm('Bạn có chắc chắn muốn xóa bình luận này?')) {
    try {
      await dataService.deleteComment(postId.value, commentId)
      loadPost()
    } catch (error) {
      alert('Có lỗi xảy ra khi xóa bình luận')
    }
  }
}

const formatDate = (dateString: string) => {
  return new Date(dateString).toLocaleDateString('vi-VN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })
}

const goBack = () => {
  router.push('/posts')
}

onMounted(() => {
  loadPost()
})
</script>

<template>
  <div class="post-detail-page" v-if="post">
    <!-- Back Button -->
    <div class="mb-4">
      <button class="btn btn-outline-secondary" @click="goBack">
        <i class="bi bi-arrow-left me-2"></i>
        Quay lại danh sách
      </button>
    </div>

    <!-- Post Content -->
    <article class="card border-0 shadow-sm">
      <!-- Post Image -->
      <div v-if="post.image" class="post-image-container">
        <img :src="post.image" class="card-img-top" alt="Post image" />
      </div>

      <div class="card-body p-4 p-md-5">
        <!-- Post Header -->
        <header class="mb-4">
          <h1 class="display-6 fw-bold text-primary mb-3">{{ post.title }}</h1>
          <div class="text-muted">
            <span class="me-4">
              <i class="bi bi-person me-2"></i>
              Được đăng bởi <strong>{{ post.authorName }}</strong>
            </span>
            <span>
              <i class="bi bi-clock me-2"></i>
              {{ formatDate(post.createdAt) }}
            </span>
          </div>
        </header>

        <!-- Post Content -->
        <div class="post-content">
          <p class="fs-5 lh-lg text-dark">{{ post.content }}</p>
        </div>
      </div>
    </article>

    <!-- Comments Section -->
    <div class="comments-section mt-5">
      <div class="card border-0 shadow-sm">
        <div class="card-header bg-white py-3">
          <h3 class="h5 mb-0 fw-bold">
            <i class="bi bi-chat-dots me-2"></i>
            Bình luận ({{ post.comments.length }})
          </h3>
        </div>

        <div class="card-body p-4">
          <!-- Add Comment Form -->
          <div class="mb-4">
            <form @submit.prevent="addComment">
              <div class="mb-3">
                <label for="commentContent" class="form-label fw-semibold"> Thêm bình luận </label>
                <textarea
                  class="form-control"
                  id="commentContent"
                  v-model="commentForm.content"
                  rows="4"
                  placeholder="Viết bình luận của bạn..."
                  required
                ></textarea>
              </div>
              <button type="submit" class="btn btn-primary" :disabled="!user">
                <i class="bi bi-send me-2"></i>
                {{ user ? 'Đăng bình luận' : 'Đăng nhập để bình luận' }}
              </button>
            </form>
          </div>

          <!-- Comments List -->
          <div v-if="post.comments.length > 0" class="comments-list">
            <div
              v-for="comment in post.comments"
              :key="comment.id"
              class="comment-item p-3 mb-3 bg-light rounded"
            >
              <div class="d-flex justify-content-between align-items-start mb-2">
                <div class="comment-meta">
                  <strong class="text-primary">{{ comment.authorName }}</strong>
                  <span class="text-muted ms-2">{{ formatDate(comment.createdAt) }}</span>
                </div>

                <!-- Delete comment button (only for comment author or post author) -->
                <button
                  v-if="user && (comment.authorName === user.username || post.authorId === user.id)"
                  class="btn btn-sm btn-outline-danger"
                  @click="deleteComment(comment.id)"
                  title="Xóa bình luận"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </div>

              <p class="comment-content mb-0">{{ comment.content }}</p>
            </div>
          </div>

          <!-- No Comments State -->
          <div v-else class="text-center py-4">
            <div class="text-muted mb-3">
              <i class="bi bi-chat" style="font-size: 3rem"></i>
            </div>
            <h5 class="text-muted">Chưa có bình luận nào</h5>
            <p class="text-muted">Hãy là người đầu tiên bình luận cho bài viết này!</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Loading/Error State -->
  <div v-else class="text-center py-5">
    <div class="text-muted mb-4">
      <i class="bi bi-exclamation-triangle" style="font-size: 4rem"></i>
    </div>
    <h3 class="text-muted">Không tìm thấy bài viết</h3>
    <p class="text-muted mb-4">Bài viết bạn đang tìm kiếm không tồn tại hoặc đã bị xóa.</p>
    <button class="btn btn-primary" @click="goBack">Quay lại danh sách</button>
  </div>
</template>

<style scoped>
.post-image-container {
  height: 400px;
  overflow: hidden;
}

.card-img-top {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.post-content {
  font-size: 1.1rem;
  line-height: 1.8;
}

.comment-item {
  transition: background-color 0.3s ease;
  border: 1px solid rgba(0, 0, 0, 0.05);
}

.comment-item:hover {
  background-color: #f8f9fa !important;
}

.comment-content {
  white-space: pre-wrap;
  word-wrap: break-word;
}

.bi {
  font-family: 'Bootstrap Icons' !important;
}

@media (max-width: 768px) {
  .post-image-container {
    height: 250px;
  }

  .card-body {
    padding: 1.5rem !important;
  }

  .display-6 {
    font-size: 1.75rem;
  }
}
</style>
