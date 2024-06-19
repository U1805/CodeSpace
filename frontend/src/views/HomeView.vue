<template>
    <UpdateAlgo v-model="dialog" :algoEdit="algoEdit"></UpdateAlgo>

    <CodeLayout ref="codeLayout" :layout-config="config" :main-menu-config="menuData">
        <template #panelRender="{ panel }">
            <UserInfo
                v-if="panel.name == 'user' && setTitle('用户中心')"
                :user-data="userData"
                :isAdmin="isAdmin"
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
                <div
                    v-for="repo in algoData"
                    class="repo"
                    @click="openFile(repo)"
                    style="display: flex; justify-content: space-between"
                >
                    <span>
                        <el-icon :size="15"><Collection /></el-icon>
                        <span style="font-size: 15px; margin: 5px">{{ repo['title'] }}</span>
                        <el-tag>{{ repo['author'] }}</el-tag>
                    </span>
                    <span>
                        <el-button
                            v-if="repo['author'] == userData.username"
                            @click="handleEdit(repo)"
                            @click.stop=""
                            >编辑</el-button
                        >
                        <el-button
                            v-if="repo['author'] == userData.username"
                            @click="handleDelete(repo)"
                            @click.stop=""
                            >删除</el-button
                        >
                    </span>
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
                        ><el-col :span="20">
                            <el-tag style="margin-right: 10px">{{
                                currentSelectd['origin'] ? '原创' : '转载'
                            }}</el-tag>
                            <el-tag
                                style="margin-right: 10px"
                                v-for="tag in currentSelectd['tags']"
                                >{{ tag }}</el-tag
                            >
                        </el-col>
                    </el-row>
                    <el-row
                        ><el-col :span="4">创建时间</el-col
                        ><el-col :span="20">{{ currentSelectd['createTime'] }}</el-col></el-row
                    >
                    <el-row
                        ><el-col :span="4">修改时间</el-col
                        ><el-col :span="20">{{ currentSelectd['updateTime'] }}</el-col></el-row
                    >
                </div>
            </div>
            <ControlInfo
                v-else-if="panel.name == 'control' && setTitle('管理中心')"
                class="algo_info"
            >
            </ControlInfo>
        </template>
        <template #titleBarCenter> {{ title }} </template>
        <template #centerArea>
            <slot name="center1" v-if="title == '用户中心'">
                <UploadView @reflesh="reflesh"></UploadView>
            </slot>
            <slot name="center2" v-else-if="title == '算法库'">
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
            <slot name="center2" v-else-if="title == '管理中心'">
                <ControlAlgo :username="currentSelectedUser"></ControlAlgo>
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
import { ref, onMounted, nextTick, h, watch, provide } from 'vue'
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
import UpdateAlgo from '../components/UpdateAlgo.vue'
import ControlInfo from '../components/ControlInfo.vue'
import ControlAlgo from '../components/ControlAlgo.vue'

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
        iden() // 标记用户身份
        groups.value = loadLayout(codeLayout.value, isAdmin.value)
        const username = localStorage.getItem('username')
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
const isAdmin = ref<boolean>(false)
const currentSelectedUser = ref<string>() //  管理界面选中的用户

const iden = () => {
    isAdmin.value = localStorage.getItem('userToken').startsWith('aw')
}

const setCurrentUser = (username: string) => {
    currentSelectedUser.value = username
}
provide('setUser', setCurrentUser)

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
import { deleteAlgo, getUser, search } from '@/assets/request'
import router from '@/router'
import { ElMessage, ElMessageBox } from 'element-plus'
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
    localStorage.removeItem('username')
    localStorage.removeItem('userToken')
    router.push('/login')
}

const reflesh = async () => {
    const username = localStorage.getItem('username')
    userData.value = await getUser(username)
    setBadge(userData.value.repo.toString())
    if (keyword.value) {
        algoData.value = await search(keyword.value, '')
    } else {
        const username = localStorage.getItem('username')
        algoData.value = await search('', username)
    }
}

provide('reflesh', reflesh)

watch(keyword, async (newValue, oldValue) => {
    if (newValue == '') {
        const username = localStorage.getItem('username')
        algoData.value = await search('', username)
    } else {
        algoData.value = await search(keyword.value, '')
    }
})

// 算法库删除与编辑操作
const handleDelete = (algo: Algo) => {
    ElMessageBox.confirm('删除算法库的操作不可逆，是否继续？', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    })
        .then(async () => {
            const res = await deleteAlgo(algo.id)
            if (res === 'success') {
                ElMessage({
                    type: 'success',
                    message: '删除成功'
                })
            } else {
                ElMessage({
                    type: 'error',
                    message: res
                })
            }
            reflesh()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除'
            })
        })
}

const dialog = ref(false)
const algoEdit = ref<Algo>()
const handleEdit = (algo: Algo) => {
    algoEdit.value = algo
    dialog.value = true
}

const setDialog = (flag: boolean) => {
    dialog.value = flag
}
provide('setDialog', setDialog)
const setCurrentAlgo = (algo: Algo) => {
    currentSelectd.value = algo
}
provide("setCurrentAlgo", setCurrentAlgo)
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

html.dark {
    /* 自定义深色背景颜色 */
    --el-color-danger-light-9: #fef0f0;
    --el-border-color: #31547b;
}
</style>
