<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import ListIcon from '@/components/icons/IconList.vue'
import HeaderBar from '@/components/HeaderBar.vue'
import SideBar from '@/components/SideBar.vue'
</script>

<template>
    <div id="root">
        <HeaderBar></HeaderBar>
        <SideBar></SideBar>

        <div id="listcard">
            <div id="listcard__header">
                <div class="search-group">
                    <input type="text" placeholder="Type / to search" class="search-group__text" id="searchBox" />
                </div>
                <div class="student-list__button" title="Switch Student List">
                    <ListIcon class="icon list" />
                </div>
            </div>
            <div id="listbody">
                <div class="list-item" v-for="i in [1,2,3]" :key="i">list {{ i }}
                </div>
            </div>
        </div>
        <main class="talk-wrapper" id="chatcard">
            <!-- 聊天主界面 -->
            <div class="talk-list" id="talkList">
            </div>
            <!-- 聊天主界面 -->
        </main>
    </div>
</template>

<style scoped lang="scss">
@import '@/assets/css/Home.scss';
@import '@/assets/css/icons.scss';
@import '@/assets/css/info-view.scss';
@import '@/assets/css/chat-view.scss';
</style>
