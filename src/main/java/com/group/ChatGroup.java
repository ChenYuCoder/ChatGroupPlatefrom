package com.group;

import com.common.base.domain.IBaseDO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 聊天群实体对象
 */
@Entity @Table(name = "chat_group") public class ChatGroup implements IBaseDO {
  /**
   * 聊天群ID(自增)
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id = 0;
  /**
   * 群名称
   */

  private String name;
  /**
   * 二维码（以base64编码存储）
   */
  @Column(name = "two_dimension_code") private String twoDimensionCode;
  /**
   * 标签（多个标签以';'分割）
   */
  private String tags;
  /**
   * 位置
   */
  private String position;
  /**
   * 群描述
   */
  private String description;
  /**
   * 状态
   */
  private int status;
  /**
   * 创建者openID
   */
  @Column(name = "create_user_id") private String createUserId;
  /**
   * 创建者名称
   */
  @Column(name = "create_user_name") private String createUserName;
  /**
   * 创建者微信账号
   */
  @Column(name = "create_user_account_number") private String createUserAccountNumber;
  /**
   * 创建时间
   */
  @Column(name = "create_time") private long createTime;
  /**
   * 修改时间
   */
  @Column(name = "update_time") private long updateTime;

  public ChatGroup(String name, String twoDimensionCode, String tags, String position,
    String description, String createUserId, String createUserName, String createUserAccountNumber,
    long createTime, long updateTime) {
    this.name = name;
    this.twoDimensionCode = twoDimensionCode;
    this.tags = tags;
    this.position = position;
    this.description = description;
    this.createUserId = createUserId;
    this.createUserName = createUserName;
    this.createUserAccountNumber = createUserAccountNumber;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

  public ChatGroup() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTwoDimensionCode() {
    return twoDimensionCode;
  }

  public void setTwoDimensionCode(String twoDimensionCode) {
    this.twoDimensionCode = twoDimensionCode;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public String getCreateUserName() {
    return createUserName;
  }

  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }

  public String getCreateUserAccountNumber() {
    return createUserAccountNumber;
  }

  public void setCreateUserAccountNumber(String createUserAccountNumber) {
    this.createUserAccountNumber = createUserAccountNumber;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  @Override public String toString() {
    return "ChatGroup{" + "id=" + id + ", name='" + name + '\'' + ", twoDimensionCode='"
      + twoDimensionCode + '\'' + ", tags='" + tags + '\'' + ", position='" + position + '\''
      + ", description='" + description + '\'' + ", status=" + status + ", createUserId='"
      + createUserId + '\'' + ", createUserName='" + createUserName + '\''
      + ", createUserAccountNumber='" + createUserAccountNumber + '\'' + ", createTime="
      + createTime + ", updateTime=" + updateTime + '}';
  }
}
