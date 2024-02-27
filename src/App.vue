<script setup lang="ts">
import { useRoute } from 'vue-router'
import BasicLayout from "./layouts/BasicLayout.vue"
import { onMounted } from 'vue';

onMounted(() => {
  () => import("@/access")
})

const routes = useRoute();
</script>

<template>
  <div id="app">
    <template v-if="routes.path.startsWith('/user')">
      <router-view></router-view>
    </template>
    <template v-else>
      <BasicLayout>
        <template #content>
          <router-view></router-view>
        </template>
      </BasicLayout>
    </template>
  </div>
</template>


