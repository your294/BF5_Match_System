<template>
    <div>
        <!-- header -->
        <van-nav-bar title="Parter-Team"  left-arrow 
            @click-left="onClickBack" @click-right="onClickSearch">
                <template #right>
                    <van-icon name="search" />
                </template>
        </van-nav-bar>

        <!-- content -->
        <router-view></router-view>

        <!-- footer -->
        <GlobalTabbar />
    </div>
    
</template>


<script setup lang="ts">
import GlobalTabbar from '@/components/GlobalTabbar.vue';

import { showToast } from "vant"


const onClickBack = () => {
    showToast("Back")
}

const onClickSearch = () => {
    showToast("Search")
}

</script>