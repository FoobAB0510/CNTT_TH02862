<script setup>
import { ref } from 'vue';
const seen = ref(true);
const ok = ref(true);
const message = ref(true);
const nextStep = () => {
    if (step.value < 4) {
        step.value++;
    } else {
        step.value = 1;
    }
};
const step = ref(1);
//V-show
const isVisible = ref(true);
const toggleVisibility = () => {
    isVisible.value = !isVisible.value;
};
const products = ref([
    {
        name: 'Vợ Nhặt',
        description: 'Tác phẩm Vợ Nhặt của tác giả Kim Lân (1921-2007)',
        price: 55000,
    }
]);
const showDetails = ref(products.value.map(() => false));
const toggleDetails = (index) => {
    showDetails.value[index] = !showDetails.value[index];
};
</script>
<template>
  <div class="container">
      <h4>1. v-if</h4>
      <span v-if="seen">Bạn đang nhìn thấy tôi</span>
  </div>
  <div class="container mb-3">
      <h4>2. v-if trên template</h4>
      <template v-if="ok">
          <h1>Title</h1>
          <p>Paragraph 1</p>
          <p>Paragraph 2</p>
      </template>
  </div>
  <div class="container mb-3">
      <h4>3. v-else</h4>
      <p>Bấm vào Ok nhiều lần để chuyển đổi thông điệp</p>
      <button @click="message = !message">Toggle Message</button>
      <h1 v-if="message">Xin chúc mừng bạn!</h1>
      <h1 v-else>Rất tiếc, hẹn gặp lại!</h1>
  </div>
  <div class="container mb-3">
      <h4>4. v-else-if</h4>
      <button class="btn btn-primary" @click="nextStep()">Bước tiếp theo</button>
      <p v-if="step === 1">Bước 1: Giới thiệu</p>
      <p v-else-if="step === 2">Bước 2: Cài đặt</p>
      <P v-else-if="step === 3">Bước 3: Thực thi</P>
      <p v-else>Bạn đã hoàn thành!</p>
  </div>
  <div class="container mb-3">
      <h4>5. v-show</h4>
      <button class="btn btn-primary" @click="toggleVisibility">Hiển thị/ Ẩn</button>
      <p v-show="isVisible">Thông điệp này được chuyển đổi bởi v-show</p>
  </div>
  <div class="container mb-3">
      <h4>5. v-show</h4>
      <h5>Top sách bán chạy</h5>
      <ul class="list-group">
          <li class="list-group-item" v-for="(product, index) in products">
              <h6>{{ product.name }} - {{ product.price }}</h6>
              <button @click="toggleDetails(index)" class="btn btn-primary">
                  {{ showDetails[index] ? 'Ẩn chi tiết' : 'Xem chi tiết' }}
              </button>
              <p v-show="showDetails[index]"> {{ product.description }}</p>
          </li>
      </ul>
      <p v-show="isVisible">Thông điệp này được chuyển đổi bởi v-show</p>
  </div>

  <div class="container mb-3">
    <h4>6. Bt của bn</h4>
    <h5>Nhập điểm</h5>
    <input type="number" v-model="score" min="0" max="10">
    <p v-if="score >= 9">Xuất sắc</p>
    <p v-if="score >= 8">Tốt</p>
    <p v-if="score >= 7">Khá</p>
    <p v-if="score >= 6">Trung bình</p>
    <p v-if="score >= 5">Yếu</p>
    <p v-if="score < 5">Kém</p>
  </div>
</template>
