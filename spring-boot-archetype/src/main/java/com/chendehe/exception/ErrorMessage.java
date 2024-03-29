package com.chendehe.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 获取异常国际化.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Component
public final class ErrorMessage {

    private static final String DEFAULT = "can't find any messages";

    private static MessageSource messageSource;

    private ErrorMessage() {}

    /**
     * 注入静态变量.
     *
     * @param messageSource
     *            message
     */
    @Autowired
    private void setMessageSource(MessageSource messageSource) {
        ErrorMessage.messageSource = messageSource;
    }

    public static String message(String code, String... params) {
        return messageSource.getMessage(code, params, DEFAULT, LocaleContextHolder.getLocale());
    }

}
