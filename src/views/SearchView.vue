<template>
    <div id="SearchView">
        <van-search
            v-model="searchContent"
            shape="round"
            background="#4fc08d"
            placeholder="请输入要搜索的标签"
            >
            <template #action>
                <div @click="onCancel">取消</div>
            </template>
        </van-search>
        <div class="history-title-list">
            <h2 style="color: grey; font-size: 16px; margin-left: 5px;">历史搜索</h2>
            <van-space fill style="margin: 2px 10px 10px 5px">
                <van-tag style="margin-right: 10px;" 
                    size="large" 
                    type="primary" 
                    v-for="tag in historyTagList"
                    @click="clickHistoryTag(tag)"
                    >
                    {{ tag }}
                </van-tag>
            </van-space>
        </div>
        <van-divider 
            style="margin: 40px 0 25px 0;
                    border-color: gray;" />
        <div class="tag-choose-list">
            <h2 style="color: grey; font-size: 16px; margin-left: 5px;">标签栏</h2>
            <van-tabs v-model:active="activeName">
                <van-tab style="align-items: center;" v-for="tab in tabNameList" :title="tab" :name="tab">
                    <van-space wrap style="margin: 20px 20px 20px 20px;">
                        <van-tag
                            :plain="tag.value.choose"
                            color="#7232dd" 
                            size="large" 
                            v-for="tag in tabMap.get(tab)" 
                            :closeable="tag.value.choose"
                            @click="clickTag(tag.value)"
                            @close="closeTag(tag.value)">
                            {{ tag.value.tagName }}
                        </van-tag>
                    </van-space>
                </van-tab>
                
            </van-tabs>
        </div>
        <div style="width: 100%;">
            <van-button type="primary" block @click="startSearchUser">搜索</van-button>
        </div>
        
    </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { ref } from 'vue';
import type { Ref } from 'vue';

const activeName = ref("编程语言")

const tabNameList = ref([
    "编程语言", "学历", "心情"
]);

const languageTagList = [
    "Java", "cpp", "Python", "前端", "后端", "Pro", "薯条", "C", "C++",
    "Markdown", "html", "css", "sass", "typescript", "C#"
]

const eduQualificationTagList = [
    "大一", "大二", "大三", "大四", "研一", "研二", "研三"
]

const emojiTagList = [
    "sad", "emo", "happy", "excited", "boring"
]

interface Tag {
    tagName: string;
    choose: boolean;
}

let languageRefList: any[] = [];
let eduRefList: any[] = [];
let emojiRefList: any[] = [];

const changeList = (taglist: string[]) => {
    let ans = [];
    for (let tag of taglist) {
        ans.push(ref({
            tagName: tag,
            choose: false
        }))
    }
    return ans;
}

const tabMap: Map<string, any[]> = new Map();


onMounted(() => {
    languageRefList = changeList(languageTagList);
    eduRefList = changeList(eduQualificationTagList);
    emojiRefList = changeList(emojiTagList);
    tabMap.set("编程语言", languageRefList);
    tabMap.set("学历", eduRefList);
    tabMap.set("心情", emojiRefList);
})


const searchContent =  ref("");
const historyTagList = ref(["Java", "emo"])

const clickHistoryTag = (tag: string) => {
    console.log(tag);
    searchContent.value += tag + " "
}

const clickTag = (tag: Tag) => {
    console.log(tag);
    searchContent.value += tag.tagName + " ";
    tag.choose = !tag.choose;
}

const closeTag = (tag: Tag) => {
    let name: string = tag.tagName;
    let idx = searchContent.value.search(name);
    if (idx === -1) {
        alert("标签删除错误")
    }
    let n = name.length;
    let str = "";
    str += searchContent.value.slice(0, idx);
    str += searchContent.value.slice(idx + n);
    searchContent.value = str;
    tag.choose = !tag.choose;
}

const startSearchUser = () => {

}

const onCancel = () => {
    searchContent.value = "";
}
</script>