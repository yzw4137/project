-- ============================================================
-- 评价（圈子）模块建表脚本
-- 数据库：MySQL 8.x  字符集：utf8mb4
-- 说明：点赞数以 Redis ZSet 为准，表中 like_count 仅作持久化兜底，
--       由定时任务从 Redis 同步回写。
-- ============================================================

-- ----------------------------
-- 1. 房源评价表
-- ----------------------------
DROP TABLE IF EXISTS `review_info`;
CREATE TABLE `review_info` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id`       BIGINT       NOT NULL COMMENT '发布用户id',
  `apartment_id`  BIGINT       NOT NULL COMMENT '关联公寓id',
  `room_id`       BIGINT       NOT NULL COMMENT '关联房间id',
  `agreement_id`  BIGINT       NOT NULL COMMENT '关联租约id',
  `content`       VARCHAR(500) NOT NULL COMMENT '评价正文',
  `rating`        TINYINT      NOT NULL COMMENT '评分 1~5',
  `like_count`    INT          NOT NULL DEFAULT 0 COMMENT '点赞数（兜底，最终以 Redis 为准）',
  `comment_count` INT          NOT NULL DEFAULT 0 COMMENT '评论数',
  `create_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`    TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除 1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_agreement_id` (`agreement_id`) COMMENT '同一租约仅允许发布一条评价',
  KEY `idx_user_id` (`user_id`),
  KEY `idx_apartment_id` (`apartment_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房源评价表';

-- ----------------------------
-- 2. 评价图片表
-- ----------------------------
DROP TABLE IF EXISTS `review_graph`;
CREATE TABLE `review_graph` (
  `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `review_id`  BIGINT       NOT NULL COMMENT '所属评价id',
  `url`        VARCHAR(255) NOT NULL COMMENT '图片地址',
  `name`       VARCHAR(64)  DEFAULT NULL COMMENT '图片名称',
  PRIMARY KEY (`id`),
  KEY `idx_review_id` (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价图片表';

-- ----------------------------
-- 3. 评价点赞记录表（用于 isLiked 判断与防重复点赞）
-- ----------------------------
DROP TABLE IF EXISTS `review_like`;
CREATE TABLE `review_like` (
  `id`          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `review_id`   BIGINT   NOT NULL COMMENT '评价id',
  `user_id`     BIGINT   NOT NULL COMMENT '点赞用户id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_review_user` (`review_id`, `user_id`) COMMENT '同一用户对同一评价仅一条点赞记录'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价点赞记录表';

-- ----------------------------
-- 4. 评价评论表
-- ----------------------------
DROP TABLE IF EXISTS `review_comment`;
CREATE TABLE `review_comment` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `review_id`   BIGINT       NOT NULL COMMENT '所属评价id',
  `user_id`     BIGINT       NOT NULL COMMENT '评论用户id',
  `parent_id`   BIGINT       DEFAULT NULL COMMENT '被回复的评论id，顶级评论为 NULL',
  `content`     VARCHAR(500) NOT NULL COMMENT '评论内容',
  `like_count`  INT          NOT NULL DEFAULT 0 COMMENT '评论点赞数（兜底）',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_review_id` (`review_id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价评论表';

-- ----------------------------
-- 5. 评论点赞记录表
-- ----------------------------
DROP TABLE IF EXISTS `review_comment_like`;
CREATE TABLE `review_comment_like` (
  `id`          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `comment_id`  BIGINT   NOT NULL COMMENT '评论id',
  `user_id`     BIGINT   NOT NULL COMMENT '点赞用户id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_comment_user` (`comment_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论点赞记录表';
