package com.example.hotSpot.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        // 设置请求页数大于最大页时的操作：true 调回到首页，false 继续请求，默认为 false
        innerInterceptor.setOverflow(false);
        // 设置单页的限制数量，-1 表示不限制
        innerInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }
}
