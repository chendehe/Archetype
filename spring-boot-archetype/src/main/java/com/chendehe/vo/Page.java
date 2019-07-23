package com.chendehe.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {

  // 当前页
  private Integer currentPage;

  // 页面大小
  private Integer pageSize;

}
