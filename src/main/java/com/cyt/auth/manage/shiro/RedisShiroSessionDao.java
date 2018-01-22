package com.cyt.auth.manage.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author CaoYangTao
 * @date 2018/1/22  13:59
 */
@Component("redisShiroSessionDao")
public class RedisShiroSessionDao extends EnterpriseCacheSessionDAO {
    private static final String SESSION_REDIS_KEY_PREFFIX = "sessionId:";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  创建session
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        final String sessionRedisKey = getSessionRedisKey(sessionId.toString());
        redisTemplate.opsForValue().set(sessionRedisKey, session);
        redisTemplate.expire(sessionRedisKey, 60, TimeUnit.MINUTES);
        return sessionId;
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            final String sessionRedisKey = getSessionRedisKey(sessionId.toString());
            session = (Session) redisTemplate.opsForValue().get(sessionRedisKey);
        }
        return session;
    }

    /**
     * 更新session
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        final String sessionRedisKey = getSessionRedisKey(session.getId().toString());
        redisTemplate.opsForValue().set(sessionRedisKey, session);
        redisTemplate.expire(sessionRedisKey, 60, TimeUnit.MINUTES);
    }

    /**
     * 删除session
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        final String sessionRedisKey = getSessionRedisKey(session.getId().toString());
        redisTemplate.delete(sessionRedisKey);
    }

    private final String getSessionRedisKey(String sessionId) {
        return SESSION_REDIS_KEY_PREFFIX + sessionId;
    }
}
