package com.todoApp.config;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class ApplicationConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("localhost");
		config.setPort(6379);
		return new JedisConnectionFactory(config);
	}
	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(){
//		
//		RedisTemplate<String , Object> template = new RedisTemplate<>();
//		
//		template.setConnectionFactory(connectionFactory());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
//		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//		template.setEnableTransactionSupport(true);
//		template.afterPropertiesSet();
//		
//		return template;
//		
//	}
//	
//	@Bean
//	CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("todos");
//	}

	@Bean
	RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)).serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer( new GenericJackson2JsonRedisSerializer() ));
	}

}