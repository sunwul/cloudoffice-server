package com.sunwul.cloudoffice.generator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@TableName("t_salary")
@ApiModel(value="Salary对象", description="")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "基本工资")
    @TableField("basicSalary")
    private BigDecimal basicsalary;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "午餐补助")
    @TableField("lunchSalary")
    private BigDecimal lunchsalary;

    @ApiModelProperty(value = "交通补助")
    @TableField("trafficSalary")
    private BigDecimal trafficsalary;

    @ApiModelProperty(value = "应发工资")
    @TableField("allSalary")
    private BigDecimal allsalary;

    @ApiModelProperty(value = "养老金基数")
    @TableField("pensionBase")
    private BigDecimal pensionbase;

    @ApiModelProperty(value = "养老金比率")
    @TableField("pensionPer")
    private BigDecimal pensionper;

    @ApiModelProperty(value = "启用时间")
    @TableField("createDate")
    private LocalDateTime createdate;

    @ApiModelProperty(value = "医疗基数")
    @TableField("medicalBase")
    private BigDecimal medicalbase;

    @ApiModelProperty(value = "医疗保险比率")
    @TableField("medicalPer")
    private BigDecimal medicalper;

    @ApiModelProperty(value = "公积金基数")
    @TableField("accumulationFundBase")
    private BigDecimal accumulationfundbase;

    @ApiModelProperty(value = "公积金比率")
    @TableField("accumulationFundPer")
    private BigDecimal accumulationfundper;

    @ApiModelProperty(value = "名称")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public BigDecimal getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(BigDecimal basicsalary) {
        this.basicsalary = basicsalary;
    }
    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
    public BigDecimal getLunchsalary() {
        return lunchsalary;
    }

    public void setLunchsalary(BigDecimal lunchsalary) {
        this.lunchsalary = lunchsalary;
    }
    public BigDecimal getTrafficsalary() {
        return trafficsalary;
    }

    public void setTrafficsalary(BigDecimal trafficsalary) {
        this.trafficsalary = trafficsalary;
    }
    public BigDecimal getAllsalary() {
        return allsalary;
    }

    public void setAllsalary(BigDecimal allsalary) {
        this.allsalary = allsalary;
    }
    public BigDecimal getPensionbase() {
        return pensionbase;
    }

    public void setPensionbase(BigDecimal pensionbase) {
        this.pensionbase = pensionbase;
    }
    public BigDecimal getPensionper() {
        return pensionper;
    }

    public void setPensionper(BigDecimal pensionper) {
        this.pensionper = pensionper;
    }
    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }
    public BigDecimal getMedicalbase() {
        return medicalbase;
    }

    public void setMedicalbase(BigDecimal medicalbase) {
        this.medicalbase = medicalbase;
    }
    public BigDecimal getMedicalper() {
        return medicalper;
    }

    public void setMedicalper(BigDecimal medicalper) {
        this.medicalper = medicalper;
    }
    public BigDecimal getAccumulationfundbase() {
        return accumulationfundbase;
    }

    public void setAccumulationfundbase(BigDecimal accumulationfundbase) {
        this.accumulationfundbase = accumulationfundbase;
    }
    public BigDecimal getAccumulationfundper() {
        return accumulationfundper;
    }

    public void setAccumulationfundper(BigDecimal accumulationfundper) {
        this.accumulationfundper = accumulationfundper;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Salary{" +
            "id=" + id +
            ", basicsalary=" + basicsalary +
            ", bonus=" + bonus +
            ", lunchsalary=" + lunchsalary +
            ", trafficsalary=" + trafficsalary +
            ", allsalary=" + allsalary +
            ", pensionbase=" + pensionbase +
            ", pensionper=" + pensionper +
            ", createdate=" + createdate +
            ", medicalbase=" + medicalbase +
            ", medicalper=" + medicalper +
            ", accumulationfundbase=" + accumulationfundbase +
            ", accumulationfundper=" + accumulationfundper +
            ", name=" + name +
        "}";
    }
}
