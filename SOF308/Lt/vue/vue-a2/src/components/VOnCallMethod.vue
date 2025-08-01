<template>
    <p> {{ message }}</p>
    <button class="button" @click="showinfo">Click me</button>
</template>

<script setup>
    import { ref } from 'vue';
    const message = ref('Qua lại khách chờ sông lận sóng');
    const showinfo = () => {
        message.value = message.value.split(' ').reverse().join(' ');
    };
</script>