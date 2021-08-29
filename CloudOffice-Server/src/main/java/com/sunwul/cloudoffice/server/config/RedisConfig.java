package com.sunwul.cloudoffice.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*****
 * @author sunwul
 * @date 2021/3/23 19:00
 * @description redis配置类
 *
 */
@Configuration
public class RedisConfig {

    /**
     * 主要用来做序列化
     * @param connectionFactory Redis连接工厂 自定义RedisTemplate时，必须设置连接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // String类型 key序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // String类型 value序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // Hash类型 key序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // Hash类型 value序列器
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        // 设置Redis连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
