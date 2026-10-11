<template>
  <div class="bg-[--color-background-3] main-container py-[10px] mt-[15px] rounded-xl">
    <!-- 头部：头像、昵称、时间 -->
    <div class="flex items-center">
      <van-image round width="12vw" height="12vw" :src="data.avatarUrl || defaultAvatar">
        <template v-slot:error>加载失败</template>
        <template v-slot:loading>
          <van-loading type="spinner" size="20" />
        </template>
      </van-image>
      <div class="flex flex-col justify-center ml-[10px] text-[12px] flex-1">
        <div class="font-bold">{{ data.nickname }}</div>
        <div class="text-gray-500">{{ data.createTime }}</div>
      </div>
      <!-- 评分 -->
      <van-rate v-model="ratingDisplay" readonly allow-half size="14" />
    </div>
    <!-- 关联房源 -->
    <div
      class="mt-[8px] text-[12px] text-[var(--van-primary-color)]"
      @click="goApartmentDetail"
    >
      {{ data.apartmentName }} · {{ data.roomNumber }}房间
    </div>
    <!-- 正文 -->
    <div class="mt-[8px] text-[14px] leading-relaxed">{{ data.content }}</div>
    <!-- 图片 -->
    <van-row v-if="data.graphVoList?.length" class="mt-[8px]">
      <van-col :span="8" v-for="(img, idx) in data.graphVoList" :key="idx">
        <van-image
          class="rounded-md"
          width="25vw"
          height="25vw"
          :src="img.url"
          @click="previewImage(idx)"
        >
          <template v-slot:error>加载失败</template>
          <template v-slot:loading>
            <van-loading type="spinner" size="20" />
          </template>
        </van-image>
      </van-col>
    </van-row>
    <!-- 点赞、评论 -->
    <div class="flex mt-[10px] text-[13px]">
      <div class="flex items-center cursor-pointer" @click="onLike">
        <van-icon
          :name="data.isLiked ? 'good-job' : 'good-job-o'"
          size="18"
          :color="data.isLiked ? 'var(--van-primary-color)' : ''"
        />
        <span class="ml-[5px]">{{ data.likeCount }}</span>
      </div>
      <div class="flex items-center ml-[15px] cursor-pointer" @click="onComment">
        <van-icon name="comment-o" size="18" />
        <span class="ml-[5px]">{{ data.commentCount }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { useRouter } from "vue-router";
import { showImagePreview } from "vant";
import defaultAvatar from "../../../public/favicon.ico";
import type { ReviewItemInterface } from "@/api/review/types";
import { toggleReviewLike } from "@/api/review";
import { showToast } from "vant";

const props = defineProps<{
  data: ReviewItemInterface;
}>();

const emit = defineEmits<{
  (e: "comment", review: ReviewItemInterface): void;
  (e: "update", review: ReviewItemInterface): void;
}>();

const router = useRouter();

const ratingDisplay = computed(() => props.data.rating);

const goApartmentDetail = () => {
  router.push({
    path: "/apartmentDetail",
    query: { id: props.data.apartmentId }
  });
};

const previewImage = (idx: number) => {
  showImagePreview({
    images: props.data.graphVoList.map(g => g.url),
    startPosition: idx
  });
};

const onLike = async () => {
  await toggleReviewLike(props.data.id);
  // 本地乐观更新
  props.data.isLiked = !props.data.isLiked;
  props.data.likeCount += props.data.isLiked ? 1 : -1;
  emit("update", { ...props.data });
};

const onComment = () => {
  emit("comment", { ...props.data });
};
</script>

<style scoped lang="less"></style>
