package com.sunwul.cloudoffice.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sunwul
 * @since 2021-03-21
 * 菜单表
 */
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
//    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "菜单url")
    private String url;
    @ApiModelProperty(value = "菜单path")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "图标")
    private String iconCls;

    @ApiModelProperty(value = "是否保持激活")
    private String keep_alive;

    @ApiModelProperty(value = "是否要求权限")
    private String require_auth;

    @ApiModelProperty(value = "父id")
    private Integer parent_id;

    @ApiModelProperty(value = "是否启用")
    private String enabled;

    @ApiModelProperty(value = "子菜单列表")
    // 表示在实际的表中没有这个字段
    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty(value = "角色列表")
    // 根据菜单里的URL查询哪些角色拥有这些权限
    @TableField(exist = false)
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getKeep_alive() {
        return keep_alive;
    }

    public void setKeep_alive(String keep_alive) {
        this.keep_alive = keep_alive;
    }

    public String getRequire_auth() {
        return require_auth;
    }

    public void setRequire_auth(String require_auth) {
        this.require_auth = require_auth;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", url=" + url +
                ", path=" + path +
                ", component=" + component +
                ", name=" + name +
                ", iconCls=" + iconCls +
                ", keepAlive=" + keep_alive +
                ", requireAuth=" + require_auth +
                ", parentId=" + parent_id +
                ", enabled=" + enabled +
                "}";
    }
}
