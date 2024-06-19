<template>
    <el-drawer title="编辑算法库信息" :before-close="cancelForm" direction="ltr">
        <div>
            <el-form :model="algoForm">
                <el-form-item label="算法库名" label-width="80px" :required="true">
                    <el-input v-model="algoForm.title" 
                        placeholder="填写该算法库的名称"
                        maxlength="20"
                        show-word-limit
                        size="large"/>
                </el-form-item>
                <el-form-item label="算法标签" label-width="80px">
                    <el-select
                        v-model="algoForm.tags"
                        multiple
                        filterable
                        allow-create
                        default-first-option
                        :reserve-keyword="false"
                        placeholder="按回车键Enter创建标签"
                        size="large"
                    >
                        <el-option
                            v-for="item in tagOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="算法简介" label-width="80px">
                    <el-input
                        v-model="algoForm.desc"
                        placeholder="填写更全面的相关信息"
                        maxlength="500"
                        show-word-limit
                        type="textarea"
                        :autosize="{ minRows: 5, maxRows: 5 }"
                        size="large"
                    />
                </el-form-item>
                <el-form-item label="原创/转载" :required="true">
                    <el-switch v-model="algoForm.origin"
                    style="--el-switch-off-color: #13ce66"
                    inline-prompt
                    active-text="原创"
                    inactive-text="转载"
                    />
                </el-form-item>
            </el-form>
            <div style="text-align: right;">
                <el-button @click="cancelForm">Cancel</el-button>
                <el-button type="primary" :loading="loading" @click="confirmForm">
                    {{ loading ? 'Submitting ...' : 'Submit' }}
                </el-button>
            </div>
        </div>
    </el-drawer>
</template>

<script setup lang="ts">
import { updateAlgo } from '@/assets/request';
import { ElNotification } from 'element-plus';
import { inject, reactive, ref, watch } from 'vue';

const props = defineProps(['algoEdit'])
const reflesh = inject('reflesh') as Function
const setDialog = inject('setDialog') as Function
const setCurrentAlgo = inject('setCurrentAlgo') as Function
const loading = ref(false)
const algoForm = reactive({
    id: 0,
    title: "",
    content: "",
    line: 0,
    desc: "",
    origin: true,
    tags: []
})

watch(props, (newValue, oldValue)=>{
    algoForm.id = newValue.algoEdit.id
    algoForm.title = newValue.algoEdit.title
    algoForm.content = newValue.algoEdit.content
    algoForm.line = newValue.algoEdit.line
    algoForm.desc = newValue.algoEdit.desc
    algoForm.origin = newValue.algoEdit.origin
    algoForm.tags = newValue.algoEdit.tags
})

const confirmForm = async () => {
    loading.value = true
    const res = await updateAlgo(algoForm)
    setTimeout(() => {
        loading.value = false
        setDialog(false)
        if (res === 'success') {
            ElNotification({
                title: '编辑成功',
                message: '算法库信息修改成功',
                type: 'success'
            })
            reflesh()
            setCurrentAlgo(null)
        }else{
            ElNotification({
                title: '编辑错误',
                message: res,
                type: 'error'
            })
        }
    }, 800)
}

let timer
const cancelForm = () => {
    console.log(props.algoEdit);
    
    loading.value = false
    setDialog(false)
    clearTimeout(timer)
}

const tagOptions = [
    {
        value: '图论',
        label: '图论'
    },
    {
        value: '动态规划',
        label: '动态规划'
    },
    {
        value: '排序算法',
        label: '排序算法'
    },
    {
        value: '贪心算法',
        label: '贪心算法'
    },
    {
        value: '数据结构',
        label: '数据结构'
    },
    {
        value: '数学算法',
        label: '数学算法'
    },
    {
        value: '字符串处理',
        label: '字符串处理'
    }
]
</script>