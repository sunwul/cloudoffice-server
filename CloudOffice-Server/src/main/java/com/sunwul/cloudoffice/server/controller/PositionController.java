package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Position;
import com.sunwul.cloudoffice.server.service.PositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunwul
 * @since 2021-03-21
 * 职位
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private PositionService positionService;

    // 单表的CRUD mybatis-plus都已经封装好了,不需要去写ServiceImpl和mapper数据库操作
    // 对于多表的操作, mybatis-plus也支持,但是要比我们自己写SQL复杂, 所以此工程的多表操作都不用mybatis-plus实现

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public ResponseBean addPosition(@RequestBody Position position) {
        // 创建时间不需要前端传, 由后端取当前时间
        position.setCreate_date(LocalDateTime.now());
        // 使用mybatis-plus的save保存数据,返回boolean类型
        if (positionService.save(position)) {
            return ResponseBean.success("添加职位信息成功!");
        }
        return ResponseBean.error("添加职位信息失败!");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public ResponseBean updatePosition(@RequestBody Position position) {
        // 使用mybatis-plus的updateById根据ID更新数据,返回boolean类型
        if (positionService.updateById(position)) {
            return ResponseBean.success("更新职位信息成功!");
        }
        return ResponseBean.error("更新职位信息失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    // PathVariable 获取路径中的参数
    public ResponseBean deletePosition(@PathVariable("id") Integer id) {
        // 使用mybatis-plus的removeById根据ID删除数据,返回boolean类型
        if (positionService.removeById(id)) {
            return ResponseBean.success("删除职位信息成功!");
        }
        return ResponseBean.error("删除职位信息失败!");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    // PathVariable 获取路径中的参数
    public ResponseBean deletePositionsByIds(Integer[] ids) {
        // 使用mybatis-plus的removeByIds根据ID数组批量删除数据,返回boolean类型
        // Arrays.asList 装换为数组
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.success("批量删除职位信息成功!");
        }
        return ResponseBean.error("批量删除职位信息失败!");
    }

}
