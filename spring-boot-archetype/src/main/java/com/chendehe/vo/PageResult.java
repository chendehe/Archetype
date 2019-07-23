package com.chendehe.vo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResult<T> extends Page {

  // 总数
  private Integer totalNum;

  private List<T> list;

}
