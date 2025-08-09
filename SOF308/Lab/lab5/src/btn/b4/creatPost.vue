<template>
  <div>
    <h2>Tạo bài viết mới</h2>
    <input v-model="title" placeholder="tiêu đề" />
    <input v-model="author" placeholder="tác giả" />
    <textarea v-model="content" placeholder="nội dung"></textarea>
    <button @click="submit">Đăng bài</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const emit = defineEmits(['submit'])

const title = ref('')
const author = ref('')
const content = ref('')

const submit = () => {
  // Xử lý đăng bài ở đây
  if (title.value && author.value && content.value) {
    const newPost = {
      title: title.value,
      author: author.value,
      content: content.value,
    }
    emit('submit', newPost)

    title.value = ''
    author.value = ''
    content.value = ''
  }
}
</script>

<style lang="css" scoped>
button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

input,textarea {
  display: block;
  width: 100%;
  margin: 10px 0;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}
</style>
