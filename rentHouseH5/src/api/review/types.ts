import type { ReqPage } from "@/api/types";

// 评价图片信息
export interface ReviewGraphVo {
  url: string;
  name: string;
}

// 评价列表项（圈子/我的评价共用）
export interface ReviewItemInterface {
  id: number | string;
  // 发布用户信息
  userId: number | string;
  nickname: string;
  avatarUrl: string;
  // 评价内容
  content: string;
  // 评分 1-5
  rating: number;
  // 评价图片列表
  graphVoList: ReviewGraphVo[];
  // 关联房源信息
  apartmentId: number | string;
  apartmentName: string;
  roomId: number | string;
  roomNumber: string;
  // 关联租约id
  agreementId: number | string;
  // 点赞数
  likeCount: number;
  // 当前用户是否已点赞
  isLiked: boolean;
  // 评论数
  commentCount: number;
  // 发布时间
  createTime: string;
}

// 评价查询参数（圈子分页）
export interface ReviewQueryInterface extends ReqPage {
  // 可选：按公寓id筛选
  apartmentId?: number | string;
}

// 发布评价请求参数
export interface ReviewSubmitInterface {
  // 租约id（必传，用于校验评价权限与房源关联）
  agreementId: number | string;
  // 评分 1-5
  rating: number;
  // 评价内容
  content: string;
  // 评价图片列表（可选）
  graphVoList?: ReviewGraphVo[];
}

// 评论项
export interface CommentItemInterface {
  id: number | string;
  reviewId: number | string;
  userId: number | string;
  nickname: string;
  avatarUrl: string;
  content: string;
  createTime: string;
  // 被回复的评论id，顶级评论为 null
  parentId: number | string | null;
  // 被回复用户昵称（用于展示 "回复 xxx:"）
  replyToNickname?: string;
}

// 发布评论请求参数
export interface CommentSubmitInterface {
  reviewId: number | string;
  content: string;
  // 回复的评论id，不传则为顶级评论
  parentId?: number | string;
}
