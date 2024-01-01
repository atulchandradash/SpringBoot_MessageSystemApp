import { createRouter, createWebHistory } from 'vue-router'
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import CreateMsgView from "@/views/CreateMsgView.vue";
import ReciviedMsgView from "@/views/ReciviedMsgView.vue";
import SentMsg from "@/views/SentMsg.vue";



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'createmsg',
      component: CreateMsgView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/recMsg',
      name: 'recMsg',
      component: ReciviedMsgView
    },
    {
      path: '/sentMsg',
      name: 'sentMsg',
      component: SentMsg
    },

  ]
})

router.beforeEach((to, from, next) => {

  const isAuthenticated = localStorage.getItem('token');


  if (to.path === '/login' || to.path === '/register') {
    next();
    return;
  }


  if (!isAuthenticated) {
    next('/login');
    return;
  }


  next();
});

export default router
