package com.chendehe.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author cdh
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Value("#{${feign.services.gray}}")
    private Map<String, Boolean> isGrayService;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Target<?> target = requestTemplate.feignTarget();
        String serviceName = target.name();
        String url = target.url();

        if (isGrayService.get(serviceName)) {
            url = url.replaceFirst(serviceName, serviceName.concat("-gray"));
        }

        target = new Target.HardCodedTarget<>(Target.HardCodedTarget.class, serviceName, url);
        requestTemplate.feignTarget(target);

        target.apply(requestTemplate);
    }
}
