<template>
    <div id="TeamCardList" style="overflow-y: scroll;">
        <van-card v-for="team in teamList"
        :desc="team.description"
        thumb="src/assets/teamAvatar.png"
        >
            <template #title>
                <h2>{{ team.name }}</h2>
            </template>
            <template #tags>
                <van-space fill>
                    <van-tag v-if="team.status === 0" plain type="danger">公开</van-tag>
                    <van-tag v-if="team.status === 1" plain type="danger">私有</van-tag>
                    <van-tag v-if="team.status === 2" plain type="danger">加密</van-tag>
                    <p>队伍人数{{ team.hasJoinNum }} / {{ team.maxNum }}</p>
                </van-space>
                
            </template>
            <template #price>
                <div style="font-size: 13px;">
                    <p>队伍创建时间: {{ team.createTime }}</p>
                    <p>队伍过期时间: {{ team.expireTime }}</p>
                </div>
            </template>
            <template #footer>
                <van-button v-if="!team.hasJoin" plain hairline 
                type="primary" 
                @click="joinTeam(team.id as number, team.status as number)"
                >
                    加入队伍
                </van-button>
                <van-button v-else plain hairline 
                type="danger" 
                @click="quitTeam(team.id as number)">退出队伍</van-button>
                <van-button v-if="team.userId === store.loginUser.id" 
                plain hairline type="success" @click="dissolveTeam">解散队伍</van-button>
            </template>
        </van-card>
        <van-dialog v-model:show="show" title="提交密码" show-cancel-button>
            <van-field v-model="password" label="password" placeholder="at least 32位" />
        </van-dialog>

    </div>
</template>

<script setup lang="ts">
import { userInfoStore } from "@/stores/user"
import { TeamControllerService, type TeamJoinRequest } from '../../../generated';
import { computed } from 'vue';
import { ref } from 'vue';



const store = userInfoStore();
const show =  ref(false);
const password = ref("");


interface TeamInfo {
    id?: number;
    name?: string;
    description?: string;
    maxNum?: number;
    expireTime?: string;
    userId?: string;
    status?: number;
    createTime?: string;
    updateTime?: string;
    hasJoinNum?: number;
    hasJoin?: boolean;
}

interface Props {
    teamList: TeamInfo[];
}

const props = withDefaults(defineProps<Props>(), {
    teamList: [] as TeamInfo[]
});





async function joinTeam(teamId: number, status: number) {
    let request: TeamJoinRequest = {};
    if (status == 2) {
        show.value = !show.value;
        request = {
            teamId: teamId,
            password: password.value
        }
    }else {
        request = {
            teamId: teamId
        }
    }
    const res = await TeamControllerService.joinTeamUsingPost(request);
    if (res.code === 0) {
        alert("加入队伍成功");
    }else {
        alert("加入队伍失败");
    }
}

async function quitTeam(teamId: number) {
    const res = await TeamControllerService.quitTeamUsingPost({
        teamId: teamId
    });
    if (res.code === 0) {
        alert("退出队伍成功");
    }else {
        alert("退出队伍失败!!!");
    }
}

function dissolveTeam() {

}

</script>