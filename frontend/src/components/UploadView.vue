<template>
    <div
        style="
            display: flex;
            justify-content: center;
            flex-direction: column;
            padding: 50px;
            height: 100%;
            font-size: large;
        "
    >
        <el-row>
            <h2>上传算法库</h2>
        </el-row>
        <el-form :model="form" :rules="rules" ref="ruleFormRef" status-icon>
            <el-form-item label="文件上传" :required="true">
                <el-upload
                    class="upload-demo"
                    drag
                    multiple
                    action="#"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :auto-upload="false"
                    :on-change="handleFileUpload"
                    :on-remove="handleFileUpload"
                    ref="upload"
                    style="width: 100%"
                >
                    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                    <div class="el-upload__text">Drop file here or <em>click to upload</em></div>
                    <template #tip>
                        <div class="el-upload__tip" style="text-align: right">
                            files with a size less than 5MB
                        </div>
                    </template>
                </el-upload>
            </el-form-item>
            <el-form-item label="算法库名" :required="true">
                <el-input
                    v-model="form.title"
                    placeholder="填写该算法库的名称"
                    maxlength="20"
                    show-word-limit
                    size="large"
                />
            </el-form-item>
            <el-form-item label="算法标签">
                <el-select
                    v-model="form.tags"
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
            <el-form-item label="算法简介">
                <el-input
                    v-model="form.desc"
                    placeholder="填写更全面的相关信息"
                    maxlength="500"
                    show-word-limit
                    type="textarea"
                    :autosize="{ minRows: 5, maxRows: 5 }"
                    size="large"
                />
            </el-form-item>
            <el-form-item label="当前行数">
                {{ form.line }}
            </el-form-item>
            <el-form-item label="实现语言">
                {{ form.language }}
            </el-form-item>
            <el-form-item label="原创/转载" :required="true">
                <el-switch v-model="form.origin"
                style="--el-switch-off-color: #13ce66"
                inline-prompt
                active-text="原创"
                inactive-text="转载"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">上传</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang="ts">
import { uploadAlgo } from '@/assets/request'
import { RuleForm, rules } from '@/assets/uploadRule'
import { FormInstance, UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
import { reactive, ref } from 'vue'
import { ElNotification } from 'element-plus'

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

const allow_type = ['js', 'c', 'cpp', 'cs', 'java', 'py', 'go', 'txt']

const upload = ref<UploadInstance>()
const handleExceed: UploadProps['onExceed'] = (files) => {
    upload.value!.clearFiles()
    const file = files[0] as UploadRawFile
    upload.value!.handleStart(file)
}

const handleFileUpload = (file, upload_files) => {
    form.content = ''
    form.line = 0
    upload_files.forEach((file) => {
        const ext = file.raw.name.split('.')[file.raw.name.split('.').length - 1]
        if (allow_type.findIndex((ele) => ele == ext) != -1) {
            form.language = ext
            countTextFile(file.raw, ext)
        }else{
            upload.value!.clearFiles()
            ElNotification({
                title: 'Info',
                dangerouslyUseHTMLString: true,
                message: '目前不支持该类型实现文件。<br/>支持实现语言：<br/>C、C++、C#、Java、JavaScript、Golang、Python、伪代码',
                type: 'info',
            })
        }
    })
}

const countTextFile = (file, ext) => {
    const reader = new FileReader()
    reader.onload = () => {
        const content = reader.result as string
        form.title = file.name.split('.')[0]
        // 这里可以实现文本文件的统计逻辑
        const wordCount = content.split(/\s+/).length
        const charCount = content.length
        const lineCount = content.split(/\n/).length
        ;(form.content = content), (form.line = lineCount)
    }
    reader.readAsText(file)
}

const username = localStorage.getItem('username')
const form = reactive<RuleForm>({
    title: '',
    content: '',
    author: username,
    tags: [],
    desc: '',
    line: 0,
    origin: true,
    language: '无'
})

const ruleFormRef = ref<FormInstance>()
const emit = defineEmits(['reflesh'])
const onSubmit = async () => {
    await ruleFormRef.value.validate(async (valid, fields) => {
        if (valid) {
            const score = await uploadAlgo(form)
            if (score === -1){
                ElNotification({
                    title: 'Error',
                    message: '上传失败，缺少必要信息',
                    type: 'error',
                })
            }else{
                ElNotification({
                    title: 'Success',
                    message: '上传成功，积分 + ' + score.toString(),
                    type: 'success',
                })
            }
            form.title = ''
            form.content = ''
            form.author = username
            form.tags = []
            form.desc = ''
            form.line = 0
            form.language = '无'
            upload.value!.clearFiles()
            emit('reflesh')
        } else {
            console.log('error submit!', fields)
        }
    })
}
</script>

<style lang="scss">
.el-row {
    margin-bottom: 40px;
    --el-fill-color-blank: #eff0f0;
}

.el-form-item__label {
    --el-text-color-regular: #b9baba;
}
</style>
