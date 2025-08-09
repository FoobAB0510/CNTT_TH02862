<template>
  <div>
    <!-- Nếu người dùng chưa đăng nhập, hiển thị form đăng nhập -->
    <LoginComponent v-if="!isLoggedIn" @loggedIn="handleLoginSuccess" />

    <!-- Nếu người dùng đã đăng nhập, hiển thị component bình luận -->
    <CommentComponent v-else :username="loggedInUser" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import LoginComponent from './login.vue';
import CommentComponent from './cmt.vue';

// Quản lý trạng thái đăng nhập và tên người dùng đã đăng nhập
const isLoggedIn = ref(false);
const loggedInUser = ref("");

// Xử lý khi người dùng đăng nhập thành công
function handleLoginSuccess(username) {
  loggedInUser.value = username;
  isLoggedIn.value = true;
}
</script>
