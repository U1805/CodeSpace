<template>
    <CodeLayout ref="codeLayout" :layout-config="config" :main-menu-config="menuData">
        <template #panelRender="{ panel }">
            <UserInfo
                v-if="panel.name == 'user' && setTitle('用户中心')"
                :user-data="userData"
                style="height: 100%"
            >
            </UserInfo>
            <div v-else-if="panel.name == 'explorer.search' && setTitle('算法库')">
                <el-input
                    v-model="keyword"
                    style="padding: 20px"
                    placeholder="搜索"
                    :prefix-icon="Search"
                />
            </div>
            <div v-else-if="panel.name == 'explorer.algorithms' && setTitle('算法库')">
                <div v-for="repo in algoData" class="repo" @click="openFile(repo)">
                    <el-icon :size="15"><Collection /></el-icon>
                    <span style="font-size: 15px; margin-left: 5px">{{ repo['title'] }}</span>
                </div>
            </div>
            <div v-else-if="panel.name == 'explorer.info' && setTitle('算法库')" class="algo_info">
                <div v-if="currentSelectd">
                    <el-row
                        ><el-col :span="4">算法名称</el-col
                        ><el-col :span="20">{{ currentSelectd['title'] }}</el-col></el-row
                    >
                    <el-row
                        ><el-col :span="4">算法作者</el-col
                        ><el-col :span="20">{{ currentSelectd['author'] }}</el-col></el-row
                    >
                    <el-row
                        ><el-col :span="4">算法简介</el-col
                        ><el-col :span="20">{{ currentSelectd['desc'] }}</el-col></el-row
                    >
                    <el-row
                        ><el-col :span="4">算法标签</el-col
                        ><el-col :span="20">{{
                            currentSelectd['tags'].length ? currentSelectd['tags'].join(', ') : ''
                        }}</el-col></el-row
                    >
                </div>
            </div>
        </template>
        <template #titleBarCenter> {{ title }} </template>
        <template #centerArea>
            <slot name="center1" v-if="title == '用户中心'">
                <UploadView @reflesh="reflesh"></UploadView>
            </slot>
            <slot name="center2" v-else>
                <SplitLayout
                    ref="splitLayout"
                    @panelClose="onPanelClose"
                    @canLoadLayout="loadInnerLayout"
                    @click="onPanelClick"
                >
                    <template #tabContentRender="{ panel }">
                        <div @click="currentSelectd = panel.data.repo" style="height: 100%">
                            <vue-monaco-editor
                                v-model:value="panel.data.value"
                                :language="panel.data.language"
                                :path="panel.data.path"
                                theme="vs-dark"
                                :options="MONACO_EDITOR_OPTIONS"
                            />
                        </div>
                    </template>
                </SplitLayout>
            </slot>
        </template>
        <template #titleBarIcon>
            <img src="../assets/images/logo.svg" width="20px" style="margin: 0 10px 0 13px" />
        </template>
        <template #titleBarRight>
            <span style="width: 210px"></span>
            <el-button :icon="Close" size="small" text id="quit_btn" @click="logout" />
        </template>
        <template #statusBar>
            <span>Welcome to CodeSpace-DevNet(CSDN) !</span>
        </template>
    </CodeLayout>
</template>

<script setup lang="ts">
import { loadLayout, config, menuData } from '@/assets/layout'
import { ref, onMounted, nextTick, h, watch } from 'vue'
import {
    CodeLayout,
    SplitLayout,
    type CodeLayoutSplitNInstance,
    CodeLayoutSplitNGridInternal,
    CodeLayoutInstance,
    CodeLayoutPanelInternal
} from 'vue-code-layout'
import { Close, Search } from '@element-plus/icons-vue'
import UserInfo from '../components/UserInfo.vue'
import UploadView from '../components/UploadView.vue'

// =====初始化布局=====
const title = ref<string>('用户中心')
const codeLayout = ref<CodeLayoutInstance>()
const MONACO_EDITOR_OPTIONS = {
    automaticLayout: true,
    formatOnType: true,
    formatOnPaste: true
}
const splitLayout = ref<CodeLayoutSplitNInstance>()
const split = ref<CodeLayoutSplitNGridInternal>()
const groups = ref<CodeLayoutPanelInternal[]>()
function loadInnerLayout() {
    if (splitLayout.value) {
        const grid = splitLayout.value.getRootGrid()
        split.value = grid.addGrid({
            name: 'split1'
        })
    }
}
onMounted(() => {
    nextTick(async () => {
        groups.value = loadLayout(codeLayout.value)
        const username = localStorage.getItem('userToken')
        userData.value = await getUser(username)
        setBadge(userData.value.repo.toString())
        algoData.value = await search('', username)
    })
})
// =====初始化布局结束======

const userData = ref<User>()
const keyword = ref<string>()
const currentSelectd = ref()
const algoData = ref<Algo[]>()

const showBottom = (flag: boolean) => {
    config.bottomPanel = flag
    return true
}

const setBadge = (message: string) => {
    const groupExplorer = codeLayout.value.getPanelByName('explorer')
    groupExplorer.badge = message
}

const setTitle = (new_title: string) => {
    title.value = new_title
    return true
}

import IconMarkdown from '../components/icons/IconMarkdown.vue'
import { Algo, User } from '../assets/interface'
import { getUser, search } from '@/assets/request'
import router from '@/router'
const file_opened = ref(0)
const file_cnt = ref(0)
const openFile = (repo) => {
    if (split.value.children.findIndex((panel) => panel.data.repo == repo) != -1) return

    const t = split.value.addPanel({
        title: repo['title'],
        name: 'file' + file_cnt.value++,
        iconSmall: () => h(IconMarkdown),
        data: { value: repo['content'], language: repo['language'], repo: repo },
        closeType: 'close'
    })

    t.activeSelf()
    file_opened.value++
    currentSelectd.value = repo
}
const onPanelClose = (panel: CodeLayoutPanelInternal, resolve: () => void) => {
    if (file_opened.value > 1) {
        resolve()
        file_opened.value--
    }
}
const onPanelClick = () => {
    // 点击标签切换算法信息
    currentSelectd.value = split.value.activePanel.data.repo
}

const logout = () => {
    localStorage.removeItem('userToken')
    router.push('/login')
}

const reflesh = async () => {
    const username = localStorage.getItem('userToken')
    userData.value = await getUser(username)
    setBadge(userData.value.repo.toString())
    algoData.value = await search('', username)
}

watch(keyword, async (newValue, oldValue) => {
    if (newValue == '') {
        const username = localStorage.getItem('userToken')
        algoData.value = await search('', username)
    } else {
        algoData.value = await search(keyword.value, '')
    }
})
</script>

<style lang="scss">
.code-layout-root > .code-layout-activity .code-layout-activity-bar {
    width: 60px;

    & .item {
        width: 60px;
        height: 60px;
    }
}

.top {
    width: 60px;

    & .code-layout-tooltip-ref {
        height: 60px;
    }
}

.code-layout-root > .code-layout-activity .code-layout-activity-bar .item .badge {
    right: 8px;
    bottom: 8px;
}

.code-layout-panel .collapse {
    height: 50px;
}

.repo {
    padding: 5px 10px;
}

.algo_info .el-row {
    margin-bottom: 10px;
    margin-left: 20px;
}

#quit_btn {
    margin-right: 10px;
    color: white;
    font-size: large;
    --el-fill-color-light: #04395e;
    --el-fill-color: #04395e;
}
</style>
