package com.chendehe.vo;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class PageResult<T> extends Page {

  // 总数
  private Integer totalNum;

  private List<T> list;

  /**
   * 空页.
   *
   * @return 空页.
   */
  public PageResult<T> defaultPage() {
    PageResult<T> result = new PageResult<>();
    result.setTotalNum(0);
    result.setList(Collections.emptyList());
    result.setCurrentPage(1);
    result.setPageSize(0);
    return result;
  }
}
