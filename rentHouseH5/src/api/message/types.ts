import type { ReqPage } from "@/api/types";

// 消息类型：1 系统通知 2 评论 3 点赞
export type MessageType = 1 | 2 | 3;

// 消息项
export interface MessageItemInterface {
  id: number | string;
  type: MessageType;
  senderId: number | string | null;
  nickname: string;
  avatarUrl: string;
  content: string;
  bizType: string | null;
  bizId: number | string | null;
  isRead: boolean;
  createTime: string;
}

// 三类消息未读数概览
export interface MessageOverviewInterface {
  noticeUnread: number;
  commentUnread: number;
  likeUnread: number;
}

// 分页请求参数
export interface MessageQueryInterface extends ReqPage {
  type?: MessageType;
}

// 标记已读请求体
export interface MessageReadInterface {
  id?: number | string;
  type?: MessageType;
}
