package com.cyt.auth.manage.dto.vo;

import com.cyt.auth.manage.dto.pojo.SysMenu;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * 菜单信息VO
 *
 * @author CaoYangTao
 * @date 2018/1/22  17:15
 */
public class SysMenuVO extends SysMenu {
    /**
     * 父级菜单名称
     */
    private String parentName;

    /**
     * zTree 是否打开
     */
    private Boolean zTreeOpen;

    /**
     * 子菜单
     */
    private List<?> list;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getzTreeOpen() {
        return zTreeOpen;
    }

    public void setzTreeOpen(Boolean zTreeOpen) {
        this.zTreeOpen = zTreeOpen;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("parentName", parentName)
                .append("zTreeOpen", zTreeOpen)
                .append("list", list)
                .toString();
    }
}
