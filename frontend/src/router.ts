import { createRouter, createWebHistory } from 'vue-router';

import PopularMoviesPage from "./components/pages/PopularMoviesPage.vue";
import NotFound from "./components/molecules/NotFound.vue";
import ProfilePage from "./components/pages/ProfilePage.vue";

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