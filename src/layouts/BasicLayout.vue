<template>
    <div id="BasicLayout">
        <!-- header -->
        <van-nav-bar title="Parter-Team"  left-arrow 
        @click-left="onClickBack" @click-right="onClickSearch">
            <template #right>
                <van-icon name="search" />
            </template>
        </van-nav-bar>

        <!-- content -->
        <slot name="content">

        </slot>
        <!-- tabbar -->
        <GlobalTabbar />
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