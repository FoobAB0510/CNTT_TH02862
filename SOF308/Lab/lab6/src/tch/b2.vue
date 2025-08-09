<template>
  <div class="container">
    <h3>Nhập tháng</h3>
    <input type="number" v-model.number="month">
    <p v-if="month < 1 || month > 12">Tháng không hợp lệ</p>
    <p v-else-if="month >= 1 && month <= 3">Mùa xuân</p>
    <p v-else-if="month <= 6">Mùa hè</p>
    <p v-else-if="month <= 9">Mùa thu</p>
    <p v-else>Mùa đông</p>
  </div>
</template>


<script setup>
import { ref } from 'vue';
const month = ref(1);

</script>
