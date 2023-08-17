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
@TableName("t_politics_status")
@ApiModel(value = "PoliticsStatus对象", description = "")
public class PoliticsStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "政治面貌")
    @Excel(name = "政治面貌")
    private String name;

    public PoliticsStatus() {
    }

    public PoliticsStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PoliticsStatus(String name) {
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
        PoliticsStatus that = (PoliticsStatus) o;
        return name.equals(that.name);
    }

    /**
     * 重写hashCode方法 - 只使用name计算hash
     * 与民族类似, 政治面貌字典表, 不存在name重复的情况
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
        return "PoliticsStatus{" +
                "id=" + id +
                ", name=" + name +
                "}";
    }
}
