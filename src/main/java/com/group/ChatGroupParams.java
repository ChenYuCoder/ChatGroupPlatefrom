package com.group;

class ChatGroupParams extends ChatGroup {
  /**
   * 排序字段（基础类原有字段）
   */
  private String sortKey;
  /**
   * 排序类型（desc/asc）
   */
  private String sortType;
  /**
   * 每页显示数量
   */
  private int count;
  /**
   * 当前页数
   */
  private int page;

  /**
   * 开始时间
   */
  private long startTimestamp;

  /**
   * 结束时间
   */
  private long endTimestamp;

  public String getSortKey() {
    return sortKey;
  }

  public void setSortKey(String sortKey) {
    this.sortKey = sortKey;
  }

  public String getSortType() {
    return sortType;
  }

  public void setSortType(String sortType) {
    this.sortType = sortType;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public long getStartTimestamp() {
    return startTimestamp;
  }

  public void setStartTimestamp(long startTimestamp) {
    this.startTimestamp = startTimestamp;
  }

  public long getEndTimestamp() {
    return endTimestamp;
  }

  public void setEndTimestamp(long endTimestamp) {
    this.endTimestamp = endTimestamp;
  }

  @Override public String toString() {
    return "ChatGroupParams{" + "sortKey='" + sortKey + '\'' + ", sortType='" + sortType + '\''
      + ", count=" + count + ", page=" + page + ", startTimestamp=" + startTimestamp
      + ", endTimestamp=" + endTimestamp + '}';
  }
}
