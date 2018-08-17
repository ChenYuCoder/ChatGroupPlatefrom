package com.group;

import com.common.base.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component public interface ChatGroupRepository extends IBaseRepository<ChatGroup, Integer> {

  /**
   * 更新聊天群状态
   *
   * @param id     聊天群主键
   * @param status 状态
   * @return 更新记录条数
   */
  @Modifying @Query("update ChatGroup c set c.status = ?2 where c.id = ?1") int updateByStatus(
    int id, int status);

}
