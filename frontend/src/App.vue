<script setup>
import MomoIcon from './components/icons/IconMomo.vue'
import SettingIcon from './components/icons/IconSetting.vue'
import StudentIcon from './components/icons/IconStudent.vue'
import MessageIcon from './components/icons/IconMessage.vue'
import DownloadIcon from './components/icons/IconDownload.vue'
import ListIcon from './components/icons/IconList.vue'
import ResetIcon from './components/icons/IconReset.vue'
import LanguageIcon from './components/icons/IconLanguage.vue'
</script>

<template>
    <PlayerDialog></PlayerDialog>
    <SettingDialog></SettingDialog>
    <div id="root">
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
        <RouterView id="chatcard" />
    </div>
</template>

<style scoped lang="scss">
@import './app.scss';
@import '@/assets/css/icons.scss';
</style>
