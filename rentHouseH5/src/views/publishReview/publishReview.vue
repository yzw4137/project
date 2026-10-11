<template>
  <div class="min-h-[100vh] bg-[--color-block-background] p-[15px]">
    <!-- 房源信息 -->
    <van-card class="rounded-xl shadow">
      <template #title>
        <h2 class="text-[15px] font-bold">
          {{ apartmentName }} · {{ roomNumber }}房间
        </h2>
      </template>
      <template #thumb>
        <van-image
          class="w-full h-full object-cover"
          :src="graphUrl || '失败'"
        >
          <template v-slot:error>加载失败</template>
        </van-image>
      </template>
    </van-card>

    <!-- 评价表单 -->
    <van-form @submit="onSubmit" class="mt-[15px]">
      <van-cell-group inset>
        <!-- 评分 -->
        <van-cell title="总体评分" center>
          <template #value>
            <van-rate v-model="formData.rating" :size="24" />
          </template>
        </van-cell>
        <!-- 评价内容 -->
        <van-field
          v-model.trim="formData.content"
          type="textarea"
          rows="4"
          autosize
          maxlength="500"
          show-word-limit
          label="评价内容"
          placeholder="分享一下入住体验吧~"
          :rules="[{ required: true, message: '请填写评价内容' }]"
        />
        <!-- 图片上传（占位，后端接口就绪后接入） -->
        <van-cell title="上传图片" is-link @click="showToast('图片上传功能待后端接口就绪后开放')">
          <template #value>
            <span class="text-gray-400">{{ formData.graphVoList?.length || 0 }}/9</span>
          </template>
        </van-cell>
      </van-cell-group>
      <div class="mt-[50px] main-container">
        <van-button round block type="primary" native-type="submit" :loading="submitting">
          发布评价
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<script setup lang="ts" name="PublishReview">
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { showToast } from "vant";
import { saveReview } from "@/api/review";
import type { ReviewSubmitInterface } from "@/api/review/types";

const route = useRoute();
const router = useRouter();

const apartmentName = ref(route.query.apartmentName as string || "");
const roomNumber = ref(route.query.roomNumber as string || "");
const graphUrl = ref(route.query.graphUrl as string || "");

const formData = ref<ReviewSubmitInterface>({
  agreementId: route.query.agreementId as string,
  rating: 5,
  content: "",
  graphVoList: []
});

const submitting = ref(false);

const onSubmit = async () => {
  submitting.value = true;
  try {
    await saveReview(formData.value);
    showToast({ type: "success", message: "发布成功" });
    router.replace("/myReview");
  } finally {
    submitting.value = false;
  }
};
</script>

<style scoped lang="less"></style>
