<script setup lang="ts">
import { ref } from 'vue'
import { UserControllerService } from "../../../generated"
import { useRouter } from 'vue-router'
import { userInfoStore }  from "../../stores/user"

const router = useRouter();
const store = userInfoStore();


const username = ref("");
const password = ref("");

const userLogin = async () => {
    const res = await UserControllerService.userLoginUsingPost({
        userPassword: password.value,
        userAccount: username.value,
    });
    if (res.code === 0) {
        console.log("登录成功");
        store.updateLoginInfo(res.data);
        router.push("/");
    }else {
        alert("请重新输入密码");
        username.value = "";
        password.value = "";
    }
}
</script>

<template>
    <div>
        <van-form @submit="userLogin">
            <van-cell-group inset>
                <van-field
                v-model="username"
                name="Username"
                label="用户名"
                placeholder="Username"
                :rules="[{ required: true, message: 'Username is at least 8' }]"
                />
                <van-field
                v-model="password"
                type="password"
                name="Password"
                label="密码"
                placeholder="Password"
                :rules="[{ required: true, message: 'Password is at least 8' }]"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                登录
                </van-button>
            </div>
        </van-form>

    </div>
</template>

