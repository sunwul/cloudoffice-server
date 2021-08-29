package com.sunwul.cloudoffice.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@TableName("t_nation")
@ApiModel(value = "Nation对象", description = "")
public class Nation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "民族")
    @Excel(name = "民族")
    private String name;

    public Nation() {
    }

    public Nation(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Nation(String name) {
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
        Nation nation = (Nation) o;
        return name.equals(nation.name);
    }

    /**
     * 重写hashCode方法 - 只使用name计算hash
     * 此种方法比较取巧,当存在有相同name的数据时,会出现异常
     * 考虑到民族只有56个,类似于一个字典表,不存在name重复的情况
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    @Override
    public String toString() {
        return "Nation{" +
                "id=" + id +
                ", name=" + name +
                "}";
    }
}
