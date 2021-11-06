package com.chendehe.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author cdh
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Value("${feign.gray.tag}")
    private String feignGrayTag;

    @Autowired
    private KubernetesClient client;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Target<?> target = requestTemplate.feignTarget();
        String serviceName = target.name();
        String url = target.url();

        if ("gray".equals(feignGrayTag)) {
            String namespace = url.replaceFirst("http://".concat(serviceName).concat("."), "");
            if (namespace.contains(":")) {
                // 有端口
                namespace = namespace.substring(0, namespace.indexOf(":"));
            }
            String serviceNameGray = serviceName.concat("-gray");
            Service service = client.services().inNamespace(namespace).withName(serviceNameGray).get();
            if (null != service) {
                // 灰度，且存在灰度版本，替换URL=URL+"-gray"
                url = url.replaceFirst(serviceName, serviceNameGray);
            }
        }

        target = new Target.HardCodedTarget<>(Target.HardCodedTarget.class, serviceName, url);
        requestTemplate.feignTarget(target);

        target.apply(requestTemplate);
    }
}
