package com.chendehe.vo;

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

}
