package com.sunwul.cloudoffice.server.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*****
 * @author sunwul
 * @date 2021/3/21 13:11
 * @description 公共- 响应统一返回对象
 */
@ApiModel(value = "ResponseBean")
public class ResponseBean {

    @ApiModelProperty(value = "返回码")
    private long code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回对象")
    private Object data;

    /**
     * 成功返回结果
     *
     * @param message 成功的消息
     * @return 返回对象
     */
    public static ResponseBean success(String message) {
        return new ResponseBean(200, message, null);
    }

    /**
     * 成功返回结果
     *
     * @param object  成功获取到的对象
     * @return 返回对象
     */
    public static ResponseBean success(Object object) {
        return new ResponseBean(200, null, object);
    }

    /**
     * 成功返回结果
     *
     * @param message 成功的消息
     * @param object  成功获取到的对象
     * @return 返回对象
     */
    public static ResponseBean success(String message, Object object) {
        return new ResponseBean(200, message, object);
    }

    /**
     * 失败返回结果
     *
     * @param message 失败的消息
     * @return 返回对象
     */
    public static ResponseBean error(String message) {
        return new ResponseBean(500, message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 失败的消息
     * @param object  失败的对象
     * @return 返回对象
     */
    public static ResponseBean error(String message, Object object) {
        return new ResponseBean(500, message, object);
    }

    public ResponseBean(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    ResponseBean() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
