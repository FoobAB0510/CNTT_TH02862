<template>
  <div class="settings-wrapper">
    <div class="settings-card">
      <div class="header">
        <h3 class="title">Update Profile</h3>
        <p class="subtitle">Change your username or email below.</p>
      </div>
      <div v-if="user">
        <form @submit.prevent="save" novalidate>
          <div class="input-group" :class="{ filled: form.username }">
            <input id="username" v-model.trim="form.username" required />
            <label for="username">Username</label>
          </div>
          <div class="input-group" :class="{ filled: form.email }">
            <input id="email" v-model.trim="form.email" required type="email" />
            <label for="email">Email</label>
          </div>
          <div class="actions">
            <button type="submit" class="btn-save" :disabled="saving">
              <span v-if="!saving">Save changes</span>
              <span v-else class="loader-small"></span>
            </button>
            <div v-if="message" class="feedback">{{ message }}</div>
          </div>
        </form>
      </div>
      <div v-else class="empty">
        <p>Please log in to edit your profile.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfileSettings",
  data() {
    return {
      form: {
        username: window.userProfile?.username || "",
        email: window.userProfile?.email || "",
      },
      saving: false,
      message: "",
    };
  },
  computed: {
    user() {
      return window.userProfile || null;
    },
  },
  methods: {
    save() {
      if (!this.form.username || !this.form.email) return;
      if (!/^\S+@\S+\.\S+$/.test(this.form.email)) {
        this.message = "Email không hợp lệ";
        return;
      }
      this.saving = true;
      setTimeout(() => {
        window.userProfile = {
          username: this.form.username,
          email: this.form.email,
        };
        this.message = "Thông tin đã được cập nhật!";
        this.saving = false;
        this.$emit("updated");
        setTimeout(() => (this.message = ""), 2000);
      }, 400);
    },
  },
};
</script>

<style scoped>
.settings-wrapper {
  max-width: 600px;
  margin: 2rem auto;
}
.settings-card {
  background: white;
  border-radius: 12px;
  padding: 1.75rem 2rem;
  box-shadow: 0 25px 50px -10px rgba(0,0,0,0.08);
  position: relative;
}
.header {
  margin-bottom: 1rem;
}
.title {
  margin: 0;
  font-size: 1.35rem;
  font-weight: 700;
  color: #2c3e50;
}
.subtitle {
  margin: 4px 0 0;
  font-size: 0.9rem;
  color: #6b7a93;
}
.input-group {
  position: relative;
  margin-bottom: 1.2rem;
}
.input-group input {
  width: 100%;
  padding: 0.8rem 1rem;
  border: 1px solid #d8dbe7;
  border-radius: 8px;
  font-size: 1rem;
  background: #f7f9fc;
  outline: none;
  transition: border .2s, box-shadow .2s;
}
.input-group input:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52,152,219,0.15);
}
.input-group label {
  position: absolute;
  left: 12px;
  top: 50%;
  transform: translateY(-50%);
  background: white;
  padding: 0 6px;
  font-size: 0.75rem;
  color: #7a8ca3;
  pointer-events: none;
  transition: all .2s ease;
}
.input-group.filled label,
.input-group input:focus + label {
  top: -8px;
  font-size: 0.65rem;
  color: #3498db;
}
.actions {
  display: flex;
  align-items: center;
  gap: 1rem;
  flex-wrap: wrap;
  margin-top: 0.5rem;
}
.btn-save {
  background: linear-gradient(135deg,#3498db,#6366f1);
  border: none;
  padding: 0.85rem 1.5rem;
  color: white;
  font-weight: 600;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
  min-width: 140px;
}
.btn-save:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
.feedback {
  font-size: 0.9rem;
  color: #1f8e49;
}
.loader-small {
  width: 16px;
  height: 16px;
  border: 2px solid white;
  border-top-color: rgba(255,255,255,0.4);
  border-radius: 50%;
  animation: spin .8s linear infinite;
}
@keyframes spin {
  to { transform: rotate(360deg); }
}
.empty {
  padding: 1rem;
  background: #fff3cd;
  border: 1px solid #ffeeba;
  border-radius: 8px;
  color: #856404;
}
</style>
