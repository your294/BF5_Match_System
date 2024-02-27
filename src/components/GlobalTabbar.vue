<script setup lang="ts">
import { ref } from "vue"
import { showToast } from "vant"
import { useRouter } from "vue-router"

let router_map: Map<String, String> = new Map();
router_map.set("index", "主页")
router_map.set("search", "搜索")
router_map.set("queue", "队伍")
router_map.set("user_setting", "用户设置")

const router = useRouter();

const active =  ref("index");
const onChange = (name: string) => {
    router.push({name: router_map.get(name) as any})
}
</script>


<template>
    <div id="tabbar">
        <van-tabbar v-model="active" @change="onChange">
            <van-tabbar-item icon="home-o" name="index">主页</van-tabbar-item>
            <van-tabbar-item icon="search" name="search">搜索</van-tabbar-item>
            <van-tabbar-item icon="friends-o" name="queue">队伍</van-tabbar-item>
            <van-tabbar-item icon="setting-o" name="user_setting">用户</van-tabbar-item>
        </van-tabbar>

    </div>
</template>