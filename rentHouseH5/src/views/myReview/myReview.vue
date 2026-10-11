<template>
  <div class="min-h-[100vh] bg-[--color-block-background] p-[10px]">
    <van-skeleton :row="6" :loading="loading">
      <ReviewCard
        v-for="item in list"
        :key="item.id"
        :data="item"
        @comment="openComment"
        @update="onCardUpdate"
      />
      <van-empty v-if="!loading && list.length === 0" description="还没有发布过评价" />
    </van-skeleton>

    <!-- 评论抽屉（与圈子页一致） -->
    <van-popup
      v-model:show="commentVisible"
      position="bottom"
      :style="{ height: '70%' }"
      round
    >
      <div class="flex flex-col h-full">
        <div class="text-center py-[10px] font-bold border-b border-gray-100">
          评论 ({{ commentList.length }})
        </div>
        <div class="flex-1 overflow-y-auto p-[10px]">
          <div
            v-for="c in commentList"
            :key="c.id"
            class="flex py-[8px] border-b border-gray-50"
          >
            <van-image round width="32" height="32" :src="c.avatarUrl || defaultAvatar" />
            <div class="ml-[8px] flex-1">
              <div class="text-[12px] text-gray-500">{{ c.nickname }}</div>
              <div class="text-[14px] mt-[2px]">
                <span v-if="c.replyToNickname" class="text-[var(--van-primary-color)]"
                  >回复 {{ c.replyToNickname }}：</span
                >{{ c.content }}
              </div>
              <div class="text-[11px] text-gray-400 mt-[2px]">{{ c.createTime }}</div>
            </div>
          </div>
          <van-empty v-if="commentList.length === 0" description="暂无评论" />
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup lang="ts" name="MyReview">
import { onMounted, ref } from "vue";
import defaultAvatar from "../../../public/favicon.ico";
import ReviewCard from "@/components/ReviewCard/ReviewCard.vue";
import { getCommentList, getMyReviewList } from "@/api/review";
import type { CommentItemInterface, ReviewItemInterface } from "@/api/review/types";

const list = ref<ReviewItemInterface[]>([]);
const loading = ref(true);

const loadList = async () => {
  loading.value = true;
  try {
    const { data } = await getMyReviewList();
    list.value = data;
  } finally {
    loading.value = false;
  }
};

const onCardUpdate = (updated: ReviewItemInterface) => {
  const idx = list.value.findIndex(i => i.id === updated.id);
  if (idx > -1) list.value[idx] = updated;
};

//#region 评论查看
const commentVisible = ref(false);
const commentList = ref<CommentItemInterface[]>([]);

const openComment = async (review: ReviewItemInterface) => {
  commentVisible.value = true;
  const { data } = await getCommentList(review.id);
  commentList.value = data;
};
//#endregion

onMounted(loadList);
</script>

<style scoped lang="less"></style>
