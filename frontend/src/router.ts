import { createRouter, createWebHistory } from 'vue-router';

import PopularMoviesPage from "./pages/PopularMoviesPage.vue";
import NotFound from "./molecules/NotFound.vue";
import Profile from "./pages/Profile.vue";

const routes = [
    {
        path: '/',
        name: 'popularMoviesPage',
        component: PopularMoviesPage
    },
    {
        path: '/profile',
        name: 'profilePage',
        component: Profile
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