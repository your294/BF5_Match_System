<template>
    <div id="BasicLayout">
        <div id="header">
            <!-- header -->
            <van-nav-bar title="Parter-Team"  left-arrow 
            @click-left="onClickBack" @click-right="onClickSearch">
                <template #right>
                    <van-icon name="search" />
                </template>
            </van-nav-bar>
        </div>
        
        <div id="content">
            <!-- content -->
            <slot name="content">

            </slot>
        </div>
        <div id="tabbar">
            <!-- tabbar -->
            <GlobalTabbar />
        </div>
        
    </div>
</template>

<script setup lang="ts">
import router from "@/router";
import GlobalTabbar from "../components/GlobalTabbar.vue"
import { showToast } from "vant"
import { useRouter }  from "vue-router"


const onClickBack = () => {
    // 单纯的路由后退
    router.back();
}

const onClickSearch = () => {
    router.push({name: "搜索"})
}
</script>

<style scoped>
#content {
    /* 样式 */
  overflow: auto; /* 如果内容过多，可以滚动 */
  padding-bottom: 50px; /* 可以根据需要调整 */
}
</style>