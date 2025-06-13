import { createRouter, createWebHistory } from 'vue-router';

import PopularMoviesPage from "./pages/PopularMoviesPage.vue";
import NotFound from "./molecules/NotFound.vue";
import ProfilePage from "./pages/ProfilePage.vue";

const routes = [
    {
        path: '/',
        name: 'popularMoviesPage',
        component: PopularMoviesPage
    },
    {
        path: '/profile',
        name: 'profilePage',
        component: ProfilePage
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