import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/HomeView.vue';
import Login from '@/views/LoginView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})

// 模拟一个简单的登录状态
const isAuthenticated = () => !!localStorage.getItem('userToken')

router.beforeEach((to, from, next) => {
  if (to.name !== 'login' && to.name !== 'register' && !isAuthenticated()) {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
