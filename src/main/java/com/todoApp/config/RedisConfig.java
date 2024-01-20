package com.todoApp.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String HOST;

	@Value("${spring.redis.port}")
	private int port;

	@Bean
	LettuceConnectionFactory connectionFactory() {

		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName(HOST);
		redisConfig.setPort(port);
		return new LettuceConnectionFactory(redisConfig);
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() {

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		RedisSerializer<String> stringSerilizer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer jdkSerializationRedisSerializer = new GenericJackson2JsonRedisSerializer();

		template.setConnectionFactory(connectionFactory());
		template.setKeySerializer(stringSerilizer);
		template.setHashKeySerializer(stringSerilizer);
		template.setValueSerializer(jdkSerializationRedisSerializer);
		template.setHashValueSerializer(jdkSerializationRedisSerializer);
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();

		return template;
	}
	
	@Bean
	RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues();
	}

	@Bean
	RedisCacheManager cacheManager() {
		return RedisCacheManager.builder(connectionFactory()).cacheDefaults(cacheConfiguration()).transactionAware()
				.build();
	}

}
