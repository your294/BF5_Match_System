<template>
    <div id="TeamAddView">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                v-model="team.name"
                name="Username"
                label="队伍名字"
                placeholder=""
                :rules="[{ required: true, message: 'teamName is less than 20 words' }]"
                />
                <van-field
                v-model="team.description"
                name="desc"
                label="desc"
                placeholder="介绍你的队伍吧"
                />
                <van-field
                v-model="team.maxNum"
                name="maxNum"
                label="maxNum"
                placeholder="设置队伍最大人数"
                />
                <van-field name="radio" label="队伍隐私">
                    <template #input>
                        <van-radio-group v-model="team.status" direction="horizontal">
                        <van-radio name="0">公开</van-radio>
                        <van-radio name="1">私有</van-radio>
                        <van-radio name="2">加密</van-radio>
                        </van-radio-group>
                    </template>
                </van-field>
                <van-field v-if="team.status === '2'" name="password" label="密码" 
                    v-model="team.password" placeholder="最少8位最多32位">
                </van-field>
                <van-field name="expireTime" label="过期时间">
                    <template #input>
                        <van-cell title="" :value="date" @click="show = true" />
                        <van-calendar v-model:show="show" @confirm="onConfirm" />

                    </template>
                    
                </van-field>

            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                添加你的队伍
                </van-button>
            </div>
        </van-form>

    </div>
</template>

<script setup lang="ts">
import type { Ref } from "vue";
import { TeamControllerService, type TeamAddRequest } from "../../../generated"
import { ref } from "vue";
import router from "@/router";
import moment from "moment"

const date: Ref<any> = ref('');
const show = ref(false);

const formatDate = (date: any) => `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
const onConfirm = (value: any) => {
    show.value = false;
    date.value = formatDate(value);
    console.log(date.value);
};


const team: Ref<TeamAddRequest> = ref({
    name: "",
    description: "",
    status: "0",
    maxNum: 1,
    expireTime: "2024-03-29 20:06:40",
    password: "",
});

async function onSubmit() {
    team.value.expireTime = formatDateString(date.value);
    console.log(team.value.expireTime);
    const res = await TeamControllerService.addTeamUsingPost(team.value);
    if (res.code === 0) {
        router.push("/Team");
    }else {
        alert("队伍添加失败" + res.data);
    }
}

function formatDateString(inputDate: string): string {
  // 使用 Moment.js 解析输入的日期字符串
  const date = moment(inputDate, 'YYYY/MM/DD');

  return date.toISOString();
}
</script>