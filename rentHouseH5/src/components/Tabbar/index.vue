<template>
  <van-tabbar
    v-show="isShowTabBar"
    v-model="active"
    :placeholder="true"
    :route="true"
    fixed
  >
    <van-tabbar-item
      v-for="(item, index) in tabBarData"
      :key="index"
      :icon="item?.icon as string"
      :to="item.to"
    >
      {{ item.title }}
    </van-tabbar-item>
  </van-tabbar>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";
import tabBarRoutes from "@/router/tabBarRoutes";
import { useRoute } from "vue-router";
const route = useRoute();
console.log(route);
const active = ref(0);
const tabBarData = computed(() => {
  return tabBarRoutes.map(item => {
    return {
      icon: item.meta?.icon,
      title: item.meta?.title,
      to: {
        path: item.path
      }
    };
  });
});
const isShowTabBar = computed(() => {
  return tabBarRoutes.some(item => item.path === route.path);
});
</script>
