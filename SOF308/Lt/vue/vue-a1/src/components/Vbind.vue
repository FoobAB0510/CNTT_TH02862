<template>
    <div>
        <h2>{{ title }}</h2>
        <img v-bind:src="imageUrl" v-bind:alt="altText" width="200" height="auto"/>
        <p v-bind:class="{highlighted:isHighlighted}">{{ description }}</p>
        <a v-bind:href="link" target="_blank">Tìm hiểu thêm về VueJS</a>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    const title = ref('VueJS');
    const imageUrl = ref('https://vuejs.org/images/logo.png');
    const altText = ref('VueJS Logo');
    const description = ref('Một frontend framework mạnh mẽ và linh hoạt');
    const link = ref('https://vuejs.org/');
    const isHighlighted = ref(true);
</script>

<style scoped>
    .highlighted {
        color: red;
        font-weight: 800;
    }
</style>