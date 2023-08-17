package com.sunwul.cloudoffice.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@TableName("t_employee")
@ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工姓名")
    // easypoi注解,设置要导入导出的字段 name标明这个字段的列名
    @Excel(name = "员工姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    @Excel(name = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate birthday;

    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号", width = 20)
    private String id_card;

    @ApiModelProperty(value = "婚姻状况")
    @Excel(name = "性别")
    private String wedlock;

    @ApiModelProperty(value = "民族")
    private Integer nation_id;

    @ApiModelProperty(value = "籍贯")
    @Excel(name = "籍贯")
    private String native_place;

    @ApiModelProperty(value = "政治面貌")
    private Integer politic_id;

    @ApiModelProperty(value = "邮箱")
    @Excel(name = "邮箱", width = 20)
    private String email;

    @ApiModelProperty(value = "电话号码")
    @Excel(name = "电话号码", width = 20)
    private String phone;

    @ApiModelProperty(value = "联系地址")
    @Excel(name = "联系地址", width = 50)
    private String address;

    @ApiModelProperty(value = "所属部门")
    private Integer department_id;

    @ApiModelProperty(value = "职称ID")
    private Integer job_level_id;

    @ApiModelProperty(value = "职位ID")
    private Integer pos_id;

    @ApiModelProperty(value = "聘用形式")
    @Excel(name = "聘用形式")
    private String engage_form;

    @ApiModelProperty(value = "最高学历")
    @Excel(name = "最高学历")
    private String tiptop_degree;

    @ApiModelProperty(value = "所属专业")
    @Excel(name = "所属专业", width = 20)
    private String specialty;

    @ApiModelProperty(value = "毕业院校")
    @Excel(name = "毕业院校", width = 40)
    private String school;

    @ApiModelProperty(value = "入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "入职日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate begin_date;

    @ApiModelProperty(value = "在职状态")
    @Excel(name = "在职状态")
    private String work_state;

    @ApiModelProperty(value = "工号")
    @Excel(name = "工号", width = 20)
    private Integer work_id;

    @ApiModelProperty(value = "合同期限")
    @Excel(name = "合同期限", suffix = "年")
    private Double contract_term;

    @ApiModelProperty(value = "转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "转正日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate conversion_time;

    @ApiModelProperty(value = "离职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
//    @Excel(name = "离职日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate not_work_date;

    @ApiModelProperty(value = "合同起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同起始日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate begin_contract;

    @ApiModelProperty(value = "合同终止日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同终止日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate end_contract;

    @ApiModelProperty(value = "工龄")
//    @Excel(name = "工龄")
    private String work_age;

    @ApiModelProperty(value = "工资账套ID")
    private Integer salary_id;

    @ApiModelProperty(value = "民族关联对象")
    @TableField(exist = false)
    // easypoi注解, 表示这是一个实体类, 在实体类中要导出的字段上加@Excel(name = "XXX"),就可以实现导出关联的特定字段
    @ExcelEntity(name = "民族")
    private Nation nation;

    @ApiModelProperty(value = "政治面貌关联对象")
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;

    @ApiModelProperty(value = "部门关联对象")
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private Department department;

    @ApiModelProperty(value = "职称关联对象")
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;

    @ApiModelProperty(value = "职位关联对象")
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private Position position;

    @ApiModelProperty(value = "工资账套关联对象")
    @TableField(exist = false)
    private Salary salary;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public Integer getNation_id() {
        return nation_id;
    }

    public void setNation_id(Integer nation_id) {
        this.nation_id = nation_id;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
    }

    public Integer getPolitic_id() {
        return politic_id;
    }

    public void setPolitic_id(Integer politic_id) {
        this.politic_id = politic_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getJob_level_id() {
        return job_level_id;
    }

    public void setJob_level_id(Integer job_level_id) {
        this.job_level_id = job_level_id;
    }

    public Integer getPos_id() {
        return pos_id;
    }

    public void setPos_id(Integer pos_id) {
        this.pos_id = pos_id;
    }

    public String getEngage_form() {
        return engage_form;
    }

    public void setEngage_form(String engage_form) {
        this.engage_form = engage_form;
    }

    public String getTiptop_degree() {
        return tiptop_degree;
    }

    public void setTiptop_degree(String tiptop_degree) {
        this.tiptop_degree = tiptop_degree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public String getWork_state() {
        return work_state;
    }

    public void setWork_state(String work_state) {
        this.work_state = work_state;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public Double getContract_term() {
        return contract_term;
    }

    public void setContract_term(Double contract_term) {
        this.contract_term = contract_term;
    }

    public LocalDate getConversion_time() {
        return conversion_time;
    }

    public void setConversion_time(LocalDate conversion_time) {
        this.conversion_time = conversion_time;
    }

    public LocalDate getNot_work_date() {
        return not_work_date;
    }

    public void setNot_work_date(LocalDate not_work_date) {
        this.not_work_date = not_work_date;
    }

    public LocalDate getBegin_contract() {
        return begin_contract;
    }

    public void setBegin_contract(LocalDate begin_contract) {
        this.begin_contract = begin_contract;
    }

    public LocalDate getEnd_contract() {
        return end_contract;
    }

    public void setEnd_contract(LocalDate end_contract) {
        this.end_contract = end_contract;
    }

    public String getWork_age() {
        return work_age;
    }

    public void setWork_age(String work_age) {
        this.work_age = work_age;
    }

    public Integer getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(Integer salary_id) {
        this.salary_id = salary_id;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public PoliticsStatus getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(PoliticsStatus politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Joblevel getJoblevel() {
        return joblevel;
    }

    public void setJoblevel(Joblevel joblevel) {
        this.joblevel = joblevel;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", id_card='" + id_card + '\'' +
                ", wedlock='" + wedlock + '\'' +
                ", nation_id=" + nation_id +
                ", native_place='" + native_place + '\'' +
                ", politic_id=" + politic_id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", department_id=" + department_id +
                ", job_level_id=" + job_level_id +
                ", pos_id=" + pos_id +
                ", engage_form='" + engage_form + '\'' +
                ", tiptop_degree='" + tiptop_degree + '\'' +
                ", specialty='" + specialty + '\'' +
                ", school='" + school + '\'' +
                ", begin_date=" + begin_date +
                ", work_state='" + work_state + '\'' +
                ", work_id=" + work_id +
                ", contract_term=" + contract_term +
                ", conversion_time=" + conversion_time +
                ", not_work_date=" + not_work_date +
                ", begin_contract=" + begin_contract +
                ", end_contract=" + end_contract +
                ", work_age='" + work_age + '\'' +
                ", salary_id=" + salary_id +
                ", nation=" + nation +
                ", politicsStatus=" + politicsStatus +
                ", department=" + department +
                ", joblevel=" + joblevel +
                ", position=" + position +
                '}';
    }
}
