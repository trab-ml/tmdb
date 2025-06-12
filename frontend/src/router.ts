import { createRouter, createWebHistory } from 'vue-router';

import PopularMoviesPage from "./pages/PopularMoviesPage.vue";
import NotFound from "./molecules/NotFound.vue";

const routes = [
    {
        path: '/',
        name: 'popularMoviesPage',
        component: PopularMoviesPage
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: NotFound
    }
];

export default createRouter({
    history: createWebHistory(),
    routes
});