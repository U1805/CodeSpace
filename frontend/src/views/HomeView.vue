<template>
    <CodeLayout ref="codeLayout" :layout-config="config" :main-menu-config="menuData">
        <template #panelRender="{ panel }">
            <UserInfo
                v-if="panel.name == 'loginInfo' && setTitle('用户中心')"
                :loginInfo-data="userData"
                style="height: 100%"
            >
            </UserInfo>
            <div v-else-if="panel.name == 'explorer.search' && setTitle('算法库')">
                <el-input
                    v-model="search"
                    style="padding: 20px"
                    placeholder="搜索"
                    :prefix-icon="Search"
                />
            </div>
            <div v-else-if="panel.name == 'explorer.algorithms' && setTitle('算法库')">
                <div v-for="repo in tableData" class="repo" @click="openFile(repo)">
                    <el-icon :size="15"><Collection /></el-icon>
                    <span style="font-size: 15px; margin-left: 5px">{{ repo['name'] }}</span>
                </div>
            </div>
            <div v-else-if="panel.name == 'explorer.info' && setTitle('算法库')" class="algo_info">
                <div v-if="currentSelectd">
                    <el-row
                        ><el-col :span="4">算法名称</el-col
                        ><el-col :span="20">{{ currentSelectd['name'] }}</el-col></el-row
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
                        ><el-col :span="20">{{ currentSelectd['tags'].join(', ') }}</el-col></el-row
                    >
                </div>
            </div>
        </template>
        <template #titleBarCenter> {{ title }} </template>
        <template #centerArea>
            <slot name="center1" v-if="title == '用户中心'">
                <UploadView></UploadView>
            </slot>
            <slot name="center2" v-else>
                <SplitLayout
                    ref="splitLayout"
                    @panelClose="onPanelClose"
                    @canLoadLayout="loadInnerLayout"
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
        </template>
        <template #statusBar>
            <span>Welcome to CodeSpace-DevNet(CSDN) !</span>
        </template>
    </CodeLayout>
</template>

<script setup lang="ts">
import { loadLayout, config, menuData } from '@/assets/layout'
import { ref, onMounted, nextTick, h, VueElement, DefineComponent, reactive, watch } from 'vue'
import {
    CodeLayout,
    SplitLayout,
    type CodeLayoutSplitNInstance,
    CodeLayoutSplitNGridInternal,
    CodeLayoutInstance,
    CodeLayoutPanelInternal
} from 'vue-code-layout'
import { Calendar, Search } from '@element-plus/icons-vue'
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
    nextTick(() => {
        groups.value = loadLayout(codeLayout.value)
        // groups.value[1].activeSelf()
    })
})
// =====初始化布局结束======

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
const file_opened = ref(0)
const openFile = (repo) => {
    split.value.addPanel({
        title: repo['name'],
        name: 'file' + file_opened.value,
        iconSmall: () => h(IconMarkdown),
        data: { value: repo['content'], language: repo['language'], repo: repo },
        closeType: 'close'
    })
    file_opened.value++
    currentSelectd.value = repo
}
function onPanelClose(panel: CodeLayoutPanelInternal, resolve: () => void) {
    if (file_opened.value > 1) {
        resolve()
        file_opened.value--
    }
}

const userData = ref({
    userName: 'Lee Ji Eun',
    userEmail: '123@email.com',
    userAvatar: 'https://img.moegirl.org.cn/common/7/7a/Name_kaho_icon_v2.png',
    userInfo: {
        repo: 190,
        score: 240,
        line: '7.3k'
    }
})

const search = ref<string>()
const currentSelectd = ref()

const tableData = [
    {
        author: '2016-05-03',
        name: 'Tom',
        language: 'javascript',
        desc: '',
        tags: ['javascript', 'bb', 'cc'],
        content:
            'No. 189, Grove St, Los Angeles\nrove St, Los Angeles\nrove St, Los Angeles\nrove St, Los Angeles'
    },
    {
        author: '2016-05-02',
        name: 'Tom',
        language: 'python',
        desc: 'Tom',
        tags: ['python', 'bb', 'cc'],
        content: 'No. 189, Grove St, Los Angeles'
    },
    {
        author: '2016-05-04',
        name: 'Tom',
        language: 'c',
        desc: '6-05-0',
        tags: ['c', 'bb', 'cc'],
        content: 'No. 189, Grove St, Los Angeles'
    },
    {
        author: '2016-05-01',
        name: 'Tom',
        language: 'cpp',
        desc: '',
        tags: ['cpp', 'bb', 'cc'],
        content: 'No. 189, Grove St, Los Angeles'
    }
]
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
</style>
