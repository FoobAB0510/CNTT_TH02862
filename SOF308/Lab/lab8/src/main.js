import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/style.css';

const app = createApp(App);
window.isAuthenticated = false;
window.userProfile = null;
app.use(router);
app.mount('#app');
