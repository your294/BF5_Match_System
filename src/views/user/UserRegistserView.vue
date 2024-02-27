<script setup lang="ts">
import { ref } from 'vue'
import { UserControllerService } from "../../../generated"
import { useRouter } from 'vue-router'

const router = useRouter();


const username = ref("");
const password = ref("");
const checkPassword = ref("");

const userRegister = async () => {
    if (checkPassword.value !== password.value) {
        alert("前后2次输入密码必须一致");
    }
    const res = await UserControllerService.userRegisterUsingPost({
        userPassword: password.value,
        userAccount: username.value,
        checkPassword: checkPassword.value
    });
    if (res.code === 0) {
        console.log("注册成功");
        router.push({name: "用户登录"});
    }else {
        alert("请重新输入密码");
        password.value = "";
        checkPassword.value = "";
    }
}
</script>

<template>
    <div>
        <van-form @submit="userRegister">
            <van-cell-group inset>
                <van-field
                v-model="username"
                name="Username"
                label="Username"
                placeholder="Username"
                :rules="[{ required: true, message: 'Username is at least 8' }]"
                />
                <van-field
                v-model="password"
                type="password"
                name="Password"
                label="新密码"
                placeholder="Password"
                :rules="[{ required: true, message: 'Password is at least 8' }]"
                />
                <van-field
                v-model="checkPassword"
                type="password"
                name="CheckPassword"
                label="确认密码"
                placeholder="Password"
                :rules="[{ required: true, message: 'CheckPassword' }]"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                注册
                </van-button>
            </div>
        </van-form>

    </div>
</template>

