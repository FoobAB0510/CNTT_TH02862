<template>
  <div class="info-box">
    <h3>🔍 User Info</h3>
    <p><strong>Name:</strong> {{ user?.username }}</p>
    <p><strong>Email:</strong> {{ user?.email }}</p>
  </div>
</template>

<script>
export default {
  name: "UserProfileInfoPage",
  data() {
    return {
      user: window.userProfile || null
    };
  }
};
</script>