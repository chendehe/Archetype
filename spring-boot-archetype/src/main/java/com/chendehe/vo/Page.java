package com.chendehe.vo;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.chendehe.common.ConstantCode;
import com.chendehe.common.ErrorCode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * page 实体.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Getter
@Setter
@ToString
@Validated
public class Page {

    /**
     * 当前页
     */
    @Min(value = 1, message = ConstantCode.CURRENT_PAGE + ErrorCode.VALID_MIN)
    private Integer currentPage;

    /**
     * 页面大小
     */
    @Min(value = 1, message = ConstantCode.PAGE_SIZE + ErrorCode.VALID_MIN)
    private Integer pageSize;

}
