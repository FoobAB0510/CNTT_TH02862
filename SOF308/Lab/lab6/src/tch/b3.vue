<template>
  <div class="container mt-5">
    <h1 class="text-center">Kiến thức sức khỏe cộng đồng</h1>
    <div class="row">
      <div class="col-sm-4 mb-4" v-for="(item, index) in items" :key="index">
        <div class="card">
          <img :src="item.image" class="img-fluid" alt="Hình ảnh" />
          <div class="card-body">
            <h3 class="card-title">{{ item.title }}</h3>
            <p class="card-text">{{ item.content }}</p>
            <button class="btn btn-info">Xem chi tiết</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const items = ref([
  {
    image: 'https://placehold.co/300x200',
    title: '8 loại rau củ có quá giàu canxi',
    content: 'Canxi là khoáng chất cần thiết đối với cơ thể người. Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất, có 8 loại rau củ giàu canxi...',
  },
  {
    image: 'https://placehold.co/300x200',
    title: 'Các loại gia vị tốt cho sức khỏe',
    content: 'Một số loại gia vị cung cấp nhiều polyphenol chống oxy hóa cao hơn các loại gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm và vị hấp dẫn...',
  },
  {
    image: 'https://placehold.co/300x200',
    title: '9 loại đậu bổ dưỡng nên dùng nhiều',
    content: 'Đậu lăng, đậu nành, đậu phồng, đậu Hà Lan giàu chất xơ, protein cùng nhiều vitamion và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim...',
  },
])
</script>

<style scoped>
.container {
  max-width: 1200px;
}

.card {
  transition: transform 0.2s;
}

.card:hover {
  transform: scale(1.05);
}
</style>
