<script setup>
import MomoIcon from './icons/IconMomo.vue'
import SettingIcon from './icons/IconSetting.vue'
</script>

<template>
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
</template>

<style scoped lang="scss">
@import '@/assets/css/Home.scss';
@import '@/assets/css/icons.scss';
</style>