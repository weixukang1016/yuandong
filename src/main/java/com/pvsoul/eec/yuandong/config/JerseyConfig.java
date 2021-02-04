package com.pvsoul.eec.yuandong.config;

import com.pvsoul.eec.yuandong.provider.RuntimeExceptionProvider;
import com.pvsoul.eec.yuandong.provider.ValidationExceptionProvider;
import com.pvsoul.eec.yuandong.resource.devicedata.JinlangResource;
import com.pvsoul.eec.yuandong.resource.devicedata.TastekLetDtuResouce;
import com.pvsoul.eec.yuandong.resource.web.SideBarResource;
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
        config.register(JinlangResource.class);
        config.register(TastekLetDtuResouce.class);
        config.register(SideBarResource.class);

        config.registerClasses(ValidationExceptionProvider.class);
        config.registerClasses(RuntimeExceptionProvider.class);
    }
}