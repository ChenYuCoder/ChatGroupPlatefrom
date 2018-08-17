package com.common.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * 基础更新类型POJO对象
 *
 * @author: gl
 * @date: Created in 5/30/18 11:47 PM
 * @modify by:
 */
@MappedSuperclass
public abstract class BaseUpdateDO extends BaseCreateDO {
  /**
   * 删除标志位 0:未删除 1:已删除
   */
  @Column(name = "del_flag")
  private boolean delFlag = false;

  /**
   * 最后更新者
   */
  @Column(name = "update_by", nullable = false)
  private String updateBy = null;

  /**
   * 最后更新时间
   */
  @Column(name = "update_date", insertable = false, updatable = false)
  private Timestamp updateDate = null;

  public boolean isDelFlag() {
    return delFlag;
  }

  public void setDelFlag(boolean delFlag) {
    this.delFlag = delFlag;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public String toString() {
    return "BaseUpdateDO{" +
        "delFlag=" + delFlag +
        ", updateBy='" + updateBy + '\'' +
        ", updateDate=" + updateDate +
        "} " + super.toString();
  }
}
