package com.sunwul.cloudoffice.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
@TableName("t_employee_ec")
@ApiModel(value="EmployeeEc对象", description="")
public class EmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "员工编号")
    private Integer eid;

    @ApiModelProperty(value = "奖罚日期")
    private LocalDateTime ecDate;

    @ApiModelProperty(value = "奖罚原因")
    private String ecReason;

    @ApiModelProperty(value = "奖罚分")
    private String ecPoint;

    @ApiModelProperty(value = "奖罚类别，0：奖，1：罚")
    private String ecType;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
    public LocalDateTime getEcDate() {
        return ecDate;
    }

    public void setEcDate(LocalDateTime ecDate) {
        this.ecDate = ecDate;
    }
    public String getEcReason() {
        return ecReason;
    }

    public void setEcReason(String ecReason) {
        this.ecReason = ecReason;
    }
    public String getEcPoint() {
        return ecPoint;
    }

    public void setEcPoint(String ecPoint) {
        this.ecPoint = ecPoint;
    }
    public String getEcType() {
        return ecType;
    }

    public void setEcType(String ecType) {
        this.ecType = ecType;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmployeeEc{" +
            "id=" + id +
            ", eid=" + eid +
            ", ecDate=" + ecDate +
            ", ecReason=" + ecReason +
            ", ecPoint=" + ecPoint +
            ", ecType=" + ecType +
            ", remark=" + remark +
        "}";
    }
}
