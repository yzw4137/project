import http from "@/utils/http";
import type { PageRes } from "@/api/types";
import type {
  CommentItemInterface,
  CommentSubmitInterface,
  ReviewItemInterface,
  ReviewQueryInterface,
  ReviewSubmitInterface
} from "./types";

/**
 * @description 分页查询评价列表（圈子，所有人的评价）
 */
export function getReviewPage(params: Partial<ReviewQueryInterface>) {
  return http.get<PageRes<ReviewItemInterface>>(`/app/review/pageItem`, params);
}

/**
 * @description 查询当前用户发布的评价列表（我的评价）
 */
export function getMyReviewList() {
  return http.get<ReviewItemInterface[]>(`/app/review/listMyItem`);
}

/**
 * @description 根据id查询评价详情（含评论列表）
 * @param id 评价id
 */
export function getReviewDetailById(id: number | string) {
  return http.get<ReviewItemInterface>(`/app/review/getDetailById?id=${id}`);
}

/**
 * @description 发布评价
 */
export function saveReview(params: ReviewSubmitInterface) {
  return http.post(`/app/review/save`, params);
}

/**
 * @description 点赞 / 取消点赞评价
 * @param id 评价id
 */
export function toggleReviewLike(id: number | string) {
  return http.post(`/app/review/like`, { id });
}

/**
 * @description 查询评价的评论列表
 * @param reviewId 评价id
 */
export function getCommentList(reviewId: number | string) {
  return http.get<CommentItemInterface[]>(
    `/app/review/comment/list?reviewId=${reviewId}`
  );
}

/**
 * @description 发表评论
 */
export function saveComment(params: CommentSubmitInterface) {
  return http.post(`/app/review/comment/save`, params);
}

/**
 * @description 删除评论（仅本人可删除）
 * @param id 评论id
 */
export function removeCommentById(id: number | string) {
  return http.delete(`/app/review/comment/removeById?id=${id}`);
}
