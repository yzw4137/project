<template>
  <div class="bg-[--color-block-background] min-h-[100vh]">
    <PullDownRefreshContainer
      :request="loadList"
      ref="pullDownRefreshContainerRef"
      class="min-h-[70vh]"
    >
      <van-image :src="bgImgUrl">
        <template v-slot:error>加载失败</template>
        <template v-slot:loading>
          <van-loading type="spinner" size="20" />
        </template>
      </van-image>
      <!-- 排序切换 -->
      <van-tabs v-model:active="sortType" sticky line-width="20px">
        <van-tab title="最新" name="latest" />
        <van-tab title="最热" name="hot" />
      </van-tabs>
      <van-skeleton :row="6" :loading="list.length === 0">
        <ReviewCard
          v-for="item in list"
          :key="item.id"
          :data="item"
          @comment="openComment"
          @update="onCardUpdate"
        />
        <van-empty v-if="list.length === 0" description="还没有评价，快去发布第一条吧" />
      </van-skeleton>
    </PullDownRefreshContainer>

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
          <!-- 评论排序切换 -->
          <div class="absolute right-[15px] text-[12px]">
            <span
              :class="commentSortType === 'latest' ? 'text-[var(--van-primary-color)] font-bold' : 'text-gray-400'"
              class="cursor-pointer"
              @click="commentSortType = 'latest'"
              >最新</span
            >
            <span class="mx-[6px] text-gray-300">|</span>
            <span
              :class="commentSortType === 'hot' ? 'text-[var(--van-primary-color)] font-bold' : 'text-gray-400'"
              class="cursor-pointer"
              @click="commentSortType = 'hot'"
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
          <van-empty v-if="commentList.length === 0" description="暂无评论，快来抢沙发" />
        </div>
        <!-- 评论输入框 -->
        <div class="p-[10px] border-t border-gray-100 flex items-center">
          <van-field
            v-model="commentInput"
            placeholder="说点什么..."
            :border="false"
            class="flex-1"
          />
          <van-button type="primary" size="small" :loading="submitting" @click="submitComment">
            发送
          </van-button>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup lang="ts" name="Group">
import { ref, watch } from "vue";
import { showConfirmDialog, showToast } from "vant";
import bgImgUrl from "@/assets/group_bg.png";
import defaultAvatar from "../../../public/favicon.ico";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
import ReviewCard from "@/components/ReviewCard/ReviewCard.vue";
import {
  getCommentList,
  getReviewPage,
  removeCommentById,
  saveComment,
  toggleCommentLike
} from "@/api/review";
import type { CommentItemInterface, ReviewItemInterface } from "@/api/review/types";
import type { ReqPage } from "@/api/types";

const pullDownRefreshContainerRef =
  ref<InstanceType<typeof PullDownRefreshContainer>>();

const list = ref<ReviewItemInterface[]>([]);
// 排序方式
const sortType = ref<"latest" | "hot">("latest");

// 切换排序时重置分页并重新加载
watch(sortType, () => {
  pullDownRefreshContainerRef.value?.onRefresh();
});

// 分页加载评价列表
async function loadList(pageInfo: ReqPage) {
  const { data } = await getReviewPage({
    current: pageInfo.current,
    size: pageInfo.size,
    sortType: sortType.value
  });
  if (pageInfo.current === 1) {
    list.value = data.records;
  } else {
    list.value = [...list.value, ...data.records];
  }
  pullDownRefreshContainerRef.value?.setFinished(list.value.length >= data.total);
}

// 点赞后同步列表状态
const onCardUpdate = (updated: ReviewItemInterface) => {
  const idx = list.value.findIndex(i => i.id === updated.id);
  if (idx > -1) list.value[idx] = updated;
};

//#region 评论相关
const commentVisible = ref(false);
const currentReviewId = ref<number | string>("");
const commentList = ref<CommentItemInterface[]>([]);
const commentInput = ref("");
const submitting = ref(false);
// 评论排序方式
const commentSortType = ref<"latest" | "hot">("latest");

// 切换评论排序时重新加载
watch(commentSortType, () => {
  if (commentVisible.value) loadComments();
});

const openComment = async (review: ReviewItemInterface) => {
  currentReviewId.value = review.id;
  commentVisible.value = true;
  commentInput.value = "";
  commentSortType.value = "latest";
  await loadComments();
};

const loadComments = async () => {
  const { data } = await getCommentList(currentReviewId.value, commentSortType.value);
  commentList.value = data;
};

const submitComment = async () => {
  if (!commentInput.value.trim()) {
    showToast("请输入评论内容");
    return;
  }
  submitting.value = true;
  try {
    await saveComment({
      reviewId: currentReviewId.value,
      content: commentInput.value.trim()
    });
    commentInput.value = "";
    showToast({ type: "success", message: "评论成功" });
    await loadComments();
    // 同步评论数
    const target = list.value.find(i => i.id === currentReviewId.value);
    if (target) target.commentCount = commentList.value.length;
  } finally {
    submitting.value = false;
  }
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
      const target = list.value.find(i => i.id === currentReviewId.value);
      if (target) target.commentCount = Math.max(0, (target.commentCount || 0) - 1);
    })
    .catch(() => {});
};

//#endregion
</script>

<style lang="less" scoped></style>
