package com.leyou.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCORSConfig {
    /**@Autowired
    private CorsProperties prop;

    @Bean
    public CorsFilter corsFilter() {
     //1.添加cors的配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许访问的域
        config.setAllowedOrigins(prop.getAllowedOrigins());
        //是否允许发送cookie
        config.setAllowCredentials(prop.getAllowedCredentials());
        //允许的请求方式
        config.setAllowedMethods(prop.getAllowedMethods());
//          允许的头信息
        config.setAllowedHeaders(prop.getAllowedHeaders());
//          访问有效期 单位秒
        config.setMaxAge(prop.getMaxAge());
//       2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(prop.getFilterPath(), config);
//       3.返回新的CORSFilter
        return new CorsFilter(source);
    }*/

    @Bean
    public CorsFilter corsFilter(){
        //添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许的域, 不要写* . 否则cokie无法使用
        config.addAllowedOrigin("http://manage.leyou.com");
        config.addAllowedOrigin("http://www.leyou.com");
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //允许请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        //允许的头信息
        config.addAllowedHeader("*");
        //有效期 秒
        config.setMaxAge(3600L);
        //添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config );

        return new CorsFilter(configSource);
    }



}
