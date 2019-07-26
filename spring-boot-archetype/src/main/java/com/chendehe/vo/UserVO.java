package com.chendehe.vo;

import com.chendehe.common.ConstantCode;
import com.chendehe.common.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Validated
public class UserVO {

  private String id;

  @NotNull(message = ConstantCode.NAME + ErrorCode.VALID_NULL)
  @Size(min = 1, max = 5, message = ConstantCode.NAME + ErrorCode.VALID_SIZE)
  private String name;

  @NotNull(message = ConstantCode.GENDER + ErrorCode.VALID_NULL)
  @JsonProperty("sex")
  private Integer gender;

  private String birthday;
  private String address;


}
