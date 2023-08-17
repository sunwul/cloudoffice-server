package com.sunwul.cloudoffice.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.response.ResponsePageBean;
import com.sunwul.cloudoffice.server.common.utils.DateUtils;
import com.sunwul.cloudoffice.server.entity.Employee;
import com.sunwul.cloudoffice.server.entity.MailConstants;
import com.sunwul.cloudoffice.server.entity.MailLog;
import com.sunwul.cloudoffice.server.mapper.EmployeeMapper;
import com.sunwul.cloudoffice.server.mapper.MailLogMapper;
import com.sunwul.cloudoffice.server.mapper.SalaryMapper;
import com.sunwul.cloudoffice.server.service.EmployeeService;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailLogMapper mailLogMapper;

    @Autowired
    private SalaryMapper salaryMapper;


    /**
     * 获取所有员工(分页)
     *
     * @param currentPage    当前页
     * @param size           数据数量
     * @param employee       员工信息
     * @param beginDateScope 开始日期范围
     * @return ResponsePageBean 分页统一返回对象
     */
    @Override
    public ResponsePageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        /**
         * 记一次mybatis-plus分页不生效, 具体情况查看mybatis-plus配置bean
         * @see com.sunwul.cloudoffice.server.config.MyBatisPlusConfig
         */
//        System.out.println("当前分页总页数: "+employeeByPage.getPages());
//        System.out.println("当前满足条件总行数: "+employeeByPage.getTotal());
//        System.out.println("分页记录列表: "+employeeByPage.getRecords());
//        System.out.println("获取每页显示条数: "+employeeByPage.getSize());
//        System.out.println("当前页: "+employeeByPage.getCurrent());
        return new ResponsePageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
    }

    /**
     * 获取最新工号
     *
     * @return 操作结果
     */
    @Override
    public ResponseBean getMaxWorkId() {
        // 1. 获取当前的最大工号,虽然返回结果是一个list,但本质上里面只有一组数据,key就是我们要查的MAX(work_id), value就是查出来的值
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("MAX(work_id)"));
        // 2. 获取具体的id值 然后加1
//        Integer.parseInt(maps.get(0).get("MAX(work_id)").toString())+1;
        // 3. 数据库要存varchar, 此处为了防止乱码, 使用String格式化,将数值转为字符串, 此时获取的就是最大工号加1, 可以分配给新员工
//        String.format("%08d", Integer.parseInt(maps.get(0).get("MAX(work_id)").toString()) + 1);

        // ********** 修改, 数据库直接存储Int, 此处不再转换为String
//        return ResponseBean.success("获取最新工号成功!", String.format("%08d", Integer.parseInt(maps.get(0).get("MAX(work_id)").toString()) + 1));

        int max_work_id = (int) maps.get(0).get("MAX(work_id)") + 1;
        return ResponseBean.success(max_work_id);
    }

    /**
     * 添加员工
     *
     * @param employee 员工信息
     * @return 操作结果
     * @throws AmqpConnectException rabbitMQ连接异常
     */
    @Override
    public ResponseBean addEmp(Employee employee) throws AmqpConnectException {
        // 使用mybatis-plus的insert插入数据,返回受影响的行数
        if (employeeMapper.insert(dateUtils.getNewEmployee(employee)) == 1) {
            // 查询员工 - 当传入ID时查询此员工的信息,当未传入ID时查询所有员工信息
            // 此处传入了id,所以返回值虽然是List但只有一个值,直接get(0)获取
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            // 使用UUID生成消息ID
            String msgId = UUID.randomUUID().toString();
//            String msgId = "123456789";  // 消息的幂等性测试,设置固定的消息ID
            // 设置MailLog
            MailLog mailLog = new MailLog();
            // 写入消息ID
            mailLog.setMsgid(msgId);
            // 写入员工id
            mailLog.setEid(emp.getId());
            // 写入消息状态
            mailLog.setStatus(0);
            // 写入路由键
            mailLog.setRoutekey(MailConstants.MAIL_ROUTING_KEY_NAME);
            // 写入交换机
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            // 写入重试次数
            mailLog.setCount(0);
            // 写入重试时间  在当前时间往后推一分钟
            mailLog.setTrytime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            // 创建时间
            mailLog.setCreatetime(LocalDateTime.now());
            // 更新时间
            mailLog.setUpdatetime(LocalDateTime.now());
            // 消息入库
            mailLogMapper.insert(mailLog);
            // rabbitMQ传递消息
            // 传递对象: emp 注意发送消息的传递对象必须实现Serializable接口
            // 这里传递的是从数据库中获取的员工完整信息,对象中包含字典对象的属性
            // 不能直接用添加员工功能传进来的信息, 应为不包含字段对象的属性,只有ID
            // 设置邮件内容的时候获取不到我们要的属性, 会异常
            // CorrelationData 带上这个在发送消息的时候才能获取到msgId
            /** @see com.sunwul.cloudoffice.mail.MailReceiver  line:87*/
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME,
                    emp,
                    new CorrelationData(msgId));
            return ResponseBean.success("添加员工信息成功!");
        }
        return ResponseBean.error("添加员工信息失败!");
    }

    /**
     * 查询员工(当传入ID时查询此员工的信息,当未传入ID时查询所有员工信息)
     *
     * @param id 员工ID
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    /**
     * 获取所有员工账套(分页)
     *
     * @param currentPage 当前页
     * @param size        总数量
     * @return ResponsePageBean
     */
    @Override
    public ResponsePageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeWithSalary = salaryMapper.getEmployeeWithSalary(page);
        return new ResponsePageBean(employeeWithSalary.getTotal(),
                employeeWithSalary.getRecords());
    }
}
