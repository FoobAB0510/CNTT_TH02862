import { createrRouter, createWebHistory } from 'vue-router';
//import các view
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import ContactView from '../views/ContactView.vue';

const routers = [
  { path: '/', componment: HomeView, name: 'home' },
  { path: '/about', componment: AboutView, name: 'about' },
  { path: '/contact', componment: ContactView, name: 'contact' },
]

const router = createrRouter({
  history: createWebHistory(), routers
})
