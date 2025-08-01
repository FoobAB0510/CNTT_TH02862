<template>
  <div class="container">
    <h4>1. v-for</h4>
    <ul>
      <li v-for="item in student">
        {{ item.name }} - {{ item.age }} - {{ item.address }}

      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const student = ref(
  {
    name: 'Nguyen Van A',
    age: 20,
    address: '123 Main St',
  },
  {
    name: 'Nguyen Van B',
    age: 11,
    address: '123 Main St',
  },
  {
    name: 'Nguyen Van C',
    age: 99,
    address: '123 Main St',
  }
)
</script>

<style lang="css" scoped></style>
