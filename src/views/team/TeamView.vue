<template>
    <div>
        <!-- 搜索栏 -->
        <van-search
            v-model="teamValue"
            show-action
            placeholder="搜索队伍"
            @search="onSearch"
            @cancel="onCancel"
        />
        <div id="showTeam" v-if="!showResult" style="overflow-y: scroll;">
            <!-- 队伍展示栏 2个tab 区分公共和私有 -->
            <van-tabs v-model:active="activeKind" @click-tab="changeTeamList(activeKind)">
                <van-tab title="公开" name="public"></van-tab>
                <van-tab title="加密" name="password"></van-tab>
                <van-tab title="我的" name="mine"></van-tab>
            </van-tabs>
            <!-- 加点间隔 -->
            <div style="margin-bottom: 10px;"></div>
            <!-- 队伍展示组件 -->
            <team-card-list :team-list="teamList" />
        </div>

        <div id="showResultTeam" v-else>
            <van-button type="primary" @click="showResult = !showResult" block>返回队伍展示界面</van-button>
            <team-card-list :team-list="ResultTeamList" />
        </div>
        
        <!-- 添加队伍的悬浮按钮 -->
        <van-floating-bubble v-model:offset="offset" icon="add" @click="clickAddTeam" />
    </div>
    <!-- add this to show toast ui -->
    <van-toast></van-toast>
</template>

<script setup lang="ts">
import { showToast } from 'vant';
import { ref } from 'vue';
import { useRouter } from 'vue-router'
import TeamCardList from './TeamCardList.vue';
import { TeamControllerService, type TeamQueryRequest } from '../../../generated';
import type { Ref } from 'vue';
import moment from 'moment';
import { onMounted } from 'vue';
import { userInfoStore } from "@/stores/user";

const showResult = ref(false);
const activeKind = ref("public");
const router = useRouter();
const teamValue = ref("");
const teamList: Ref<any> = ref([]);
const offset = ref({x: 300, y: 500});
const store = userInfoStore();


const ResultTeamList: Ref<any> = ref([]);
const onSearch = async () => {
    const res = await TeamControllerService.searchTeamByConditionsUsingPost({
        name: teamValue.value,
        searchText: teamValue.value
    });
    if (res.code === 0) {
        ResultTeamList.value = res.data;
    }else {
        alert("后端搜索服务已挂!!");
    }
    showResult.value = !showResult.value;
}


/**
 * 取消队伍搜索
 */
const onCancel = () => {
    teamValue.value = "";
    showToast("Cancel search")
}

/**
 * 跳转到新增队伍界面
 */
const clickAddTeam = () => {
    router.push("/Team/add");
}

/**
 * 开始展示公有标签
 */
onMounted(() => {
    changeTeamList("public");
})


/**
 * 根据tab标签名切换显示的队伍
 * @param tab tab标签名
 */
async function changeTeamList(tab: string) {
    let status = 0;
    // alert(tab);
    switch (tab) {
        case "public":
            status = 0;
            break;
        case "password":
            status = 2;
            break;
        case "mine": {
            showMyTeam();
            return;
        }
        default:
            break;
    }
    const query: TeamQueryRequest = {
        status: status
    }
    const res = await TeamControllerService.listAllTeamUsingPost(query);
    if (res.code === 0) {
        teamList.value = res.data;
    }else {
        alert("后端服务挂了，请联系管理员");
    }
}

/**
 * 展示你的队伍
 */
async function showMyTeam() {
    const res = await TeamControllerService.listMyTeamUsingGet();
    if (res.code === 0) {
        teamList.value = res.data;
        for (let team of teamList.value) {
            team.expireTime = formatDate(team.expireTime);
            team.createTime = formatDate(team.createTime);
        }
    }else {
        alert("未查询到该用户的队伍, 请联系后台管理员");
    }
}

const formatDate = (date: string) => {
    return moment(date, "YYYY/MM/DD")
}
</script>