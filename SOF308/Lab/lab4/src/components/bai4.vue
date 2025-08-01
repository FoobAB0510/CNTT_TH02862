<template>
  <div class="container mt-5">
    <h1 class="text-center">Kiến thức sức khỏe cộng đồng</h1>
    <div class="row">
      <div class="col-sm-4" v-for="(item, idx) in items" :key="idx">
        <div class="card">
          <img :src="item.image" alt="Hình ảnh" />
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

<script setup>
// idk không có tài nguyên thầy ạ
const items = [
  {
    title: "8 loại rau củ quả giàu canxi",
    content: "Canxi là...",
    image: "https://placehold.co/300x200?text=Rau+Củ",
  },
  {
    title: "Các loại gia vị tốt cho sức khỏe",
    content: "Một số loại...",
    image: "https://placehold.co/300x200?text=Gia+Vị",
  },
  {
    title: "9 loại đậu bổ dưỡng nên dùng nhiều",
    content: "Đậu lăng...",
    image: "https://placehold.co/300x200?text=Đậu+Bổ+Dưỡng",
  },
];
</script>
