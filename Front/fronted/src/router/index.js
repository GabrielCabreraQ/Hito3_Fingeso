// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: () => import('../vistas/home.vue'), // Usa el archivo home.vue
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
    {
      path: '/arriendo',
      name: 'Arriendo',
      component: () => import('../vistas/arriendo.vue'),
    }
  ],
});

export default router;
