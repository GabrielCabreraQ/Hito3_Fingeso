import {createRouter,createWebHistory}from'vue-router'

const routes = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'Home',
            component: () => import('../vistas/admin.vue'),
            alias: '/inicio',
        }
    ],
});

export default routes;