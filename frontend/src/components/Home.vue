<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import MomoIcon from './icons/IconMomo.vue'
import SettingIcon from './icons/IconSetting.vue'
import StudentIcon from './icons/IconStudent.vue'
import MessageIcon from './icons/IconMessage.vue'
import DownloadIcon from './icons/IconDownload.vue'
import ListIcon from './icons/IconList.vue'
import ResetIcon from './icons/IconReset.vue'
import LanguageIcon from './icons/IconLanguage.vue'

import { useRouter } from 'vue-router';

const router = useRouter();
const logout = () => {
  localStorage.removeItem('userToken')
  router.push({ name: 'login' })
}
</script>

<template>
    <div id="root" style="height: 80vh; width: 80vw;">
        <div id="header">
            <div id="header__left">
                <MomoIcon class="icon momo" />
                <span id="header__title">MomoTalk</span>
                <RouterLink to="/help" title="Help">
                    <button class="help">?</button>
                </RouterLink>
            </div>
            <div id="header__right">
                <SettingIcon class="icon setting" />
            </div>
        </div>

        <div id="sidebar">
            <div id="sidebar__up">
                <RouterLink to="/" title="Info">
                    <StudentIcon class="icon info" />
                </RouterLink>
                <RouterLink to="/chat" title="Chat">
                    <MessageIcon class="icon message" />
                </RouterLink>
            </div>
            <div id="sidebar__down">
                <div style="cursor: pointer" title="Reset">
                    <ResetIcon class="icon reset" />
                </div>
                <div style="cursor: pointer" title="Download">
                    <DownloadIcon class="icon download" />
                </div>
                <div style="cursor: pointer" title="Switch Language">
                    <LanguageIcon class="icon language" />
                </div>
            </div>
        </div>

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
        <main class="student-info" id="chatcard">
        </main>
    </div>
</template>

<style scoped lang="scss">
@import '@/assets/css/Home.scss';
@import '@/assets/css/icons.scss';
@import '@/assets/css/info-view.scss';
</style>
