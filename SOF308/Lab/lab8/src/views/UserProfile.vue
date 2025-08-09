<template>
  <section class="page user-profile">
    <h2>👤 User Profile</h2>

    <div v-if="!isLoggedIn" class="profile-section">
      <p>⚠️ No user information found. Please login first.</p>
      <router-link :to="{ name: 'LoginPage' }" class="btn-link">Go to Login</router-link>
    </div>

    <div v-else>
      <div class="tabs">
        <router-link
          :to="{ name: 'UserProfileInfo' }"
          class="tab"
          active-class="active"
        >
          🔍 Info
        </router-link>
        <router-link
          :to="{ name: 'UserProfileSettings' }"
          class="tab"
          active-class="active"
        >
          ⚙️ Settings
        </router-link>
      </div>
      <div class="tab-content">
        <router-view />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "UserProfilePage",
  computed: {
    isLoggedIn() {
      return !!window.isAuthenticated;
    },
  },
};
</script>

<style scoped>
.user-profile {
  max-width: 800px;
  margin: 2rem auto;
}
.profile-section {
  padding: 1rem 1.25rem;
  background: #f8f9fa;
  border-left: 4px solid #3498db;
  border-radius: 6px;
  margin-bottom: 1rem;
}
.tabs {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}
.tab {
  padding: 0.5rem 1rem;
  border-radius: 8px;
  background: #eef2f7;
  text-decoration: none;
  color: #2c3e50;
  font-weight: 600;
  transition: all .2s;
}
.tab.active {
  background: linear-gradient(135deg,#6366f1,#a78bfa);
  color: white;
  box-shadow: 0 10px 25px -5px rgba(99,102,241,0.4);
}
.tab-content {
  margin-top: 0.5rem;
}
.btn-link {
  display: inline-block;
  margin-top: 6px;
  color: #6366f1;
  font-weight: 600;
  text-decoration: none;
}
.btn-link:hover {
  text-decoration: underline;
}
</style>
