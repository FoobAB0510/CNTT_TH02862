<template>
  <div>
    <div class="container mb-3">
      <h5>Nhập điểm</h5>
      <input type="number" v-model="score" min="0" max="10" />
      <p v-if="score >= 9">Xuất sắc</p>
      <p v-else-if="score >= 8">Tốt</p>
      <p v-else-if="score >= 7">Khá</p>
      <p v-else-if="score >= 6">Trung bình</p>
      <p v-else-if="score >= 5">Yếu</p>
      <p v-else-if="score < 5">Kém</p>
    </div>
  </div>
</template>


<script setup lang="ts">
import { ref } from 'vue';

const score = ref<number>(0);
</script>
