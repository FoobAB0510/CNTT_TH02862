<template>
  <div class="app">
    <header class="navbar">
      <div class="nav-brand">
        <h1>MyBlog</h1>
      </div>
      <nav class="nav-links">
        <router-link to="/">Home</router-link>
        <router-link to="/blog">Blog</router-link>
        <router-link to="/profile/info">Profile</router-link>
        <router-link to="/dashboard">Dashboard</router-link>
        <router-link to="/login">Login</router-link>
      </nav>
    </header>
    <main class="main-content">
      <router-view />
    </main>
    <footer class="footer">
      <p>© 2025 MyBlog</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: "AppRoot",
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
  line-height: 1.6;
  color: #333;
  background-color: #f8f9fa;
}

.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.navbar {
  background: white;
  border-bottom: 1px solid #e9ecef;
  padding: 0.5rem 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.nav-brand h1 {
  font-size: 1.2rem;
  color: #495057;
  font-weight: 600;
}

.nav-links {
  display: flex;
  gap: 1rem;
}

.nav-links a {
  text-decoration: none;
  color: #6c757d;
  font-size: 0.9rem;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  transition: all 0.2s;
}

.nav-links a:hover,
.nav-links a.router-link-active {
  color: #007bff;
  background-color: #f8f9fa;
}

.main-content {
  flex: 1;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
  padding: 1rem;
}

.footer {
  background: white;
  border-top: 1px solid #e9ecef;
  padding: 1rem;
  text-align: center;
}

.footer p {
  color: #6c757d;
  font-size: 0.875rem;
}

.page {
  background: white;
  border-radius: 6px;
  padding: 1.5rem;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.page h2 {
  margin-bottom: 1rem;
  color: #495057;
  font-size: 1.5rem;
  font-weight: 600;
}
</style>
