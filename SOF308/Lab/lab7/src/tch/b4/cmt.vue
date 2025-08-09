<template>
  <div class="col-md-4 m-3">
    <h3>Minh luận bài viết</h3>
    <div class="card">
      <img src="https://placehold.co/300x200" alt="Hình ảnh" />
      <div class="card-body">
        <h3 class="card-title">8 loại rau củ quả giàm canxi</h3>
        <p class="card-text">Canxi là khoáng chất cần thiết đối với thể người. Có nhiều cách để bổ sung canxi, trong đó bổ sung qua những ăn uống là cách tốt nhất. Có 8 loại rau củ giàu canxi...</p>
      </div>
    </div>
    <form @submit.prevent="submitComment">
      <div class="mt-3">
        <textarea id="commentText" cols="60" v-model="commentText" placeholder="Nhập bình luận của bạn"></textarea>
      </div>
      <button type="submit" class="btn btn-success">Gửi bình luận</button>
    </form>
    <div v-if="comments.length" class="mt-3">
      <h3>Danh sách các bình luận</h3>
      <ul class="list-style-type: circle;">
        <li v-for="(comment, index) in comments" :key="index">
          <strong>{{ comment.name }}</strong>: {{ comment.text }}</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const props = defineProps(['username']);

const commentText = ref("");

// Mảng chứa các bình luận đã gởi
const comments = ref([]);

// xử lý gửi bình luận
function submitComment() {
  if (commentText.value) {
    // Thêm bình luận mới vào mảng comments
    comments.value.push({
      name: props.username,  // Sử dụng tên từ props
      text: commentText.value
    });

    // Xóa dữ liệu trong form sau khi gởi
    commentText.value = '';
  }
}
</script>
