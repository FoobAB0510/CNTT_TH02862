<template>
  <div class="container">
    <b1/>
  </div>

  <div class="container">
    <b2/>
  </div>

  <div class="container">
    <h1>Php My Profile</h1>
    <b3/>
  </div>

  <br>
  <br>

  <div class="container">
    <creatPost @submit="addPost"/>
    <postList :posts="posts"/>
  </div>
</template>

<script setup>
import postList from './btn/b4/postList.vue';
import creatPost from './btn/b4/creatPost.vue';
import b1 from './btn/b1.vue';
import b2 from './btn/b2.vue';
import b3 from './btn/b3.vue';
import { ref } from 'vue';

const posts = ref([])

function addPost(post) {
  posts.value.push(post)
}
</script>

<style lang="css" scoped>
#app {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
</style>
