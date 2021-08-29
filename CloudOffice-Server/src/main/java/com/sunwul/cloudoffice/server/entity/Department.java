package com.sunwul.cloudoffice.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@TableName("t_department")
@ApiModel(value = "Department对象", description = "")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门")
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer parent_id;

    @ApiModelProperty(value = "路径")
    private String dep_path;

    @ApiModelProperty(value = "是否启用")
    private String enabled;

    @ApiModelProperty(value = "是否上级")
    private String is_parent;

    @ApiModelProperty(value = "子部门列表")
    // 表示在实际的表中没有这个字段
    @TableField(exist = false)
    private List<Department> children;

    @ApiModelProperty(value = "返回结果,存储过程使用")
    // 表示在实际的表中没有这个字段
    @TableField(exist = false)
    private Integer result;

    public Department() {
    }

    public Department(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        this.name = name;
    }

    /**
     * 重写equals方法 - 只对比name值
     * 不需要考虑其它字段的值,当name值一样时,判定为同一对象
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return name.equals(that.name);
    }

    /**
     * 重写hashCode方法
     * 与民族类似, 部门字典表, 不存在name重复的情况
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Department(Integer id, String name, Integer parent_id, String dep_path, String enabled, String is_parent, List<Department> children, Integer result) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.dep_path = dep_path;
        this.enabled = enabled;
        this.is_parent = is_parent;
        this.children = children;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getDep_path() {
        return dep_path;
    }

    public void setDep_path(String dep_path) {
        this.dep_path = dep_path;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(String is_parent) {
        this.is_parent = is_parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name=" + name +
                ", parent_id=" + parent_id +
                ", dep_path=" + dep_path +
                ", enabled=" + enabled +
                ", is_parent=" + is_parent +
                "}";
    }
}
