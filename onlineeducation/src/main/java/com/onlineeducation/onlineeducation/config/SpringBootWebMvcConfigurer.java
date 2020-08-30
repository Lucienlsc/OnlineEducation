package com.onlineeducation.onlineeducation.config;

import com.onlineeducation.onlineeducation.config.handler.TokenToAdminMethodArgumentResolver;
import com.onlineeducation.onlineeducation.config.handler.TokenToStudentMethodArgumentResolver;
import com.onlineeducation.onlineeducation.config.handler.TokenToTeacherMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private TokenToAdminMethodArgumentResolver tokenToAdminMethodArgumentResolver;

    @Autowired
    private TokenToTeacherMethodArgumentResolver tokenToTeacherMethodArgumentResolver;

    @Autowired
    private TokenToStudentMethodArgumentResolver tokenToStudentMethodArgumentResolver;

    /**
     * TokenToUser 注解处理方法
     *
     * @param argumentResolvers
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenToAdminMethodArgumentResolver);
        argumentResolvers.add(tokenToTeacherMethodArgumentResolver);
        argumentResolvers.add(tokenToStudentMethodArgumentResolver);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:H:\\upload\\");
    }
}
