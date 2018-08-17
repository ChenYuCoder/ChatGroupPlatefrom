package com.common.base.domain.response;

/**
 * 返回码枚举对象
 *
 * @author: gl
 * @date: Created in 5/31/18 10:04 AM
 * @modify by:
 */
public enum ResultCodeEnum {
  SUCCESS(0, ""), FAILED(100999, "%s操作失败"), UNKNOWN_ERROR(999999, "请联系系统管理员"),
  INPUT_VALUE_ERROR(100101, "必录项目未录入"), INPUT_VALUE_ILLEGAL(100102, "%s无效"),
  OBJECT_EXISTS(100103, "%s已经存在"), OBJECT_NOT_EXISTS(100104, "%s不存在"),
  OBJECT_NO_UPDATE(100105, "%s,不允许进行修改操作"), OBJECT_NO_DELETE(100106, "%s,不允许进行删除操作");

  /**
   * 返回码
   */
  private final int code;

  /**
   * 描述信息
   */
  private final String message;

  private ResultCodeEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * 获取返回码
   *
   * @return 返回码
   */
  public int getCode() {
    return code;
  }

  /**
   * 获取描述信息
   *
   * @param args 需要进行宏替换参数
   * @return 描述信息
   */
  public String getMessage(Object... args) {
    return String.format(message, args);
  }

  @Override
  public String toString() {
    return "ResultCodeEnum{" +
        "code=" + code +
        ", message='" + message + '\'' +
        "} " + super.toString();
  }
}
