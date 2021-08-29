package com.sunwul.cloudoffice.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_sys_msg")
@ApiModel(value="SysMsg对象", description="")
public class SysMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "消息id")
    private Integer msgid;

    @ApiModelProperty(value = "0表示群发消息")
    private String type;

    @ApiModelProperty(value = "这条消息是给谁的")
    private String sendid;

    @ApiModelProperty(value = "0 未读 1 已读")
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SysMsg{" +
            "id=" + id +
            ", msgid=" + msgid +
            ", type=" + type +
            ", sendid=" + sendid +
            ", state=" + state +
        "}";
    }
}
