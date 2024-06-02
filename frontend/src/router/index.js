import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue';
import Chat from '@/components/Chat.vue'

import Login from '@/components/Login.vue';
import Register from '@/components/Register.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
        path: '/chat',
        name: 'chat',
        component: Chat
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    }
  ]
})

// // 模拟一个简单的登录状态
// const isAuthenticated = () => !!localStorage.getItem('userToken')

// router.beforeEach((to, from, next) => {
//   if (to.name !== 'login' && to.name !== 'register' && !isAuthenticated()) {
//     next({ name: 'login' })
//   } else {
//     next()
//   }
// })

export default router
