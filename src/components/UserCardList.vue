<template>
    <van-skeleton title avatar :loading="props.loading" v-for="user in props.userList">
            <div style="width: 100%;">
                <van-card
                :desc="user.userProfile"
                :title="`${user.userName}`"
                :thumb="user.userAvatar"
                    >
                    <template #tags>
                        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
                            {{ tag }}
                        </van-tag>
                    </template>
                    <template #footer>
                        <van-button size="small">联系我</van-button>
                    </template>
                </van-card>
            </div>
      
    </van-skeleton>
  </template>
  
<script setup lang="ts">

  
interface UserCardListProps {
    loading: boolean;
    userList: any[];
}
  
const props = withDefaults(defineProps<UserCardListProps>(), {
    loading: true,
    // @ts-ignore
    userList: [] as any[],
});
  
</script>
  
<style scoped>
  
</style>
  