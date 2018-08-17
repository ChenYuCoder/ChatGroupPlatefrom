package com.group;

/**
 * 聊天群组状态
 */
public enum ChatGroupStatusEnum {
  /**
   * 未验证
   */
  NOT_VALIDATE(-1),

  /**
   * 已验证
   */
  VALIDATE(1),

  /**
   * 过期
   */
  OVERDUE(2);

  private int code;

  ChatGroupStatusEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }
}
