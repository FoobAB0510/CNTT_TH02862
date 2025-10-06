<template>
  <div class="info-box">
    <h3>🔍 Thông Tin Người Dùng</h3>
    <p><strong>Tên:</strong> {{ user?.username }}</p>
    <p><strong>Email:</strong> {{ user?.email }}</p>
  </div>
</template>

<script>
export default {
  name: "UserProfileInfoPage",
  data() {
    return {
      user: window.userProfile || null,
    };
  },
};
</script>
