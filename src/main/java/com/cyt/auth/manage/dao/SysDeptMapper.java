package com.cyt.auth.manage.dao;

import com.cyt.auth.manage.dto.pojo.SysDept;

public interface SysDeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    int insert(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    int insertSelective(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    SysDept selectByPrimaryKey(Long deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysDept record);
}