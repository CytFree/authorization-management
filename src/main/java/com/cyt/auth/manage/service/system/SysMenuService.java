package com.cyt.auth.manage.service.system;

import com.cyt.auth.manage.dto.vo.SysMenuVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单信息服务接口
 *
 * @author CaoYangTao
 * @date 2018/1/22  17:25
 */
public interface SysMenuService {
    /**
     * 查询所有菜单
     *
     * @return
     */
    public List<SysMenuVO> queryAllList();

    /**
     * 根据用户ID查询用户有权限的菜单
     *
     * @return
     */
    public List<SysMenuVO> queryListByUserId(Long userId);
}
