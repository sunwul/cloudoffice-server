package com.sunwul.cloudoffice.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@TableName("t_salary")
@ApiModel(value = "Salary对象", description = "")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "基本工资")
//    @TableField("basicSalary")
    private BigDecimal basicSalary;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "午餐补助")
//    @TableField("lunchSalary")
    private BigDecimal lunchSalary;

    @ApiModelProperty(value = "交通补助")
//    @TableField("trafficSalary")
    private BigDecimal trafficSalary;

    @ApiModelProperty(value = "应发工资")
//    @TableField("allSalary")
    private BigDecimal allSalary;

    @ApiModelProperty(value = "养老金基数")
//    @TableField("pensionBase")
    private BigDecimal pensionBase;

    @ApiModelProperty(value = "养老金比率")
//    @TableField("pensionPer")
    private BigDecimal pensionPer;

    @ApiModelProperty(value = "启用时间")
//    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "医疗基数")
//    @TableField("medicalBase")
    private BigDecimal medicalBase;

    @ApiModelProperty(value = "医疗保险比率")
//    @TableField("medicalPer")
    private BigDecimal medicalPer;

    @ApiModelProperty(value = "公积金基数")
//    @TableField("accumulationFundBase")
    private BigDecimal accumulationFundBase;

    @ApiModelProperty(value = "公积金比率")
//    @TableField("accumulationFundPer")
    private BigDecimal accumulationFundPer;

    @ApiModelProperty(value = "名称")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getLunchSalary() {
        return lunchSalary;
    }

    public void setLunchSalary(BigDecimal lunchSalary) {
        this.lunchSalary = lunchSalary;
    }

    public BigDecimal getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(BigDecimal trafficSalary) {
        this.trafficSalary = trafficSalary;
    }

    public BigDecimal getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(BigDecimal allSalary) {
        this.allSalary = allSalary;
    }

    public BigDecimal getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(BigDecimal pensionBase) {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(BigDecimal pensionPer) {
        this.pensionPer = pensionPer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(BigDecimal medicalBase) {
        this.medicalBase = medicalBase;
    }

    public BigDecimal getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(BigDecimal medicalPer) {
        this.medicalPer = medicalPer;
    }

    public BigDecimal getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(BigDecimal accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public BigDecimal getAccumulationFundPer() {
        return accumulationFundPer;
    }

    public void setAccumulationFundPer(BigDecimal accumulationFundPer) {
        this.accumulationFundPer = accumulationFundPer;
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
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", lunchsalary=" + lunchSalary +
                ", trafficsalary=" + trafficSalary +
                ", allsalary=" + allSalary +
                ", pensionBase=" + pensionBase +
                ", pensionper=" + pensionPer +
                ", createdate=" + createDate +
                ", medicalbase=" + medicalBase +
                ", medicalper=" + medicalPer +
                ", accumulationfundbase=" + accumulationFundBase +
                ", accumulationfundper=" + accumulationFundPer +
                ", name=" + name +
                "}";
    }
}
