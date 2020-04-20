package de.fzj.atlascore.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

/**
 * Cache for data from external apis
 *
 * @author Vadim Marcenko
 *
 */
@Configuration
@EnableCaching
public class CachingConfig {

    private static final Logger LOGGER = LogManager.getLogger(CachingConfig.class);
    private long timeoutSeconds = 60;
    private int redisPort = 6379;
    private String redisHost = "localhost";
    private Map<String, Long> cacheExpirations = new HashMap<>();

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("api-data");
    }

//    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(timeoutInSeconds));
//    }
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        LOGGER.info("Redis (/Lettuce) configuration enabled. With cache timeout " + timeoutSeconds + " seconds.");
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(redisHost);
//        redisStandaloneConfiguration.setPort(redisPort);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return createCacheConfiguration(timeoutSeconds);
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//
//        for (Map.Entry<String, Long> cacheNameAndTimeout : cacheExpirations.entrySet()) {
//            cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
//        }
//
//        return RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults(cacheConfiguration())
//                .withInitialCacheConfigurations(cacheConfigurations).build();
//    }
}
