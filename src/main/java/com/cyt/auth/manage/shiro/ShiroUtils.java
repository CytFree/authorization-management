package com.cyt.auth.manage.shiro;

import com.cyt.auth.manage.common.exception.BizServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 *
 * @author CaoYangTao
 * @date 2018/1/22  16:21
 */
public class ShiroUtils {
    /**
     * 设置密码加密方式
     */
    public static final String HASH_ALGORITHM_NAME = "SHA-256";
    /**
     * 循环次数
     */
    public static final int HASH_ITERATIONS = 16;

    /**
     * 登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取shiro session
     *
     * @return
     */
    public static Session getSession () {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 将数据保存到shiro session中
     *
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从session中取值
     *
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 从session删除值
     *
     * @param key
     * @return
     */
    public static void removeSessionAttribute(Object key) {
        getSession().removeAttribute(key);
    }

    /**
     * 从session获取验证码
     *
     * @param kaptchaSessionKey
     * @return
     */
    public static String getVerificationCode(String kaptchaSessionKey) {
        Object value = getSessionAttribute(kaptchaSessionKey);
        if (value == null) {
            throw new BizServiceException("验证码已经失效，请重新登录！");
        }
        removeSessionAttribute(kaptchaSessionKey);
        return value.toString();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
