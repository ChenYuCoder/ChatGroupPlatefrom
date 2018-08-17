package com.common.base.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * 基础数据库操作接口
 *
 * @author: gl
 * @date: Created in 5/31/18 12:09 AM
 * @modify by:
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends
    PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {}
