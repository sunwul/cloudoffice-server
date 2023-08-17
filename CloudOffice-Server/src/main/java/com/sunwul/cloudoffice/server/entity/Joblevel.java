package com.sunwul.cloudoffice.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author sunwul
 * @since 2021-03-21
 * 职称表
 */
@TableName("t_joblevel")
@ApiModel(value = "Joblevel对象", description = "")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职称名称")
    @Excel(name = "职称")
    private String name;

    @ApiModelProperty(value = "职称等级")
    private String title_level;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDateTime create_date;

    @ApiModelProperty(value = "是否启用")
    private String enabled;

    public Joblevel() {
    }

    public Joblevel(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        this.name = name;
    }

    public Joblevel(Integer id, String name, String title_level, LocalDateTime create_date, String enabled) {
        this.id = id;
        this.name = name;
        this.title_level = title_level;
        this.create_date = create_date;
        this.enabled = enabled;
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
        Joblevel joblevel = (Joblevel) o;
        return name.equals(joblevel.name);
    }

    /**
     * 重写hashCode方法
     * 与民族类似, 职称字典表, 不存在name重复的情况
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

    public String getTitle_level() {
        return title_level;
    }

    public void setTitle_level(String title_level) {
        this.title_level = title_level;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Joblevel{" +
                "id=" + id +
                ", name=" + name +
                ", title_level=" + title_level +
                ", create_date=" + create_date +
                ", enabled=" + enabled +
                "}";
    }
}
