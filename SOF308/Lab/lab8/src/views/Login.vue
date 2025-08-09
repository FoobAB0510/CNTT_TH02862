<template>
  <div class="login-wrapper">
    <div class="form-card">
      <div class="header">
        <h2 class="title">Welcome Back</h2>
        <p class="subtitle">Sign in to your account to continue</p>
      </div>

      <div v-if="!loggedIn" class="body">
        <form @submit.prevent="login" novalidate>
          <div class="input-group" :class="{ filled: username }">
            <input
              id="username"
              v-model.trim="username"
              type="text"
              required
              autocomplete="username"
              :class="{ invalid: usernameError }"
            />
            <label for="username">Username</label>
            <div v-if="usernameError" class="field-error">{{ usernameError }}</div>
          </div>

          <div class="input-group" :class="{ filled: email }">
            <input
              id="email"
              v-model.trim="email"
              type="email"
              required
              autocomplete="email"
              :class="{ invalid: emailError }"
            />
            <label for="email">Email address</label>
            <div v-if="emailError" class="field-error">{{ emailError }}</div>
          </div>

          <button type="submit" class="btn-primary" :disabled="submitting">
            <span v-if="!submitting">Login</span>
            <span v-else class="loader"></span>
          </button>
        </form>
      </div>

      <div v-else class="body">
        <div class="logged-in">
          <p class="success">✅ Logged in as <strong>{{ user.username }}</strong></p>
          <p class="muted">{{ user.email }}</p>
          <div class="actions">
            <router-link :to="{ name: 'Dashboard' }" class="btn-secondary">Dashboard</router-link>
            <button @click="logout" class="btn-outline">Logout</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data() {
    return {
      username: "",
      email: "",
      usernameError: "",
      emailError: "",
      submitting: false,
    };
  },
  computed: {
    loggedIn() {
      return !!window.isAuthenticated;
    },
    user() {
      return window.userProfile || {};
    },
  },
  methods: {
    validate() {
      this.usernameError = "";
      this.emailError = "";
      let ok = true;
      if (!this.username) {
        this.usernameError = "Username is required.";
        ok = false;
      }
      if (!this.email) {
        this.emailError = "Email is required.";
        ok = false;
      } else if (!/^\S+@\S+\.\S+$/.test(this.email)) {
        this.emailError = "Email is invalid.";
        ok = false;
      }
      return ok;
    },
    login() {
      if (!this.validate()) return;
      this.submitting = true;
      setTimeout(() => {
        window.isAuthenticated = true;
        window.userProfile = {
          username: this.username,
          email: this.email,
        };
        this.submitting = false;
        this.$router.push({ name: "Dashboard" });
      }, 500);
    },
    logout() {
      window.isAuthenticated = false;
      window.userProfile = null;
      this.$router.push({ name: "Home" });
    },
  },
  mounted() {
    if (window.userProfile) {
      this.username = window.userProfile.username || "";
      this.email = window.userProfile.email || "";
    }
  },
};
</script>

<style scoped>
*, *::before, *::after {
  box-sizing: border-box;
}

.login-wrapper {
  max-width: 480px;
  margin: 4rem auto;
  padding: 0 0.75rem;
  min-width: 0;
}
.form-card {
  background: white;
  border-radius: 12px;
  padding: 2rem 2rem 2.5rem;
  box-shadow: 0 25px 60px -10px rgba(0, 0, 0, 0.08);
  position: relative;
  overflow: hidden;
  transition: transform .25s ease;
  width: 100%;
}
.form-card:hover {
  transform: translateY(-2px);
}
.header {
  text-align: center;
  margin-bottom: 1rem;
}
.title {
  margin: 0;
  font-size: 1.75rem;
  font-weight: 700;
  color: #2c3e50;
}
.subtitle {
  margin: 0.25rem 0 1rem;
  color: #6b7a93;
  font-size: 0.95rem;
}
.input-group {
  position: relative;
  margin-bottom: 1.25rem;
  min-width: 0;
}
.input-group input {
  width: 100%;
  padding: 0.85rem 1rem;
  border: 1px solid #d8dbe7;
  border-radius: 8px;
  font-size: 1rem;
  background: #f7f9fc;
  outline: none;
  transition: border .2s, box-shadow .2s;
}
.input-group input:focus {
  border-color: #6366f1;
  box-shadow: 0 0 0 3px rgba(99,102,241,0.15);
}
.input-group label {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  background: white;
  padding: 0 6px;
  color: #7a8ca3;
  font-size: 0.85rem;
  pointer-events: none;
  transition: all .2s ease;
}
.input-group.filled label,
.input-group input:focus + label {
  top: -8px;
  font-size: 0.65rem;
  color: #6366f1;
}
.field-error {
  margin-top: 4px;
  font-size: 0.75rem;
  color: #d9534f;
}
.btn-primary {
  width: 100%;
  padding: 0.85rem;
  background: linear-gradient(135deg,#6366f1,#a78bfa);
  border: none;
  color: white;
  font-weight: 600;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  display: inline-flex;
  justify-content: center;
  align-items: center;
}
.btn-primary:disabled {
  opacity: 0.65;
  cursor: not-allowed;
}
.loader {
  width: 18px;
  height: 18px;
  border: 3px solid white;
  border-top-color: rgba(255,255,255,0.4);
  border-radius: 50%;
  animation: spin .8s linear infinite;
}
@keyframes spin {
  to { transform: rotate(360deg); }
}
.logged-in {
  text-align: center;
}
.success {
  margin: 0;
  font-weight: 600;
  color: #1f8e49;
}
.muted {
  margin: 4px 0 12px;
  color: #6b7a93;
}
.actions {
  display: flex;
  gap: 0.75rem;
  justify-content: center;
  flex-wrap: wrap;
}
.btn-secondary {
  background: #eef2f7;
  padding: 0.6rem 1rem;
  border-radius: 8px;
  font-weight: 600;
  text-decoration: none;
  color: #2c3e50;
}
.btn-outline {
  background: transparent;
  border: 2px solid #6366f1;
  padding: 0.6rem 1rem;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  color: #6366f1;
}
.btn-outline:hover {
  background: rgba(99,102,241,0.1);
}

/* responsive tweak */
@media (max-width: 540px) {
  .login-wrapper {
    margin: 2rem 0.5rem;
  }
  .title {
    font-size: 1.5rem;
  }
}
</style>
