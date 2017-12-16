package com.ejyi.demo.springboot.eureka.server;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@EnableEurekaServer
@SpringBootApplication
public class WebEurekaApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(WebEurekaApplication.class, args);
    }


    /**
     * 使用 @Bean注入 fastJsonHttpMessageConvert
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}