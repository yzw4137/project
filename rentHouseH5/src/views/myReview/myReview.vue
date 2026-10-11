<template>
  <div class="min-h-[100vh] bg-[--color-block-background]">
    <!-- 排序切换 -->
    <van-tabs v-model:active="sortType" sticky line-width="20px">
      <van-tab title="最新" name="latest" />
      <van-tab title="最热" name="hot" />
    </van-tabs>
    <div class="p-[10px]">
      <van-skeleton :row="6" :loading="loading">
        <ReviewCard
          v-for="item in sortedList"
          :key="item.id"
          :data="item"
          @comment="openComment"
          @update="onCardUpdate"
        />
        <van-empty v-if="!loading && sortedList.length === 0" description="还没有发布过评价" />
      </van-skeleton>
    </div>

    <!-- 评论抽屉 -->
    <van-popup
      v-model:show="commentVisible"
      position="bottom"
      :style="{ height: '70%' }"
      round
    >
      <div class="flex flex-col h-full">
        <div class="flex items-center justify-center py-[10px] font-bold border-b border-gray-100 relative">
          <span>评论 ({{ commentList.length }})</span>
          <div class="absolute right-[15px] text-[12px]">
            <span
              :class="commentSortType === 'latest' ? 'text-[var(--van-primary-color)] font-bold' : 'text-gray-400'"
              class="cursor-pointer"
              @click="switchCommentSort('latest')"
              >最新</span
            >
            <span class="mx-[6px] text-gray-300">|</span>
            <span
              :class="commentSortType === 'hot' ? 'text-[var(--van-primary-color)] font-bold' : 'text-gray-400'"
              class="cursor-pointer"
              @click="switchCommentSort('hot')"
              >最热</span
            >
          </div>
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
              <div class="flex items-center mt-[4px] text-[11px] text-gray-400">
                <span>{{ c.createTime }}</span>
                <span
                  class="ml-[12px] flex items-center cursor-pointer"
                  :class="c.isLiked ? 'text-[var(--van-primary-color)]' : ''"
                  @click="onCommentLike(c)"
                >
                  <van-icon :name="c.isLiked ? 'good-job' : 'good-job-o'" size="13" />
                  <span class="ml-[3px]">{{ c.likeCount || 0 }}</span>
                </span>
                <span
                  v-if="c.isMine"
                  class="ml-[12px] cursor-pointer"
                  @click="onDeleteComment(c)"
                  >删除</span
                >
              </div>
            </div>
          </div>
          <van-empty v-if="commentList.length === 0" description="暂无评论" />
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup lang="ts" name="MyReview">
import { computed, onMounted, ref } from "vue";
import { showConfirmDialog, showToast } from "vant";
import defaultAvatar from "../../../public/favicon.ico";
import ReviewCard from "@/components/ReviewCard/ReviewCard.vue";
import {
  getCommentList,
  getMyReviewList,
  removeCommentById,
  toggleCommentLike
} from "@/api/review";
import type { CommentItemInterface, ReviewItemInterface } from "@/api/review/types";

// 原始数据（接口一次返回全部，前端本地排序）
const rawList = ref<ReviewItemInterface[]>([]);
const loading = ref(true);
// 排序方式
const sortType = ref<"latest" | "hot">("latest");

// 排序后的展示列表
const sortedList = computed(() => {
  const arr = [...rawList.value];
  if (sortType.value === "hot") {
    return arr.sort((a, b) => b.likeCount - a.likeCount);
  }
  return arr.sort((a, b) => (a.createTime < b.createTime ? 1 : -1));
});

const loadList = async () => {
  loading.value = true;
  try {
    const { data } = await getMyReviewList();
    rawList.value = data;
  } finally {
    loading.value = false;
  }
};

const onCardUpdate = (updated: ReviewItemInterface) => {
  const idx = rawList.value.findIndex(i => i.id === updated.id);
  if (idx > -1) rawList.value[idx] = updated;
};

//#region 评论查看
const commentVisible = ref(false);
const commentList = ref<CommentItemInterface[]>([]);
const currentReviewId = ref<number | string>("");
const commentSortType = ref<"latest" | "hot">("latest");

const loadComments = async () => {
  const { data } = await getCommentList(currentReviewId.value, commentSortType.value);
  commentList.value = data;
};

const switchCommentSort = (type: "latest" | "hot") => {
  commentSortType.value = type;
  loadComments();
};

const openComment = async (review: ReviewItemInterface) => {
  currentReviewId.value = review.id;
  commentSortType.value = "latest";
  commentVisible.value = true;
  await loadComments();
};

// 评论点赞（乐观更新）
const onCommentLike = async (c: CommentItemInterface) => {
  await toggleCommentLike(c.id);
  const liked = !c.isLiked;
  c.isLiked = liked;
  c.likeCount = (c.likeCount || 0) + (liked ? 1 : -1);
};

// 删除自己的评论
const onDeleteComment = (c: CommentItemInterface) => {
  showConfirmDialog({ title: "提示", message: "确定删除这条评论吗？" })
    .then(async () => {
      await removeCommentById(c.id);
      showToast({ type: "success", message: "已删除" });
      commentList.value = commentList.value.filter(i => i.id !== c.id);
      const target = rawList.value.find(i => i.id === currentReviewId.value);
      if (target) target.commentCount = Math.max(0, (target.commentCount || 0) - 1);
    })
    .catch(() => {});
};
//#endregion

onMounted(loadList);
</script>

<style scoped lang="less"></style>
