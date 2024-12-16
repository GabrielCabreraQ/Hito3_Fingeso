// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../vistas/home.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home, // Usa el archivo home.vue
      alias: '/inicio',
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../vistas/admin.vue'),
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../vistas/login.vue'),
    },
    {
      path: '/registerArrendatario',
      name: 'Registro Arrendatario',
      component: () => import('../vistas/registerArrendatario.vue'),
    },
  ],
});

export default router;
