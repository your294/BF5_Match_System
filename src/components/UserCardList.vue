<template>
    <div style="overflow-y: scroll;">
        <van-skeleton title avatar :loading="props.loading" v-for="user in props.userList">
                <div style="width: 100%; overflow-y: scroll;">
                    <van-card
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
                </div>
        </van-skeleton>
    </div>    
  </template>
  
<script setup lang="ts">
import { showDialog } from 'vant';
import { ref } from 'vue';

  
interface UserCardListProps {
    loading: boolean;
    userList: any[];
}
  
const props = withDefaults(defineProps<UserCardListProps>(), {
    loading: true,
    // @ts-ignore
    userList: [] as any[],
});

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
  
<style scoped>
  
</style>
  