<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { dataService, type Post } from '../services/dataService'
import { authService } from '../services/authService'

const router = useRouter()
const user = authService.user

const posts = ref<Post[]>(dataService.getPosts())
const showCreateForm = ref(false)
const showEditForm = ref(false)
const showDeleteModal = ref(false)
const selectedPost = ref<Post | null>(null)

const createForm = ref({
  title: '',
  content: '',
  image: '',
})

const editForm = ref({
  title: '',
  content: '',
  image: '',
})

const refreshPosts = () => {
  posts.value = dataService.getPosts()
}

const openCreateForm = () => {
  createForm.value = { title: '', content: '', image: '' }
  showCreateForm.value = true
}

const createPost = async () => {
  if (!createForm.value.title || !createForm.value.content) {
    alert('Vui lòng nhập đầy đủ thông tin')
    return
  }

  if (!user.value) {
    alert('Bạn chưa đăng nhập')
    return
  }

  try {
    await dataService.createPost({
      title: createForm.value.title,
      content: createForm.value.content,
      image: createForm.value.image,
      authorId: user.value.id,
      authorName: user.value.username,
    })

    showCreateForm.value = false
    refreshPosts()
  } catch (error) {
    alert('Có lỗi xảy ra khi tạo bài viết')
  }
}

const openEditForm = (post: Post) => {
  if (post.authorId !== user.value?.id) {
    alert('Bạn chỉ có thể chỉnh sửa bài viết của mình')
    return
  }

  selectedPost.value = post
  editForm.value = {
    title: post.title,
    content: post.content,
    image: post.image,
  }
  showEditForm.value = true
}

const editPost = async () => {
  if (!selectedPost.value) return

  if (!editForm.value.title || !editForm.value.content) {
    alert('Vui lòng nhập đầy đủ thông tin')
    return
  }

  try {
    await dataService.updatePost(selectedPost.value.id, {
      title: editForm.value.title,
      content: editForm.value.content,
      image: editForm.value.image,
    })

    showEditForm.value = false
    selectedPost.value = null
    refreshPosts()
  } catch (error) {
    alert('Có lỗi xảy ra khi cập nhật bài viết')
  }
}

const openDeleteModal = (post: Post) => {
  if (post.authorId !== user.value?.id) {
    alert('Bạn chỉ có thể xóa bài viết của mình')
    return
  }

  selectedPost.value = post
  showDeleteModal.value = true
}

const deletePost = async () => {
  if (!selectedPost.value) return

  try {
    await dataService.deletePost(selectedPost.value.id)
    showDeleteModal.value = false
    selectedPost.value = null
    refreshPosts()
  } catch (error) {
    alert('Có lỗi xảy ra khi xóa bài viết')
  }
}

