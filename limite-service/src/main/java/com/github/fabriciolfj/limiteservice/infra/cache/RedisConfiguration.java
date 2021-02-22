package com.github.fabriciolfj.limiteservice.infra.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    public static final String CACHE_NAME = "limite";

    private final RedisProperties properties;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryStandalone() {
        final RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(properties.getHost());
        redisConf.setPort(properties.getPort());
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .withCacheConfiguration(CACHE_NAME, getCacheConfiguration())
                .build();
    }

    @Bean
    public RedisCacheConfiguration getCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair
                        .fromSerializer(new Jackson2JsonRedisSerializer<>(String.class)));
    }

    @Bean
    @Qualifier(CACHE_NAME)
    public Cache getCacheLimits(final CacheManager cacheManager) {
        return cacheManager.getCache(CACHE_NAME);
    }

    @Bean
    public RedisClient redisClient() { // used in bulk insert
        final RedisURI uri = RedisURI.builder()
                .withHost(properties.getHost())
                .withPort(properties.getPort())
                .withDatabase(properties.getDatabase())
                .build();
        return RedisClient.create(uri);
    }

}
