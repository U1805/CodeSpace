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
        <el-form :model="form">
            <el-form-item label="文件上传">
                <el-upload
                    class="upload-demo"
                    drag
                    multiple
                    action="#"
                    :limit="1"
                    :auto-upload="false"
                    :on-change="handleFileUpload"
                    :on-remove="handleFileUpload"
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
            <el-form-item label="算法库名">
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

            <el-form-item>
                <el-button type="primary" @click="onSubmit">Create</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'

const tagOptions = [
    {
        value: 'HTML',
        label: 'HTML'
    },
    {
        value: 'CSS',
        label: 'CSS'
    },
    {
        value: 'JavaScript',
        label: 'JavaScript'
    }
]

const allow_type = [
    'html', 'css', 'javascript',
    'c', 'cpp', 'cs', 'java', 'py', 'go',
    'txt', 'md'
]

const handleFileUpload = (file, upload_files) => {
    form.content = ""
    form.line = 0
    upload_files.forEach((file) => {
        const ext = file.raw.name.split('.')[file.raw.name.split('.').length-1]
        if (allow_type.findIndex(ele => ele == ext) != -1){
            form.language = ext
            countTextFile(file.raw, ext)
        }
    })
}

const countTextFile = (file, ext) => {
    const reader = new FileReader()
    reader.onload = () => {
        const content = reader.result as string
        // 这里可以实现文本文件的统计逻辑
        const wordCount = content.split(/\s+/).length
        const charCount = content.length
        const lineCount = content.split(/\n/).length
        form.content =content,
        form.line = lineCount
    }
    reader.readAsText(file)
}

const username = localStorage.getItem("userToken")
const form = reactive({
    title: '',
    content: '',
    author: username,
    tags: [],
    desc: '',
    line: 0,
    language: '无'
})

const onSubmit = () => {
    console.log(form)
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
