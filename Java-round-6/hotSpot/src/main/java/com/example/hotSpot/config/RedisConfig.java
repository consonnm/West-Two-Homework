package com.example.hotSpot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig{
    //Redis链接工厂
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    //缓存操作组件RedisTemplate的自定义配置
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        //定义RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate=new RedisTemplate
                <String, Object>();
        //设置Redis的链接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //TODO：指定大Key序列化策略为String序列化，Value为JDK自带的序列化策略
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //TODO：指定hashKey序列化策略为String序列化-针对hash散列存储
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    //缓存操作组件StringRedisTemplate
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }


}
