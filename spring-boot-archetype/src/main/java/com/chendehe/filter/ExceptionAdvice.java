package com.chendehe.filter;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chendehe.common.ErrorCode;
import com.chendehe.exception.BaseException;
import com.chendehe.exception.ErrorMessage;
import com.chendehe.exception.ErrorResult;

/**
 * 拦截处理异常响应.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@RestControllerAdvice("com.chendehe.controller")
public final class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 拦截异常，统一处理.
     *
     * @param request
     *            Servlet 请求
     * @param ex
     *            异常
     * @return 响应
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
        LOGGER.error("occur error", ex);
        if (ex instanceof BaseException) {
            return new ResponseEntity<>(new ErrorResult(ex.getMessage(), ex.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
        } else {
            String defaultMsg = ErrorMessage.message(ErrorCode.SYSTEM_ERROR);
            if (ex instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException methodEx = (MethodArgumentNotValidException)ex;
                String errorMessage = methodEx.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((s1, s2) -> s1.concat(",").concat(Optional.ofNullable(s2).orElse(""))).orElse(defaultMsg);

                return new ResponseEntity<>(new ErrorResult(errorMessage, errorMessage), HttpStatus.BAD_REQUEST);
            } else if (ex instanceof BindException) {
                BindException bindEx = (BindException)ex;
                String errorMessage = bindEx.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((s1, s2) -> s1.concat(",").concat(Optional.ofNullable(s2).orElse(""))).orElse(defaultMsg);

                return new ResponseEntity<>(new ErrorResult(errorMessage, errorMessage), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new ErrorResult(defaultMsg, ex.getMessage()), getStatus(request));
            }
        }
    }

    /**
     * 获取 Servlet 的错误状态码.
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        LOGGER.debug("http status is:{}", statusCode);

        return HttpStatus.valueOf(Optional.ofNullable(statusCode).orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
