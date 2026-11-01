import http from "@/utils/http";
import type { PageRes } from "@/api/types";
import type {
  MessageItemInterface,
  MessageOverviewInterface,
  MessageQueryInterface,
  MessageReadInterface,
  MessageType
} from "./types";

/**
 * @description 查询三类消息未读数概览（角标）
 */
export function getMessageOverview() {
  return http.get<MessageOverviewInterface>(`/app/message/overview`);
}

/**
 * @description 分页查询消息列表（type 不传为全部类型）
 */
export function getMessagePage(params: Partial<MessageQueryInterface>) {
  return http.get<PageRes<MessageItemInterface>>(`/app/message/pageItem`, params);
}

/**
 * @description 标记已读（单条传 id，按类型全部传 type）
 */
export function markMessageRead(params: MessageReadInterface) {
  return http.post(`/app/message/read`, params);
}

/**
 * @description 进入分类页：将该类型消息全部标记已读
 */
export function markTypeAllRead(type: MessageType) {
  return markMessageRead({ type });
}

/**
 * @description 删除消息
 */
export function removeMessageById(id: number | string) {
  return http.delete(`/app/message/removeById?id=${id}`);
}
