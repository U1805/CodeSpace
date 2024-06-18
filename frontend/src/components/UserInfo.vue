<template>
    <div class="card">
        <el-drawer v-model="dialog" title="编辑用户信息" :before-close="cancelForm" direction="ltr">
            <div>
                <el-form :model="form">
                    <el-form-item label="Name" label-width="80px">
                        <el-input :disabled="true" v-model="form.username" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="Avatar" label-width="80px">
                        <div style="display: flex; align-items: flex-end;">
                            <el-avatar shape="square" :size="100" fit="fill" :src="form.avatar" />
                            <el-button :icon="Edit" style="width: 10px; margin-left: 5px"/>
                        </div>
                    </el-form-item>
                    <el-form-item label="Email" label-width="80px">
                        <el-input v-model="form.email" autocomplete="off" />
                    </el-form-item>
                </el-form>
                <div style="text-align: right;">
                    <el-button @click="cancelForm">Cancel</el-button>
                    <el-button type="primary" :loading="loading" @click="onClick">
                        {{ loading ? 'Submitting ...' : 'Submit' }}
                    </el-button>
                </div>
            </div>
        </el-drawer>
        <div class="section">
            <i class="message fas fa-edit" @click="openEdit()"></i><i class="notif fas fa-bell"></i>
            <div
                style="height: 100%; display: flex; flex-direction: column; justify-content: center"
            >
                <div class="pic">
                    <img :src="userData ? userData['avatar'] : default_img" />
                </div>
                <div class="name">{{ userData ? userData['username'] : 'username' }}</div>
                <div class="tag">{{ userData ? userData['email'] : '123456@email.com' }}</div>
            </div>
        </div>
        <div class="bottom-section">
            <div class="social-media">
                <a href="#"><i class="fab fa-facebook"></i></a
                ><a href="#"><i class="fab fa-twitter"></i></a
                ><a href="#"><i class="fab fa-instagram"></i></a>
            </div>
            <div class="videos">{{ userData ? userData['repo'] : 0 }}<span>算法库</span></div>
            <div class="border"></div>
            <div class="subscribers">{{ userData ? userData['score'] : 0 }}<span>得分</span></div>
            <div class="border"></div>
            <div class="videws">{{ userData ? userData['line'] : 0 }}<span>行数</span></div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { inject, reactive, ref } from 'vue'
import { ElDrawer } from 'element-plus'
import { ElNotification } from 'element-plus'
import { Edit } from '@element-plus/icons-vue'
import { editInfo } from '@/assets/request';


let timer

const dialog = ref(false)
const loading = ref(false)

const props = defineProps({
    userData: Object
})
const default_img = ref<string>('https://img.icons8.com/?size=256&id=tZuAOUGm9AuS&format=png')

const form = reactive({
    username: '',
    avatar: '',
    email: ''
})

const openEdit = () => {
    form.username = props.userData.username
    form.avatar = props.userData.avatar
    form.email = props.userData.email
    dialog.value = true
}

const reflesh = inject('reflesh') as Function
const onClick = async () => {
    loading.value = true
    const res = await editInfo(form)
    setTimeout(() => {
        loading.value = false
        dialog.value = false
        if (res === 'success') {
            ElNotification({
                title: '编辑成功',
                message: '用户信息修改成功',
                type: 'success'
            })
            reflesh()
        }else{
            ElNotification({
                title: '编辑错误',
                message: res,
                type: 'error'
            })
        }
    }, 800)
}

const cancelForm = () => {
    loading.value = false
    dialog.value = false
    clearTimeout(timer)
}
</script>

<style>
@import url('https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/font-awesome/5.15.2/css/all.min.css');
.card {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    border: 0;
}

.section {
    width: 100%;
    height: 100%;
    background: #24242e;
}

.message,
.notif {
    position: absolute;
    top: 40px;
    font-size: 24px;
    cursor: pointer;
    color: #fff;
}

.message {
    right: 40px;
}

.notif {
    left: 40px;
}

.pic {
    position: relative;
    width: 150px;
    height: 150px;
    margin: 0 auto 20px auto;
    padding: 4px;
    border: 2px solid #ffc107;
    border-radius: 50%;
}

.pic img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
}

.pic::before {
    content: '';
    width: 100%;
    height: 100%;
    position: absolute;
    border: 1px solid #ffc107;
    left: 0;
    top: 0;
    box-sizing: border-box;
    border-radius: 50%;
    animation: animate 1.5s linear infinite;
}

@keyframes animate {
    to {
        transform: scale(1.5);
        opacity: 0;
    }
}

.name {
    color: #fafafc;
    font-size: 28px;
    font-weight: 600;
    letter-spacing: 2px;
    text-transform: inherit;
}

.tag {
    font-size: 18px;
    color: #8c8c8e;
}

.bottom-section {
    width: 100%;
    height: 40%;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #2c2b37;
    padding: 60px 40px;
    font-size: 28px;
    text-transform: inherit;
}

.border {
    width: 1px;
    height: 20px;
    background: #bbb;
    margin: 0 30px;
}

.bottom-section span {
    font-size: 14px;
    display: block;
}

.social-media {
    position: absolute;
    width: 100%;
    top: -30px;
    left: 0;
    z-index: 1;
}

.social-media i {
    position: relative;
    width: 60px;
    height: 60px;
    background: #ffcc66;
    border-radius: 50%;
    color: #f1f1f1;
    font-size: 24px;
    line-height: 60px !important;
    margin: 0 10px;
}

.social-media i::after {
    content: '';
    width: 100%;
    height: 100%;
    position: absolute;
    border: 1px solid #e74c3c;
    left: 0;
    top: 0;
    box-sizing: border-box;
    border-radius: 50%;
    z-index: -1;
    transform: scale(0.9);
    transition: 0.4s linear;
}

.social-media i:hover::after {
    transform: scale(1.5);
    opacity: 0;
}
</style>
