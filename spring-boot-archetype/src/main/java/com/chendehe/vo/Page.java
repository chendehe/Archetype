package com.chendehe.vo;

import com.chendehe.common.ConstantCode;
import com.chendehe.common.ErrorCode;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Validated
public class Page {

  // 当前页
  @Min(value = 1, message = ConstantCode.CURRENT_PAGE + ErrorCode.VALID_MIN)
  private Integer currentPage;

  // 页面大小
  private Integer pageSize;

}
