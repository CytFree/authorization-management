package com.cyt.auth.manage.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author CaoYangTao
 * @date 2018年01月21日 20:43
 * @desc
 */
@Component
public class ShiroTag {

    /**
     * 判断当前用户是否具有此权限
     *
     * @param permission
     * @return true-有权限  false-无权限
     */
    public boolean hasPermission (String permission) {
        return permission != null
                && SecurityUtils.getSubject().isPermitted(permission);
    }
}
