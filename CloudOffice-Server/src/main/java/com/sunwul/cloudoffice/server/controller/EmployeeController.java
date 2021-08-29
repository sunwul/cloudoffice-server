package com.sunwul.cloudoffice.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.response.ResponsePageBean;
import com.sunwul.cloudoffice.server.common.utils.DateUtils;
import com.sunwul.cloudoffice.server.entity.*;
import com.sunwul.cloudoffice.server.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 * 员工
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DateUtils dateUtils;


    /**
     * 获取所有员工(分页)
     *
     * @param currentPage    当前页
     * @param size           数据数量
     * @param employee       员工信息
     * @param beginDateScope 开始日期范围
     * @return ResponsePageBean 分页统一返回对象
     */
    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public ResponsePageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              Employee employee,
                                              LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

    // 单表的CRUD mybatis-plus都已经封装好了,不需要去写ServiceImpl和mapper数据库操作
    // 对于多表的操作, mybatis-plus也支持,但是要比我们自己写SQL复杂, 所以此工程的多表操作都不用mybatis-plus实现

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/jobLevels")
    public List<Joblevel> getAllJobLevels() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取最新工号")
    @GetMapping("/maxWorkId")
    public ResponseBean getMaxWorkId() {
        return employeeService.getMaxWorkId();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public ResponseBean addEmp(@RequestBody Employee employee) {
        // 不使用mybatis-plus的功能是因为需要处理一些信息
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工信息")
    @PutMapping("/")
    public ResponseBean updateEmp(@RequestBody Employee employee) {
        // 使用mybatis-plus的updateById根据ID更新数据,返回boolean类型
        if (employeeService.updateById(dateUtils.getNewEmployee(employee))) {
            return ResponseBean.success("更新员工信息成功!");
        }
        return ResponseBean.error("更新员工信息失败!");
    }

    @ApiOperation(value = "删除员工信息")
    @DeleteMapping("/{id}")
    public ResponseBean deleteEmp(@PathVariable("id") Integer empId) {
        if (employeeService.removeById(empId)) {
            return ResponseBean.success("删除员工成功!");
        }
        return ResponseBean.error("删除员工失败!");
    }

    @ApiOperation(value = "导出员工数据(Excel)")
    @GetMapping(value = "/export", produces = "application/octet-stream") // 添加produces指定输入输出流的文件类型
    public void exportEmployee(HttpServletResponse response) {
        // 获取员工信息(当getEmployee()方法传入了id值时,只查此id的用户,未传值时查询所有用户)
        List<Employee> list = employeeService.getEmployee(null);
        // 使用easyPoi, 先设置表格信息(文件名,sheet名字,文件版本[使用03版])
        ExportParams params = new ExportParams("员工信息表", "员工信息表", ExcelType.HSSF);
        // 使用Workbook接收   easyPoi导出工具类 (表格属性,数据类型,数据)
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream outputStream = null;
        try {
            // 设置请求响应头信息  使用流的形式输出
            response.setHeader("content-type", "application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            // 获取输入输出流
            outputStream = response.getOutputStream();
            // 写入workbook
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    // 清空输入输出流
                    outputStream.flush();
                    // 关闭输入输出流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @ApiOperation(value = "导入员工数据(Excel)")
    @PostMapping("/import")
    public ResponseBean importEmployee(MultipartFile file) {
        if (file != null) {
            System.out.println(file.getName());
            ImportParams params = new ImportParams();
            // 去掉标题行
            params.setTitleRows(1);
            // 获取所有民族数据
            List<Nation> nations = nationService.list();
            // 获取所有的政治面貌数据
            List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
            // 获取所有的部门数据
            List<Department> departments = departmentService.list();
            // 获取所有职称数据
            List<Joblevel> jobLevels = joblevelService.list();
            // 获取所有职位数据
            List<Position> positions = positionService.list();
            try {
                List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
                list.forEach(employee -> {
                    // 1. 先通过有参构造获取list遍历中的employee对象的Nation的name
                    // employee.getNation().getName()
                    // 2. new一个新的对象 new Nation(), 使用有参构造放入上面拿到的name,此时对象里的id是空的
                    // new Nation(employee.getNation().getName())
                    // 3. 使用hashCode进行对象的比较(hashCode方法已重写,只用name值计算hash),拿到此包含此name的对象的下标
                    // int nation_index = nations.indexOf(new Nation(employee.getNation().getName()));
                    // 4. 从所有的民族数据中拿到该下标的完整对象的id  nations.get(nation_index).getId()
                    // int nid = nations.get(nation_index).getId();
                    // 5. 写入民族ID  Nation_id
                    // employee.setNation_id(nid);
                    employee.setNation_id(nations.get(nations.indexOf(new Nation(employee.getNation().getName()))).getId());
                    // 写入政治面貌ID  Politic_id
                    employee.setPolitic_id(politicsStatuses.get(politicsStatuses.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                    // 写入部门ID  Department_id
                    employee.setDepartment_id(departments.get(departments.indexOf(new Department(employee.getDepartment().getName()))).getId());
                    // 写入职位ID  Pos_id
                    employee.setPos_id(positions.get(positions.indexOf(new Position(employee.getPosition().getName()))).getId());
                    // 写入职称ID  Job_level_id
                    employee.setJob_level_id(jobLevels.get(jobLevels.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                });
                // 使用mybatis-plus的saveBatch保存特定对象的集合数据  返回boolean类型
                if (employeeService.saveBatch(list)) {
                    return ResponseBean.success("导入员工数据成功!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseBean.error("导入员工数据异常!");
            }
            return ResponseBean.error("导入员工数据失败!");
        }
        return ResponseBean.error("未选择文件!");
    }

}
