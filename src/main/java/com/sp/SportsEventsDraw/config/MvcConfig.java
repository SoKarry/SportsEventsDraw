package com.sp.SportsEventsDraw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Класс, предназначенный для настройки Spring MVC
@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //Сопоставляет URL-путь или шаблон с контроллером представления
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    //Обработчик для обслуживания статических ресурсов.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/templates/img/");
    }
}