package com.chendehe.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.chendehe.common.ConstantCode;
import com.chendehe.common.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户视图.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Getter
@Setter
@ToString
@Validated
public final class UserVO {

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
