package com.cyt.auth.manage.service.system.impl;

import com.cyt.auth.manage.dao.SysMenuMapper;
import com.cyt.auth.manage.dto.vo.SysMenuVO;
import com.cyt.auth.manage.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CaoYangTao
 * @date 2018/1/22  17:26
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuVO> queryAllList() {
        return sysMenuMapper.queryAllList();
    }

    @Override
    public List<SysMenuVO> queryListByUserId(Long userId) {
        return sysMenuMapper.queryListByUserId(userId);
    }
}
