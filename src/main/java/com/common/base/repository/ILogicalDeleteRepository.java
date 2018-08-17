package com.common.base.repository;

import com.common.base.domain.BaseUpdateDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 含有逻辑删除操作的基础数据库操作接口
 *
 * @author: gl
 * @date: Created in 5/31/18 12:13 AM
 * @modify by:
 */
@NoRepositoryBean
public interface ILogicalDeleteRepository<T extends BaseUpdateDO, ID extends Serializable> extends
    IBaseRepository<T, ID> {

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update #{#entityName} u set u.delFlag = ?2, u.updateBy = ?3 where u.id = ?1")
  int updateByDeleted(ID id, boolean deleted, String userId);


  @Deprecated
  @Override
  default void delete(T entity) {
  }

  @Deprecated
  @Override
  default void deleteById(ID id) {
  }

  @Deprecated
  @Override
  default void deleteAll(Iterable<? extends T> entities) {
  }

  @Deprecated
  @Override
  default void deleteAll() {
  }
}
