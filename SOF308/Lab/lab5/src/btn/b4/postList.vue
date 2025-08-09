<template>
  <div>
    <h2
      v-for="(post, index) in posts"
      :key="index"
      :class="{ highlight: post.title.length > 20 }"
      :style="{ backgroundColor: post.backgroundColor, color: post.textColor }"
      class="post"
    >
      <h3 class="text-success">{{ post.title }}</h3>
      <h4 class="text-danger">{{ post.author }}</h4>
      <p>{{ post.content }}</p>
    </h2>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

defineProps({
  posts: {
    type: Array,
    required: true
  }
})
</script>


<style>
.post {
  padding: 20px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.hightlight {
  background-color: #f0f8ff;
}
</style>
