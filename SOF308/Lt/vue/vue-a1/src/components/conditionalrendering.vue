<template>
  <div class="container">
    <h1>v-for</h1>
    <div v-for="item in [1, 2, 3, 4, 5]" :key="item">{{ item }}</div>

    <h1>v-if</h1>
    <div v-if="stfu.a">This is a conditional rendering example if true.</div>
    <div v-else="stfu.a">This content is shown when 'stfu' is false.</div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const stfu = ref({
  a: true,
  b: false,
});
</script>

<style lang="css" scoped>

</style>
