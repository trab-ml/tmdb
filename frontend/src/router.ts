import { createRouter, createWebHistory } from 'vue-router';

import BestMoviesPage from "./components/pages/BestMoviesPage.vue";
import NotFound from "./components/molecules/NotFound.vue";
import ProfilePage from "./components/pages/ProfilePage.vue";
import TopRatedMoviesPage from "./components/pages/TopRatedMoviesPage.vue";

const routes = [
    {
        path: '/',
        name: 'bestMoviesPage',
        component: BestMoviesPage
    },
    {
        path: '/top-rated',
        name: 'topRatedMoviesPage',
        component: TopRatedMoviesPage
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