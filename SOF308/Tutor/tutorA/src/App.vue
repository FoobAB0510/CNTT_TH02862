<template>
  <div>
    <h1>Nội suy văn bản</h1>
    Number: {{ number }}
  </div>

  <div>
    nhúng html

    <div v-html="a"></div>
    <div @click="but">Click me</div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const a = "<p>Đây là một đoạn văn bản được nhúng vào HTML</p>"

const but = () => {
  alert("Button clicked");
};

const sv = ref({
  name: "Nguyễn Văn A",
  age: 20,
  address: "Hà Nội"
})
</script>
<style scoped>

</style>
