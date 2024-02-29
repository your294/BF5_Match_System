<template>
    <div id="UserIndexView" style="overflow-y: scroll;">
        <van-swipe style="height: 20vh; margin-bottom: 10px;" :autoplay="3000" lazy-render>
            <van-swipe-item v-for="image in images" :key="image">
                <img style="object-fit: cover;" :src="image" />
            </van-swipe-item>
        </van-swipe>
        <!-- <UserCardList :loading="loading" :user-list="userList" /> -->
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
import { onMounted, watchEffect } from 'vue';
import { UserControllerService } from '../../generated';
import UserCardList from '@/components/UserCardList.vue';
import { ref } from 'vue';
import type { Ref } from 'vue';
import { showDialog } from 'vant';

const images = [
      'src\\assets\\OIP1.jpg',
      'src\\assets\\OIP2.jpg',
];

const userList: Ref<any> = ref([]);

const loading = ref(true);
const finished = ref(false);

const pageSize = ref(6);
const current = ref(1);

const loadUserData = async () => {
    const res = await UserControllerService.listUserVoByPageUsingPost({
        current: current.value,
        pageSize: pageSize.value
    })
    if (res.code === 0) {
        if (res.data.records.length === 0) {
            loading.value = true;
            finished.value = true;
            // alert("数据请求为空!")
            return;
        }
        userList.value = userList.value.concat(res.data.records);
        loading.value = false;
        current.value ++;

        if (res.data.records.length < pageSize || userList.value.length === res.data.total) {
            finished.value = true;
        }
    }else {
        alert("后端服务挂了，请联系管理员!!!")
    }
}

// watchEffect(() => {
//     loadUserData();
// })

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