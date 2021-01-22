package com.pvsoul.eec.yuandong.config;

import com.pvsoul.eec.yuandong.provider.RuntimeExceptionProvider;
import com.pvsoul.eec.yuandong.provider.ValidationExceptionProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.context.annotation.Configuration;


/**
 * Jersey配置类
 *
 */
@Configuration
public class JerseyConfig implements ResourceConfigCustomizer {

    @Override
    public void customize(ResourceConfig config) {
        //config.packages("com.pvsoul.eec.yuandong.resource");
        config.register(com.pvsoul.eec.yuandong.resource.JinlangResource.class);
        config.register(com.pvsoul.eec.yuandong.resource.TastekLetDtuResouce.class);
        config.registerClasses(ValidationExceptionProvider.class);
        config.registerClasses(RuntimeExceptionProvider.class);
    }
}