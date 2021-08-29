package com.sunwul.cloudoffice.server.common.utils;

import com.sunwul.cloudoffice.server.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*****
 * @author sunwul
 * @date 2021/3/27 12:35
 * @description 时间工具类
 */
@Component
public class DateUtils implements Converter<String, LocalDate> {

    /**
     * 日期转换 - 重写Converter
     *
     * @param source 待转换字符
     * @return 转换后的日期
     */
    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算员工合同期限
     *
     * @param employee 员工信息
     * @return 更新合同期限后的员工信息
     */
    public Employee getNewEmployee(Employee employee) {
        // 获取合同开始时间
        LocalDate beginContract = employee.getBegin_contract();
        // 获取合同结束时间
        LocalDate endContract = employee.getEnd_contract();
        // 计算合同期限, 两个日期相差多少天
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        // 设置格式化规则, 数据库中是double类型, 此处设置保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        // 将天数除以365(不考虑闰年的情况), 然后decimalFormat.format格式化一下, 再Double.parseDouble转为Double格式
        Double ct = Double.parseDouble(decimalFormat.format(days / 365.00));
        // 写入计算后的合同期限
        employee.setContract_term(ct);
        return employee;
    }
}
