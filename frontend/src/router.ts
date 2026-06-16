export const routes = [
  {
    path: "/",
    name: "bestMoviesPage",
    component: () => import("./components/pages/BestMoviesPage.vue"),
  },
  {
    path: "/top-rated",
    name: "topRatedMoviesPage",
    component: () => import("./components/pages/TopRatedMoviesPage.vue"),
  },
  {
    path: "/profile",
    name: "profilePage",
    component: () => import("./components/pages/ProfilePage.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    name: "NotFound",
    component: () => import("./components/molecules/NotFound.vue"),
  },
];
