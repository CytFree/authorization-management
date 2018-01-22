package com.cyt.auth.manage.shiro;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro Config
 *
 * @author CaoYangTao
 * @date 2018/1/22  13:55
 */
@Configuration
public class ShiroConfig {
    @Bean("sessionManager")
    public SessionManager sessionManager (RedisShiroSessionDao redisShiroSessionDao,
                                          @Value("${config.redis.open}") boolean redisOpen,
                                          @Value("${config.shiro.redis}") boolean shiroRedis) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        //session全局过期时间（毫秒）
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationSchedulerEnabled(true);

        //开启redis缓存session
        if (redisOpen && shiroRedis) {
            sessionManager.setSessionDAO(redisShiroSessionDao);
        }

        return sessionManager;
    }

}
