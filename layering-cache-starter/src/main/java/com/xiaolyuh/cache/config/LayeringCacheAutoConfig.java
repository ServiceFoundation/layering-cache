package com.xiaolyuh.cache.config;

import com.xiaolyuh.manager.CacheManager;
import com.xiaolyuh.manager.LayeringCacheManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 多级缓存自动配置类
 *
 * @author xiaolyuh
 */
@Configuration
@ConditionalOnBean(RedisTemplate.class)
@ConditionalOnMissingBean(org.springframework.cache.CacheManager.class)
@AutoConfigureAfter({RedisAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {CacheAutoConfiguration.class})
public class LayeringCacheAutoConfig {

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        return new LayeringCacheManager(redisTemplate);
    }

}