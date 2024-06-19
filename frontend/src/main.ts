import { createApp } from 'vue'

import { install as VueMonacoEditorPlugin } from '@guolao/vue-monaco-editor'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/theme-chalk/dark/css-vars.css' //暗黑模式

import 'vue-code-layout/lib/vue-code-layout.css'
import CodeLayout from 'vue-code-layout'

import './assets/css/main.scss'

import App from './App.vue'
import router from './router'

const app = createApp(App)
  .use(router)
  .use(CodeLayout)
  .use(ElementPlus)
  .use(VueMonacoEditorPlugin, {
    paths: {
      // The recommended CDN config
      vs: 'https://cdn.jsdelivr.net/npm/monaco-editor@0.43.0/min/vs'
    }
  })
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.mount('#app')
