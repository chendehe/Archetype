package com.chendehe.exception;

/**
 * 业务校验类.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
public class ValidationException extends BaseException {

    public ValidationException(String errorCode, String... param) {
        super(errorCode, param);
    }

}