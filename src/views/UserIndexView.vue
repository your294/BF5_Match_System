<template>
    <div id="UserIndexView" style="overflow-y: scroll;">
        <UserCardList :loading="loading" :user-list="userList" />
    </div>
</template>


<script setup lang="ts">
import { onMounted, watchEffect } from 'vue';
import { UserControllerService } from '../../generated';
import UserCardList from '@/components/UserCardList.vue';
import { ref } from 'vue';
import type { Ref } from 'vue';

const userList: Ref<any> = ref([]);

const loading = ref(true);

const loadUserData = async () => {
    const res = await UserControllerService.listUserVoByPageUsingPost({
        current: 1,
        pageSize: 15
    })
    if (res.code === 0) {
        userList.value = res.data.records;
    }else {
        alert("后端服务挂了，请联系管理员!!!")
    }
    loading.value = false;
}


watchEffect(() => {
    loadUserData();
})


</script>