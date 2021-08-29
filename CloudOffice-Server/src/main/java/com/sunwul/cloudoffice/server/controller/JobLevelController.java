package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Joblevel;
import com.sunwul.cloudoffice.server.service.JoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunwul
 * @since 2021-03-21
 * 职称
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    private JoblevelService joblevelService;

    // 单表的CRUD mybatis-plus都已经封装好了,不需要去写ServiceImpl和mapper数据库操作
    // 对于多表的操作, mybatis-plus也支持,但是要比我们自己写SQL复杂, 所以此工程的多表操作都不用mybatis-plus实现

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public ResponseBean addJobLevel(@RequestBody Joblevel joblevel) {
        // 创建时间不需要前端传, 由后端取当前时间
        joblevel.setCreate_date(LocalDateTime.now());
        // 使用mybatis-plus的save保存数据,返回boolean类型
        if (joblevelService.save(joblevel)) {
            return ResponseBean.success("添加职称成功!");
        }
        return ResponseBean.error("添加职称失败!");
    }

    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public ResponseBean updateJobLevel(@RequestBody Joblevel joblevel) {
        // 使用mybatis-plus的updateById根据ID更新数据,返回boolean类型
        if (joblevelService.updateById(joblevel)) {
            return ResponseBean.success("更新职称成功!");
        }
        return ResponseBean.error("更新职称失败");
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    // PathVariable 获取路径中的参数
    public ResponseBean deleteJobLevel(@PathVariable("id") Integer id) {
        // 使用mybatis-plus的removeById根据ID删除数据,返回boolean类型
        if (joblevelService.removeById(id)) {
            return ResponseBean.success("删除职称成功!");
        }
        return ResponseBean.error("删除职称失败!");
    }

    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    // PathVariable 获取路径中的参数
    public ResponseBean deleteJobLevels(Integer[] ids) {
        // 使用mybatis-plus的removeById根据ID删除数据,返回boolean类型
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.success("批量删除职称成功!");
        }
        return ResponseBean.error("批量删除职称失败!");
    }
}
