<template>
  <div>
    <!--    消息列表-->
    <PullDownRefreshContainer
      :request="loadList"
      ref="pullDownRefreshContainerRef"
      class="min-h-[70vh]"
    >
      <!--    顶部-->
      <van-sticky offset-top="0">
        <div
          class="main-container flex justify-around items-center bg-[--van-primary-background-color] h-[20vh] backdrop-filter backdrop-blur-xl backdrop-saturate-150"
        >
          <div
            v-for="item in navList"
            :key="item.type"
            class="flex flex-col justify-center items-center"
            @click="goTypeList(item.type)"
          >
            <div
              :style="{ background: item.color }"
              class="flex flex-col justify-center items-center h-[12vw] w-[12vw] rounded-xl py-[3px]"
            >
              <van-icon :name="item.icon" :badge="item.badge" size="25" />
            </div>
            <span class="mt-[2px]">{{ item.name }}</span>
          </div>
        </div>
      </van-sticky>
      <div class="flex flex-col main-container">
        <!--      单条消息-->
        <div
          class="flex py-[3px] mt-[20px]"
          v-for="item in list"
          :key="item.id"
          @click="onItemClick(item)"
        >
          <div class="relative">
            <van-image
              round
              width="15vw"
              height="15vw"
              :src="item.avatarUrl || defaultAvatar"
            >
              <template v-slot:error>加载失败</template>
              <template v-slot:loading>
                <van-loading type="spinner" size="20" />
              </template>
            </van-image>
            <span
              v-if="!item.isRead"
              class="absolute top-0 right-0 w-[8px] h-[8px] rounded-full bg-red-500"
            ></span>
          </div>
          <div
            class="flex flex-col justify-center ml-[10px] text-[12px] w-[70vw]"
          >
            <div class="flex justify-between mb-[3px]">
              <div class="font-bold">{{ item.nickname }}</div>
              <div class="">{{ formatTime(item.createTime) }}</div>
            </div>

            <div class="--van-gray-6 van-ellipsis">{{ item.content }}</div>
          </div>
        </div>
        <van-empty v-if="loaded && list.length === 0" description="暂无消息" />
      </div>
    </PullDownRefreshContainer>
  </div>
</template>

<script setup lang="ts" name="Message">
import { computed, onActivated, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
import { getMessageOverview, getMessagePage, markMessageRead } from "@/api/message";
import type { MessageItemInterface, MessageType } from "@/api/message/types";
import type { ReqPage } from "@/api/types";
import defaultAvatar from "../../../public/favicon.ico";

const router = useRouter();
const list = ref<MessageItemInterface[]>([]);
const loaded = ref(false);
const pullDownRefreshContainerRef = ref<InstanceType<
  typeof PullDownRefreshContainer
> | null>(null);

//#region 顶部三入口（角标）
const overview = ref({ noticeUnread: 0, commentUnread: 0, likeUnread: 0 });
const navList = computed(() => [
  {
    icon: "volume-o",
    name: "通知",
    type: 1 as MessageType,
    color: "#f1be4a",
    badge: overview.value.noticeUnread || undefined
  },
  {
    icon: "chat-o",
    name: "评论",
    type: 2 as MessageType,
    color: "#51b290",
    badge: overview.value.commentUnread || undefined
  },
  {
    icon: "good-job-o",
    name: "赞",
    type: 3 as MessageType,
    color: "#f39653",
    badge: overview.value.likeUnread || undefined
  }
]);
const loadOverview = async () => {
  const { data } = await getMessageOverview();
  overview.value = data;
};
const goTypeList = (type: MessageType) => {
  router.push({ path: "/messageList", query: { type } });
};
//#endregion

//#region 消息列表（全部类型）
async function loadList(pageInfo: ReqPage) {
  const { data } = await getMessagePage({
    current: pageInfo.current,
    size: pageInfo.size
  });
  if (pageInfo.current === 1) {
    list.value = data.records;
  } else {
    list.value = [...list.value, ...data.records];
  }
  pullDownRefreshContainerRef.value?.setFinished(list.value.length >= data.total);
  loaded.value = true;
}

// 点击单条：标记已读并刷新角标
const onItemClick = async (item: MessageItemInterface) => {
  if (item.isRead) return;
  await markMessageRead({ id: item.id });
  item.isRead = true;
  loadOverview();
};

// 时间显示：今天显示时分，昨天显示「昨天」，其余显示日期
const formatTime = (time: string) => {
  if (!time) return "";
  const d = new Date(time.replace(/-/g, "/"));
  const now = new Date();
  const yesterday = new Date(now);
  yesterday.setDate(now.getDate() - 1);
  if (d.toDateString() === now.toDateString()) return time.substring(11, 16);
  if (d.toDateString() === yesterday.toDateString()) return "昨天";
  return time.substring(0, 10);
};
//#endregion

onMounted(loadOverview);
// tab 页有 keep-alive 缓存，再次进入时刷新角标与列表（跳过首次激活，避免重复加载）
const firstActivated = ref(true);
onActivated(() => {
  if (firstActivated.value) {
    firstActivated.value = false;
    return;
  }
  loadOverview();
  pullDownRefreshContainerRef.value?.onRefresh();
});
</script>
<style lang="less" scoped></style>
