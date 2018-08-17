package com.common.enums;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 局部更新操作枚举对象
 *
 * @author: gl
 * @date: Created in 5/31/18 7:24 PM
 * @modify by:
 */
public enum PatchOperateEnum {
  /**
   * 生效
   */
  EFFECTIVE,

  /**
   * 失效
   */
  INEFFECTIVE;

  private static final Map<String, PatchOperateEnum> mappings = new HashMap<>(2);

  static {
    for (PatchOperateEnum patchOperateEnum : values()) {
      mappings.put(patchOperateEnum.name().replaceAll("_", "-").toLowerCase(), patchOperateEnum);
    }
  }

  /**
   * 获取局部更新操作枚举对象
   *
   * @param operate 操作值
   * @return 操作枚举对象
   */
  public static PatchOperateEnum getEnum(String operate) {
    return StringUtils.isEmpty(operate) ? null : mappings.get(operate);
  }

  /**
   * 检测操作值是否有效
   *
   * @param operate 操作值
   * @return true:有效
   */
  public static boolean valid(String operate) {
    return null != getEnum(operate);
  }
}
