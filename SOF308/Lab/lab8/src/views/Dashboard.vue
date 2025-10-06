<template>
  <section class="page dashboard">
    <h2>Bảng điều khiển</h2>
    <p>
      Chào mừng đến với bảng điều khiển của bạn. Đây là tổng quan thống kê blog
      của bạn:
    </p>
    <div class="stats-grid">
      <div class="stat-card">
        <h4>Tổng bài viết</h4>
        <p class="stat-number">12</p>
      </div>
      <div class="stat-card">
        <h4>Người theo dõi</h4>
        <p class="stat-number">358</p>
      </div>
      <div class="stat-card">
        <h4>Bình luận</h4>
        <p class="stat-number">1,243</p>
      </div>
      <div class="stat-card">
        <h4>Lượt xem</h4>
        <p class="stat-number">28,531</p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "TrangBangDieuKhien",
};
</script>

<style scoped>
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}

.stat-card {
  background: white;
  border: 1px solid #e9ecef;
  border-radius: 6px;
  padding: 1rem;
  text-align: center;
}

.stat-card h4 {
  margin: 0 0 0.5rem 0;
  color: #6c757d;
  font-size: 0.9rem;
  font-weight: 500;
}

.stat-number {
  margin: 0;
  font-size: 1.8rem;
  font-weight: 600;
  color: #495057;
}
</style>
