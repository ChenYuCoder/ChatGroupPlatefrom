package com.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 对象转换工具类
 *
 * @author: gl
 * @date: Created in 8/17/18 2:28 PM
 * @modify by:
 */
public class ConverterUtil {
  private static final Log log = LogFactory.getLog(ConverterUtil.class);

  private ConverterUtil() {}

  /**
   * 将参数集合转换成实体对象
   *
   * @param params 参数集合对象
   * @param typeClass 实体类型
   * @param <T>
   * @return 实体对象 null:转换失败
   */
  public static <T> T mapToObject(Map<String, List<String>> params, Class<? extends T> typeClass) {
    Assert.notNull(params, "params object must not be null");
    Assert.notNull(typeClass, "bean class type must not be null");

    try {
      Constructor<T> constructor = (Constructor<T>) ReflectionUtils.accessibleConstructor(typeClass);
      T result = constructor.newInstance();

      params.forEach((name, values) -> {
        if (CollectionUtils.isEmpty(values)) return;

        Field field = ReflectionUtils.findField(typeClass, name);
        if (null == field) return;

        if (1 < values.size()) {
          ReflectionUtils.setField(field, result, values);
          return;
        }

        String value = values.get(0);
        if (StringUtils.isEmpty(value)) return;

        ReflectionUtils.makeAccessible(field);
        Type type = field.getGenericType();
        if (Integer.TYPE == type || Integer.class == type) ReflectionUtils.setField(field, result, Integer.parseInt(value));
        else if (Long.TYPE == type || Long.class == type) ReflectionUtils.setField(field, result, Long.parseLong(value));
        else if (Float.TYPE == type || Float.class == type) ReflectionUtils.setField(field, result, Float.parseFloat(value));
        else if (Double.TYPE == type || Double.class == type) ReflectionUtils.setField(field, result, Double.parseDouble(value));
        else if (Boolean.TYPE == type || Boolean.class == type) ReflectionUtils.setField(field, result, Boolean.valueOf(value));
        else if (Character.TYPE == type || Character.class == type) ReflectionUtils.setField(field, result, value.charAt(0));
        else ReflectionUtils.setField(field, result, value);
      });

      return result;
    } catch (Exception e) {
      log.error(String.format("%s convert error.", typeClass), e);
    }

    return null;
  }
}
