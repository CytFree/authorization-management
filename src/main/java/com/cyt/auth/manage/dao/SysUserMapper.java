package com.cyt.auth.manage.dao;

import com.cyt.auth.manage.dto.pojo.SysUser;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    SysUser selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    SysUser queryByUserName(String userName);
}