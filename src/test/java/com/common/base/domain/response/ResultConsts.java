package com.common.base.domain.response;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

/**
 * 基础返回结果对象常量
 *
 * @author: gl
 * @date: Created in 6/6/18 10:39 AM
 * @modify by:
 */
public class ResultConsts {
  /**
   * 返回结果文档字段描述 不包含结果数据"data"文档数据类型,因为他是泛型
   */
  private static FieldDescriptor[] RESULT = new FieldDescriptor[]{
      fieldWithPath("code").type(JsonFieldType.NUMBER).description("返回码"),
      fieldWithPath("errorMessage").type(JsonFieldType.STRING).description("错误描述信息"),
  };

  private ResultConsts() {
  }

  /**
   * 获取基础返回结果文档字段描述集合 不包含结果数据"data"文档数据类型,因为他是泛型
   *
   * @return 基础返回结果文档字段描述集合
   */
  public static List<FieldDescriptor> getResultFieldDescriptor() {
    List<FieldDescriptor> result = new LinkedList<>();

    Collections.addAll(result, RESULT);

    return result;
  }
}
