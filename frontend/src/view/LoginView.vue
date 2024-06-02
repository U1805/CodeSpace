<!-- eslint-disable vue/multi-word-component-names -->
<template>
    <div class="container">
        <div class="login-wrapper">
            <div class="header">Login</div>
            <div class="form-wrapper">
                <input type="text" name="username" placeholder="username" class="input-item" v-model="username">
                <input type="password" name="password" placeholder="password" class="input-item" v-model="password">
                <div class="btn" @click="login">Login</div>
            </div>
            <div class="msg">
                Don't have account?
                <a href="/register">Sign up</a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const router = useRouter();

// Login.vue
const login = async () => {
    // 登录逻辑，例如向服务器发送请求
    const response = await fetch('/api/login', { method: 'POST', body: JSON.stringify({/* 登录数据 */ }) })
    const data = await response.json()
    if (data.success) {
        localStorage.setItem('userToken', data.token)
        router.push({ name: 'info' })
    }
}
</script>

<style scoped lang="scss">
* {
    margin: 0;
    padding: 0;
}

html {
    height: 100%;
}

body {
    height: 100%;
}

.container {
    height: 100%;
    width: 100%;
    background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}

.login-wrapper {
    background-color: #fff;
    width: 358px;
    height: 588px;
    border-radius: 15px;
    padding: 0 50px;
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}

.header {
    font-size: 38px;
    font-weight: bold;
    text-align: center;
    line-height: 200px;
}

.input-item {
    display: block;
    width: 100%;
    margin-bottom: 20px;
    border: 0;
    padding: 10px;
    border-bottom: 1px solid rgb(128, 125, 125);
    font-size: 15px;
    outline: none;
}

.input-item:placeholder {
    text-transform: uppercase;
}

.btn {
    text-align: center;
    padding: 10px;
    width: 100%;
    margin-top: 40px;
    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
    color: #fff;
}

.msg {
    text-align: center;
    line-height: 88px;
}

a {
    text-decoration-line: none;
    color: #abc1ee;
}
</style>