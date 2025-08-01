<template>
    <h1>Hiển thị nội dung</h1>
    <h2>{{ message }}</h2>
    <h2>{{ number + 5}}</h2>
    <h2>{{ sayHi() }}</h2>
</template>

<script setup>
    import { ref } from 'vue';
    const count = ref(0);
    const message = 'Yo whatsapp NI!';
    const number = 10;
    const sayHi = () => {
        return 'Đây là một phương thức';
    };
</script>