const viewPost = (postId: string) => {
  router.push(`/posts/${postId}`)
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

const handleImageUpload = (event: Event, isEdit = false) => {
  const target = event.target as HTMLInputElement
  const file = target.files?.[0]

  if (file) {
    const reader = new FileReader()
    reader.onload = (e) => {
      const result = e.target?.result as string
      if (isEdit) {
        editForm.value.image = result
      } else {
        createForm.value.image = result
      }
    }
    reader.readAsDataURL(file)
  }
}

const closeAllModals = () => {
  showCreateForm.value = false
  showEditForm.value = false
  showDeleteModal.value = false
}

// Load posts when component mounts
onMounted(() => {
  refreshPosts()
})
</script>

<template>
  <div class="posts-page">
    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h1 class="h2 fw-bold text-primary">Bài viết</h1>
      <button class="btn btn-primary btn-lg" @click="openCreateForm">
        <i class="bi bi-plus-circle me-2"></i>
        Tạo bài viết
      </button>
    </div>

    <!-- Posts List -->
    <div class="row g-4">
      <div v-for="post in posts" :key="post.id" class="col-lg-6 col-xl-4">
        <div class="card border-0 shadow-sm h-100 post-card">
          <!-- Post Image -->
          <div v-if="post.image" class="card-img-container">
            <img :src="post.image" class="card-img-top" alt="Post image" />
          </div>

          <div class="card-body d-flex flex-column">
            <!-- Post Header -->
            <div class="d-flex justify-content-between align-items-start mb-3">
              <h5 class="card-title fw-bold mb-0 flex-grow-1 me-2">{{ post.title }}</h5>

              <!-- Action Dropdown -->
              <div class="dropdown" v-if="post.authorId === user?.id">
                <button class="btn btn-link text-muted p-0" type="button" data-bs-toggle="dropdown">
                  <i class="bi bi-three-dots-vertical"></i>
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <a class="dropdown-item" href="#" @click.prevent="openEditForm(post)">
                      <i class="bi bi-pencil me-2"></i>Chỉnh sửa
                    </a>
                  </li>
                  <li>
                    <a
                      class="dropdown-item text-danger"
                      href="#"
                      @click.prevent="openDeleteModal(post)"
                    >
                      <i class="bi bi-trash me-2"></i>Xóa
                    </a>
                  </li>
                </ul>
              </div>
            </div>

            <!-- Post Meta -->
            <div class="text-muted small mb-3">
              <i class="bi bi-person me-1"></i>{{ post.authorName }}
              <span class="mx-2">•</span>
              <i class="bi bi-clock me-1"></i>{{ formatDate(post.createdAt) }}
            </div>

            <!-- Post Content -->
            <p class="card-text text-muted flex-grow-1">
              {{ post.content.substring(0, 150) }}{{ post.content.length > 150 ? '...' : '' }}
            </p>

            <!-- Post Footer -->
            <div class="d-flex justify-content-between align-items-center mt-auto">
              <small class="text-muted">
                <i class="bi bi-chat me-1"></i>{{ post.comments.length }} bình luận
              </small>
              <button class="btn btn-outline-primary btn-sm" @click="viewPost(post.id)">
                Xem chi tiết
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Empty State -->
    <div v-if="posts.length === 0" class="text-center py-5">
      <div class="text-muted mb-4">
        <i class="bi bi-file-text" style="font-size: 4rem"></i>
      </div>
      <h3 class="text-muted">Chưa có bài viết nào</h3>
      <p class="text-muted mb-4">Hãy tạo bài viết đầu tiên của bạn!</p>
      <button class="btn btn-primary" @click="openCreateForm">Tạo bài viết</button>
    </div>

    <!-- Create Post Modal -->
    <div
      class="modal"
      :class="{ show: showCreateForm }"
      :style="{ display: showCreateForm ? 'block' : 'none' }"
      tabindex="-1"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Tạo bài viết mới</h5>
            <button type="button" class="btn-close" @click="showCreateForm = false"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="createPost">
              <div class="mb-3">
                <label for="createTitle" class="form-label">Tiêu đề</label>
                <input
                  type="text"
                  class="form-control"
                  id="createTitle"
                  v-model="createForm.title"
                  placeholder="Nhập tiêu đề bài viết"
                  required
                />
              </div>

              <div class="mb-3">
                <label for="createImage" class="form-label">Ảnh minh họa</label>
                <input
                  type="file"
                  class="form-control"
                  id="createImage"
                  accept="image/*"
                  @change="handleImageUpload($event)"
                />
                <div v-if="createForm.image" class="mt-2">
                  <img :src="createForm.image" class="img-thumbnail" style="max-height: 200px" />
                </div>
              </div>

              <div class="mb-3">
                <label for="createContent" class="form-label">Nội dung</label>
                <textarea
                  class="form-control"
                  id="createContent"
                  v-model="createForm.content"
                  rows="6"
                  placeholder="Viết nội dung bài viết..."
                  required
                ></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showCreateForm = false">
              Hủy
            </button>
            <button type="button" class="btn btn-primary" @click="createPost">Đăng bài</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Edit Post Modal -->
    <div
      class="modal"
      :class="{ show: showEditForm }"
      :style="{ display: showEditForm ? 'block' : 'none' }"
      tabindex="-1"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Chỉnh sửa bài viết</h5>
            <button type="button" class="btn-close" @click="showEditForm = false"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="editPost">
              <div class="mb-3">
                <label for="editTitle" class="form-label">Tiêu đề</label>
                <input
                  type="text"
                  class="form-control"
                  id="editTitle"
                  v-model="editForm.title"
                  placeholder="Nhập tiêu đề bài viết"
                  required
                />
              </div>

              <div class="mb-3">
                <label for="editImage" class="form-label">Ảnh minh họa</label>
                <input
                  type="file"
                  class="form-control"
                  id="editImage"
                  accept="image/*"
                  @change="handleImageUpload($event, true)"
                />
                <div v-if="editForm.image" class="mt-2">
                  <img :src="editForm.image" class="img-thumbnail" style="max-height: 200px" />
                </div>
              </div>

              <div class="mb-3">
                <label for="editContent" class="form-label">Nội dung</label>
                <textarea
                  class="form-control"
                  id="editContent"
                  v-model="editForm.content"
                  rows="6"
                  placeholder="Viết nội dung bài viết..."
                  required
                ></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showEditForm = false">
              Hủy
            </button>
            <button type="button" class="btn btn-primary" @click="editPost">Cập nhật</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div
      class="modal"
      :class="{ show: showDeleteModal }"
      :style="{ display: showDeleteModal ? 'block' : 'none' }"
      tabindex="-1"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title text-danger">Xác nhận xóa</h5>
            <button type="button" class="btn-close" @click="showDeleteModal = false"></button>
          </div>
          <div class="modal-body">
            <p>
              Bạn có chắc chắn muốn xóa bài viết "<strong>{{ selectedPost?.title }}</strong
              >"?
            </p>
            <p class="text-muted">Hành động này không thể hoàn tác.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showDeleteModal = false">
              Hủy
            </button>
            <button type="button" class="btn btn-danger" @click="deletePost">Xóa bài viết</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Backdrop -->
    <div
      v-if="showCreateForm || showEditForm || showDeleteModal"
      class="modal-backdrop show"
      @click="closeAllModals"
    ></div>
  </div>
</template>

<style scoped>
.post-card {
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
}

.card-img-container {
  height: 250px;
  overflow: hidden;
}

.card-img-top {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.modal.show {
  display: block !important;
}

.bi {
  font-family: 'Bootstrap Icons' !important;
}
</style>
