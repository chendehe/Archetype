package com.chendehe.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author cdh
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Value("${feign.gray.tag}")
    private String feignGrayTag;

    @Autowired
    private KubernetesDiscoveryClient discoveryClient;

    @Value("${spring.cloud.kubernetes.client.namespace}")
    private String namespace;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(feignGrayTag);
        Target<?> target = requestTemplate.feignTarget();
        String name = target.name();
        String url = target.url();

        String hostname = name + "." + namespace;
        String hostnameGray = hostname.concat("-gray");
//        List<Endpoints> endPointsList = discoveryClient.getEndPointsList(hostnameGray);
        if ("gray".equals(feignGrayTag)) {
            url = url.replaceFirst(hostname, hostnameGray);
        }

        target = new Target.HardCodedTarget<>(Target.HardCodedTarget.class, name, url);
        requestTemplate.feignTarget(target);

        target.apply(requestTemplate);
    }
}
