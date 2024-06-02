import { createRouter, createWebHistory } from 'vue-router'
import Info from '@/views/InfoView/InfoView.vue'
import Chat from '@/views/ChatView/ChatView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'info',
      component: Info
    },
    {
        path: '/chat',
        name: 'chat',
        component: Chat
    }
  ]
})

export default router
