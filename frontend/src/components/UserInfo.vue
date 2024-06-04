<template>
    <div class="card">
        <div class="section">
            <i class="message fas fa-envelope"></i><i class="notif fas fa-bell"></i>
            <div style="height: 100%; display: flex; flex-direction: column; justify-content: center;">
                <div class="pic"><img :src="userData['userAvatar']"></div>
                <div class="name">{{ userData['userName'] }}</div>
                <div class="tag">{{ userData['userEmail'] }}</div>
            </div>
        </div>
        <div class="bottom-section">
            <div class="social-media"><a href="#"><i class="fab fa-facebook"></i></a><a href="#"><i
                        class="fab fa-twitter"></i></a><a href="#"><i class="fab fa-instagram"></i></a></div>
            <div class="videos">{{ userData['userInfo']['repo'] }}<span>算法库</span></div>
            <div class="border"></div>
            <div class="subscribers">{{ userData['userInfo']['score'] }}<span>得分</span></div>
            <div class="border"></div>
            <div class="videws">{{ userData['userInfo']['score'] }}<span>行数</span></div>
        </div>
    </div>
</template>

<script setup lang="ts">
defineProps({
  userData: Object
})
</script>

<style>
@import url("https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/font-awesome/5.15.2/css/all.min.css");
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
    background: #24242E;
}

.message,
.notif {
    position: absolute;
    top: 40px;
    font-size: 24px;
    cursor: pointer;
    color: #fff
}

.message {
    right: 40px
}

.notif {
    left: 40px
}

.pic {
    position: relative;
    width: 150px;
    height: 150px;
    margin: 0 auto 20px auto;
    padding: 4px;
    border: 2px solid #FFC107;
    border-radius: 50%
}

.pic img {
    width: 100%;
    height: 100%;
    border-radius: 50%
}

.pic::before {
    content: "";
    width: 100%;
    height: 100%;
    position: absolute;
    border: 1px solid #FFC107;
    left: 0;
    top: 0;
    box-sizing: border-box;
    border-radius: 50%;
    animation: animate 1.5s linear infinite
}

@keyframes animate {
    to {
        transform: scale(1.5);
        opacity: 0
    }
}

.name {
    color: #fafafc;
    font-size: 28px;
    font-weight: 600;
    letter-spacing: 2px;
    text-transform: inherit
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
    background-color: #2C2B37;
    padding: 60px 40px;
    font-size: 28px;
    text-transform: inherit;
}

.border {
    width: 1px;
    height: 20px;
    background: #bbb;
    margin: 0 30px
}

.bottom-section span {
    font-size: 14px;
    display: block
}

.social-media {
    position: absolute;
    width: 100%;
    top: -30px;
    left: 0;
    z-index: 1
}

.social-media i {
    position: relative;
    width: 60px;
    height: 60px;
    background: #FFCC66;
    border-radius: 50%;
    color: #f1f1f1;
    font-size: 24px;
    line-height: 60px !important;
    margin: 0 10px
}

.social-media i::after {
    content: "";
    width: 100%;
    height: 100%;
    position: absolute;
    border: 1px solid #e74c3c;
    left: 0;
    top: 0;
    box-sizing: border-box;
    border-radius: 50%;
    z-index: -1;
    transform: scale(.9);
    transition: .4s linear
}

.social-media i:hover::after {
    transform: scale(1.5);
    opacity: 0
}
</style>