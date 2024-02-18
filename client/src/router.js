import { createWebHistory, createRouter } from "vue-router";
import Home from "@/views/Home.vue";
import Posts from "@/views/Posts.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/posts/:param",
    name: "Posts",
    component: Posts,
    beforeEnter: (to, from, next) => {
      if (from.path !== "/") {
        next("/");
      } else {
        next();
      }
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
