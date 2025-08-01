<template>
  <div>Vòng lặp</div>

  <table class="table">
    <thead>
      <tr>
        <th>#</th>
        <th>First</th>
        <th>Last</th>
        <th>Handle</th>
      </tr>
    </thead>
    <tbody>
      <template v-for="item in tcc" :key="item.id">
        <tr v-if="item.age > 30">
          <th>{{ item.id }}</th>
          <td>{{ item.first }}</td>
          <td>{{ item.last }}</td>
          <td>{{ item.handle }}</td>
        </tr>
      </template>
    </tbody>
  </table>

  <div class="container">
    <ul class="container">
      <template v-for="item in tcc" :key="item.id">
        <li>{{ item.first }} {{ item.last }} ({{ item.handle }})</li>
      </template>
    </ul>
    <h1>Load combobox</h1>
    <select class="form-select" id="" aria-label="Default select example">
      <template v-for="item in tcc" :key="item.id" :value="item.id">
        <option>{{ item.last }}</option>
      </template>
    </select>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "App",
  setup() {
    const tcc = ref([
      { id: 1, first: "Mark", last: "Otto", handle: "@mdo", age: 28 },
      { id: 2, first: "Jacob", last: "Thornton", handle: "@fat", age: 32 },
      { id: 3, first: "Larry", last: "the Bird", handle: "@twitter", age: 45 },
    ]);

    return { tcc };
  },
}
</script>

<style lang="css" scoped></style>
