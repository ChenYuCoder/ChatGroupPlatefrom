package com.common.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * 基础创建类型POJO对象
 *
 * @author: gl
 * @date: Created in 5/30/18 11:42 PM
 * @modify by:
 */
@MappedSuperclass
public abstract class BaseCreateDO implements IBaseDO {
  /**
   * 创建者
   */
  @Column(name = "create_by", nullable = false)
  private String createBy = null;

  /**
   * 创建时间
   */
  @Column(name = "create_date", insertable = false, updatable = false)
  private Timestamp createDate = null;

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "BaseCreateDO{" +
        "createBy='" + createBy + '\'' +
        ", createDate=" + createDate +
        '}';
  }
}
