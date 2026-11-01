<template>
  <div>
    <van-nav-bar :title="title" left-arrow @click-left="router.back()" />
    <PullDownRefreshContainer
      :request="loadList"
      ref="pullDownRefreshContainerRef"
      class="min-h-[80vh]"
    >
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

<script setup lang="ts" name="MessageList">
import { computed, onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
import { getMessagePage, markMessageRead, markTypeAllRead } from "@/api/message";
import type { MessageItemInterface, MessageType } from "@/api/message/types";
import type { ReqPage } from "@/api/types";
import defaultAvatar from "../../../public/favicon.ico";

const route = useRoute();
const router = useRouter();
const type = Number(route.query.type || 0) as MessageType;
const titleMap: Record<number, string> = { 1: "通知", 2: "评论", 3: "赞" };
const title = computed(() => titleMap[type] || "消息");

const list = ref<MessageItemInterface[]>([]);
const loaded = ref(false);
const pullDownRefreshContainerRef = ref<InstanceType<
  typeof PullDownRefreshContainer
> | null>(null);

// 分页加载消息列表
async function loadList(pageInfo: ReqPage) {
  const { data } = await getMessagePage({
    current: pageInfo.current,
    size: pageInfo.size,
    type
  });
  if (pageInfo.current === 1) {
    list.value = data.records;
  } else {
    list.value = [...list.value, ...data.records];
  }
  pullDownRefreshContainerRef.value?.setFinished(list.value.length >= data.total);
  loaded.value = true;
}

// 点击单条：标记已读
const onItemClick = async (item: MessageItemInterface) => {
  if (item.isRead) return;
  await markMessageRead({ id: item.id });
  item.isRead = true;
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

onMounted(async () => {
  // 进入分类页，该类型消息全部标记已读（角标清零）
  if (type) {
    await markTypeAllRead(type);
  }
});
</script>
<style lang="less" scoped></style>
