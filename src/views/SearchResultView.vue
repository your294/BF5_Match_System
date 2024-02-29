<template>
    <div id="SearchResultView" style="overflow-y: scroll;">
        <van-list
            v-model:loading="loading"
            :finished="finished"
            finished-text="Finished"
            error-text="Request failed. Click to reload"
            @load="loadUserData"
        >
            <van-card v-for="user in userList"
                :desc="user.userProfile"
                :title="`${user.userName}`"
                :thumb="user.userAvatar"
                    >
                <template #tags>
                    <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
                        {{ tag }}
                    </van-tag>
                    <br/>
                    <van-tag style="margin-top: 10px;"  color="#7232dd" size="large">
                        {{ user.userRole === "user" ? "用户" : "管理员" }}
                    </van-tag>
                </template>
                <template #footer>
                    <van-button size="small" @click="showContact(user)">联系我</van-button>
                </template>

                <van-dialog 
                    v-model:show="show" title="联系方式" 
                    show-cancel-button>
                </van-dialog>

            </van-card>
        </van-list>
    </div>
</template>

<script setup lang="ts">
import { UserControllerService } from '../../generated';
import { showDialog } from 'vant';
import { ref, onMounted } from 'vue';
import type { Ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const {tagContent}: any = route.query;
console.log("tagContent" + tagContent);


const userList: Ref<any[]> = ref([]);
const loading = ref(true);
const finished = ref(false);

const loadUserData = async () => {
    let tagList: string[] = tagContent.split(' ');
    tagList = tagList.filter(str => str !== ' ')
    const res = await UserControllerService.searchUsersUsingGet(tagList)
    if (res.code === 0) {
        userList.value = userList.value.concat(res.data);
        for (let user of userList.value) {
            user.tags = JSON.parse(user.tags);
            console.log(user.tags);
        }
        loading.value = false;
        finished.value = true;
    }else {
        alert("后端服务挂了，请联系管理员!!!")
    }
}

onMounted(() => {
    loadUserData();
})

const show = ref(false);

const showContact = (user: any) => {
    showDialog({
        title: '联系方式',
        message: `电话: ${user.telephone} \n 邮箱: ${user.email}`,
    }).then(() => {
    // on close
    });
}
</script>