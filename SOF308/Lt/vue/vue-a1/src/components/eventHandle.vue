<script>
import { ref } from 'vue';

const count = ref(0);

const message = ref('')
    const method = () => {
        message.value = 'Đã kích hoạt method()!';
        console.log('Phương thức đã được gọi.');
    }
</script>
<template>
  <div class="container mb-3">
    <h4>1. Khái niệm event</h4>
    <button class="btn btn-dark" @click="count++">count is {{ count }}</button>
  </div>

  <div class="container mb-3">
        <h4>5. Các phím hệ thống</h4>
        <input @keyup.alt.c="method" placeholder="Nhấn Alt + C ở đây" />
        <div @click.ctrl="method" style="margin-top: 10px; cursor: pointer; background-color: #eef; padding: 2rem;">
            Ctrl + click để thực hiện
        </div>
        <p v-if="message">{{ message }}</p>
    </div>
</template>

<style lang="css" scoped></style>
