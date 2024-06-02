<script setup>
import StudentIcon from './icons/IconStudent.vue'
import MessageIcon from './icons/IconMessage.vue'
import DownloadIcon from './icons/IconDownload.vue'
import ResetIcon from './icons/IconReset.vue'
import LanguageIcon from './icons/IconLanguage.vue'
</script>

<template>
    <div id="sidebar">
        <div id="sidebar__up">
            <RouterLink to="/" title="Info">
                <StudentIcon class="icon info" />
            </RouterLink>
            <RouterLink to="/chat" title="Chat">
                <MessageIcon class="icon message" />
            </RouterLink>
            <RouterLink to="/chat" title="Chat">
                <ResetIcon class="icon reset" />
            </RouterLink>
        </div>
        <div id="sidebar__down">
            <div style="cursor: pointer" title="Download">
                <DownloadIcon class="icon download" />
            </div>
            <div style="cursor: pointer" title="Switch Language">
                <LanguageIcon class="icon language" />
            </div>
        </div>
    </div>
</template>


<style scoped lang="scss">
@import '@/assets/css/Home.scss';
@import '@/assets/css/icons.scss';
</style>