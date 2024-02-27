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
        <van-cell title="用户名" is-link :value="store.loginUser.userName" />
        <van-cell title="头像" is-link> 

            <template #value>
                <img :src="store.loginUser.userAvatar" alt="用户头像" />
            </template>

        </van-cell>
        <van-cell title="用户角色" is-link :value="store.loginUser.userRole" />
        <van-cell title=""></van-cell>
    </van-cell-group>

    <van-space v-if="store.loginUser.userRole === ACCESS_ENUM.NOT_LOGIN" direction="vertical" fill>
            <van-button type="primary" to="/user/login" block>登录</van-button>
            <van-button type="success" to="/user/register" block>注册</van-button>
    </van-space>
    <van-space direction="vertical" fill v-else>
            <van-button type="danger" @click="logout" block>注销</van-button>
    </van-space>
</template>

