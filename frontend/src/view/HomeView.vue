<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import HeaderBar from '@/components/HeaderBar.vue'
import SideBar from '@/components/SideBar.vue'

// import { useRouter } from 'vue-router';
// 
// const router = useRouter();
// const logout = () => {
//   localStorage.removeItem('userToken')
//   router.push({ name: 'login' })
// }
</script>

<template>
    <div id="root">
        <HeaderBar></HeaderBar>
        <SideBar></SideBar>

        <div id="listcard">
        </div>
        <main class="student-info" id="chatcard">
        </main>
    </div>
</template>

<style scoped lang="scss">
@import '@/assets/css/Home.scss';
@import '@/assets/css/icons.scss';
@import '@/assets/css/info-view.scss';
</style>
