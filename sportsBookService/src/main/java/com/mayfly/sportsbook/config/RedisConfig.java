package com.mayfly.sportsbook.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import redis.embedded.RedisServer;

@Configuration
@EnableRedisRepositories(basePackages = { "com.mayfly.sportsbook.repository", "com.mayfly.sportsbook.model" })
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		   RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6370);
		    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	private RedisServer redisServer;

	@PostConstruct
	public void startServer() {
		try {
			redisServer = RedisServer.builder().port(6370).build();
			redisServer.start();
			System.out.println("Redis Server Started OK");
		} catch (Exception e) {
			// do nothing
		}
	}

	@PreDestroy
	public void destroy() {
		try {

			redisServer.stop();
			System.out.println("Stopped Redis Server...");
		} catch (Exception e) {
			// do nothing
		}
	}
}
