package com.weon.projetoweon.configs;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@Configuration
public class ResolverConfig extends WebMvcConfigurationSupport {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver){
        argumentResolver.add(new SpecificationArgumentResolver());
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        argumentResolver.add(resolver);
        super.addArgumentResolvers(argumentResolver);
    }
    
}
