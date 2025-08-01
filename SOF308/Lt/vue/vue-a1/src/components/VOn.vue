<template>
    <p>Nhấp vào button để xem kết quả</p>
    <button class="btn" @click="showinfo()">Click me</button>
</template>

<script setup>
    const showinfo = () => {
        alert('Bạn vừa nhấp chuột vào button');
    }
</script>