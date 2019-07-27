package com.chendehe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 错误返回实体.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Getter
@Setter
@AllArgsConstructor
public final class ErrorResult {

    private String errorMessage;
    private String errorDetail;

}
