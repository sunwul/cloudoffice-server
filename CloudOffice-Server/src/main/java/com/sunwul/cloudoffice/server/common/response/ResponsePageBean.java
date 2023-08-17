package com.sunwul.cloudoffice.server.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/27 10:05
 * @description 公共- 分页统一返回对象
 */
@ApiModel(value = "ResponsePageBean")
public class ResponsePageBean {

    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "数据list")
    private List<?> data;

    public ResponsePageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
