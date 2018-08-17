package com.group;

import com.common.base.domain.response.ResultDO;
import org.springframework.data.domain.Page;

public interface IChatGroupService {
  /**
   * 查询群组数据
   *
   * @param chatGroupParams 查询条件
   * @return 群组数据
   */
  ResultDO<Page<ChatGroup>> get(ChatGroupParams chatGroupParams);

  /**
   * 创建群组
   *
   * @param chatGroup 群组对象
   * @return 主键
   */
  ResultDO<Integer> create(ChatGroup chatGroup);

  /**
   * 更新群组
   *
   * @param chatGroup 群组对象
   * @return 执行结果
   */
  ResultDO update(ChatGroup chatGroup);

  /**
   * 局部更新群组
   *
   * @param operate   操作值
   * @param chatGroup 群组对象
   * @return 执行结果
   */
  ResultDO patch(String operate, ChatGroup chatGroup);

  /**
   * 删除群组
   *
   * @param id 群组id
   * @return 执行结果
   */
  ResultDO delete(String id);
}
