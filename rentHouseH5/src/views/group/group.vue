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
import { ref } from "vue";
import { showToast } from "vant";
import bgImgUrl from "@/assets/group_bg.png";
import defaultAvatar from "../../../public/favicon.ico";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
import ReviewCard from "@/components/ReviewCard/ReviewCard.vue";
import {
  getCommentList,
  getReviewPage,
  saveComment
} from "@/api/review";
import type { CommentItemInterface, ReviewItemInterface } from "@/api/review/types";
import type { ReqPage } from "@/api/types";

const pullDownRefreshContainerRef =
  ref<InstanceType<typeof PullDownRefreshContainer>>();

const list = ref<ReviewItemInterface[]>([]);

// 分页加载评价列表
async function loadList(pageInfo: ReqPage) {
  const { data } = await getReviewPage({ current: pageInfo.current, size: pageInfo.size });
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

const openComment = async (review: ReviewItemInterface) => {
  currentReviewId.value = review.id;
  commentVisible.value = true;
  commentInput.value = "";
  await loadComments();
};

const loadComments = async () => {
  const { data } = await getCommentList(currentReviewId.value);
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

//#endregion
</script>

<style lang="less" scoped></style>
