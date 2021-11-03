package com.chendehe.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cdh
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Value("${feign.gray.tag}")
    private String feignGrayTag;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(feignGrayTag);
        Target<?> target = requestTemplate.feignTarget();
        String name = target.name();
        String url = target.url();
        if ("gray".equals(feignGrayTag)) {
            String regex = "http://.*:";
            Matcher matcher = Pattern.compile(regex).matcher(url);
            if (matcher.find()) {
                StringBuilder builder = new StringBuilder(matcher.group(0));
                String path = url.replaceFirst(builder.toString(), "");
                builder.insert(builder.lastIndexOf(":"), "-gray");
                url = builder + path;
            }
        }
        FeignTarget feignTarget = new FeignTarget(FeignTarget.class, name, url);
        requestTemplate.feignTarget(feignTarget);

        feignTarget.apply(requestTemplate);
    }
}
