<script setup lang="ts">
import ACCESS_ENUM from "@/access/accessEnum";
import { userInfoStore } from "@/stores/user"
import "@/access"
import router from "@/router";
import { UserControllerService } from "../../../generated";

const store = userInfoStore();

const EditProfile = () => {
    router.push({name: "用户编辑"});
}

const logout = async () => {
    const res = await UserControllerService.userLogoutUsingPost();
    if (res.code === 0) {
        console.log("注销成功");
        store.$reset();
    }else {
        alert("注销失败");
    }
}


</script>

<template>
    <van-cell-group>
        <van-cell title="用户名" is-link :value="store.loginUser.userName" to="/user/edit/userName" />
        <div v-if="store.loginUser.userRole !== ACCESS_ENUM.NOT_LOGIN">
            <van-cell title="头像" is-link> 
                <template #value>
                    <img :src="store.loginUser.userAvatar" alt="用户头像" />
                </template>
            </van-cell>
            <van-cell title="用户角色" :value="store.loginUser.userRole" />
            <van-cell title="邮箱" is-link :value="store.loginUser.email" to="/user/edit/email"></van-cell>
            <van-cell title="电话" is-link :value="store.loginUser.telephone" to="/user/edit/telephone"></van-cell>
            <van-cell title="个人标签" to="/user/edit/tags">
                <template #value>
                    <van-space>
                        <van-tag type="primary" v-for="tag in store.loginUser.tags">{{ tag }}</van-tag>
                    </van-space>
                </template>
            </van-cell>
            <div>
                <van-space direction="vertical" fill>
                    <van-cell title="个人简介" is-link to="/user/edit/userProfile" />
                    <p style="text-align: center; text-overflow: ellipsis;">{{ store.loginUser.userProfile }}</p>
                </van-space>
            </div>
            
        </div>
        <div v-else>
            <van-empty image="error" description="请先登录" />
        </div>
    </van-cell-group>

    <van-space v-if="store.loginUser.userRole === ACCESS_ENUM.NOT_LOGIN" direction="vertical" fill>
            <van-button type="primary" to="/user/login" block>登录</van-button>
            <van-button type="success" to="/user/register" block>注册</van-button>
    </van-space>
    <van-space direction="vertical" fill v-else>
            <van-button type="danger" @click="logout" block>注销</van-button>
    </van-space>
</template>

<style scoped>
img {
    height: 50px;
    width: 50px;
}
</style>

