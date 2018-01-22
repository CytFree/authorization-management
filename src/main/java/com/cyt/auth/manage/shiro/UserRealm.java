package com.cyt.auth.manage.shiro;

import com.cyt.auth.manage.common.util.StringConstant;
import com.cyt.auth.manage.dao.SysMenuMapper;
import com.cyt.auth.manage.dao.SysUserMapper;
import com.cyt.auth.manage.dto.pojo.SysUser;
import com.cyt.auth.manage.dto.vo.SysMenuVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CaoYangTao
 * @date 2018年01月21日 18:51
 * @desc
 */
@Component(value = "userRealm")
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        List<SysMenuVO> sysMenuVOList;
        Set<String> permissionSet = new HashSet<>();

        Long userId = sysUser.getUserId();

        //如果是管理员，拥有所有权限
        if (userId == 1L) {
             sysMenuVOList = sysMenuMapper.queryAllList();
        } else {
            //获取用户的所有权限
            sysMenuVOList = sysMenuMapper.queryListByUserId(userId);
        }

        if (!CollectionUtils.isEmpty(sysMenuVOList)) {
            for (SysMenuVO sysMenuVO : sysMenuVOList) {
                String permissions = sysMenuVO.getPerms();
                if (StringUtils.isNotBlank(permissions)) {
                    List<String> permissionList = Arrays.asList(permissions.trim().split(StringConstant.DEFAULT_SPILT));
                    permissionSet.addAll(permissionList);
                }
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userNameToken = (UsernamePasswordToken)token;
        String userName = userNameToken.getUsername();
        SysUser sysUser = sysUserMapper.queryByUserName(userName);

        if (sysUser == null) {
            throw new UnknownAccountException("用户名不存在！");
        }

        AuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        return authenticationInfo;
    }

    /**
     * 设置加密方式
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.HASH_ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
