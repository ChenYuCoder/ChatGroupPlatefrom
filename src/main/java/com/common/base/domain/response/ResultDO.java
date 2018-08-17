package com.common.base.domain.response;

/**
 * 返回给用户的结果对象
 *
 * @author: gl
 * @date: Created in 5/31/18 10:01 AM
 * @modify by:
 */
public class ResultDO<T> {
  /**
   * 不含数据的成功返回值对象
   */
  public static final ResultDO SUCCESS_NO_DATA = new ResultDO<>(null);

  /**
   * 未知错误的失败返回值对象
   */
  public static final ResultDO FAILED_UNKNOWN = new ResultDO<>(null, ResultCodeEnum.UNKNOWN_ERROR);

  /**
   * 返回码
   */
  private final int code;

  /**
   * 错误描述信息
   */
  private final String errorMessage;

  /**
   * 结果数据
   */
  private final T data;

  /**
   * 创建成功返回结果对象
   *
   * @param data 数据对象
   */
  public ResultDO(T data) {
    this(data, ResultCodeEnum.SUCCESS);
  }

  /**
   * 创建返回结果对象
   *
   * @param data 数据对象
   * @param resultCodeEnum 返回值枚举对象
   * @param messageParams 返回消息宏替换参数
   */
  public ResultDO(T data, ResultCodeEnum resultCodeEnum, Object... messageParams) {
    this(resultCodeEnum.getCode(), resultCodeEnum.getMessage(messageParams), data);
  }

  private ResultDO(int code, String errorMessage, T data) {
    this.code = code;
    this.errorMessage = errorMessage;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    return "ResultDO{" +
        "code=" + code +
        ", errorMessage='" + errorMessage + '\'' +
        ", data=" + data +
        '}';
  }
}
