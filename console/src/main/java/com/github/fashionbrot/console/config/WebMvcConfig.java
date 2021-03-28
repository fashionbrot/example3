package com.github.fashionbrot.console.config;

import com.github.fashionbrot.console.interceptor.BackstageInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("classpath:/static/");
        if ("test".equals(profile) || "dev".equals(profile)){
            registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }


    @Bean
    public BackstageInterceptor getInterceptor(){
        return new BackstageInterceptor();
    }




    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/sys/user/doLogin")
                .excludePathPatterns("/sys/user/logout")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/ruoyi/**")
                .excludePathPatterns("/ajax/**")
                .excludePathPatterns("/file/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/i18n/**")
                .excludePathPatterns("")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/merchant/**")
        ;

    }


}
