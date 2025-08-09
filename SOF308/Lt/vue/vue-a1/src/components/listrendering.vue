<template>
  <div class="container mb-3">
    <h4>1. v-for với mảng</h4>
    <ul>
      <li v-for="(st, index) in student">
        {{ st }}
      </li>
    </ul>
  </div>

  <div class="container mb-3">
    <h4>2. v-for với phạm vi</h4>
    <ul>
      <li v-for="i in 10">số {{ i }}</li>
    </ul>
  </div>

  <div class="container mb-3">
    <h4>3. v-for với template</h4>
    <template v-for="item in items" :key="item.name">
      <h5>{{ item.name }}</h5>
      <p>{{ item.description }}</p>
    </template>
  </div>

  <div class="container mb-3">
    <h4>4. kết hợp v-if và v-for</h4>
    <ul>
      <template v-for="fruint in fruit" :key="fruint.id">
        <li v-if="fruint.visible">
          {{ fruint.name }}
        </li>
      </template>
    </ul>
  </div>

  <div class="container mb-3">
    <h4>5. Quản lý khách hàng</h4>
    <form class="col-md-6" @submit.prevent="addCustomer">
      <h5 class="text-success"> Thêm khách hàng</h5>
      <div class="form-group">
        <label>Tên khách hàng</label>
        <input type="text" v-model="newCustomer.name" class="form-control" placeholder="Nhập tên" required id="name" name="name">
      </div>
      <div class="form-group">
        <label>Tuổi</label>
        <input type="number" v-model="newCustomer.age" class="form-control" placeholder="Nhập tuổi" required id="age" name="age">
      </div>
      <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
    <div class="col-mb-6">
      <h5 class="text-danger">Danh sach khach hang</h5>
      <ul class="list-group">
        <li class="list-group-item" v-for="(c, index) in customer" :key="index">
          {{ c.name }} - {{ c.age }} tuổi
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const student = ref(['Nguyễn Văn A', 'Nguyễn Văn B', 'Nguyễn Văn C', 'Nguyễn Văn D'])

const items = ref([
  { name: 'Item 1', description: 'Description 1' },
  { name: 'Item 2', description: 'Description 2' },
  { name: 'Item 3', description: 'Description 3' },
])

const fruit = ref([
  { id: 1, name: 'táo', visible: true },
  { id: 2, name: 'cam', visible: true },
  { id: 3, name: 'chuối', visible: false },
  { id: 4, name: 'dưa hấu', visible: true },
])

const customer = ref([
  { name: 'Nguyễn Văn A', age: 18 },
  { name: 'Nguyễn Văn B', age: 20 },
  { name: 'Nguyễn Văn C', age: 22 },
])

const newCustomer = ref({ name: '', age: null })

const addCustomer = () => {
  if(newCustomer.value.name && newCustomer.value.age) {
    customer.value.push({ ...newCustomer.value });
    newCustomer.value.name = '';
    newCustomer.value.age = null;
  }
}

</script>

<style lang="css" scoped></style>
