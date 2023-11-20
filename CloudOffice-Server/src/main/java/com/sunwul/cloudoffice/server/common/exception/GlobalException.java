package com.sunwul.cloudoffice.server.common.exception;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/*****
 * @author sunwul
 * @date 2021/3/24 21:50
 * @description 全局异常处理  -- 只拦截控制器异常
 * * @RestControllerAdvice 表示这是控制器的一个增强类
 * 如果发生异常, 并且符合类中定义的异常, 会拦截此异常进行自定义处理逻辑
 * 异常捕获 @ExceptionHandler(特定异常.class)
 */
@RestControllerAdvice
public class GlobalException {

    private final static Logger log = LoggerFactory.getLogger(GlobalException.class);


    /**
     * 捕捉SQL异常
     *
     * @param e SQLException
     * @return 自定义异常返回结果
     */
    @ExceptionHandler(SQLException.class)
    public ResponseBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResponseBean.error("global - 该数据有关联数据,操作失败!");
        }
        if (e instanceof CommunicationsException) {
            return ResponseBean.error("global - 数据库通讯异常,请检查数据库连接!");
        }
        return ResponseBean.error("global - 未捕获的SQLException!", e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseBean runtimeException(RuntimeException e) {
        if (e instanceof CannotGetJdbcConnectionException) {
            return ResponseBean.error("global - 无法连接到数据库,请检查数据库是否启动正常!");
        }
        if (e instanceof CJCommunicationsException) {
            return ResponseBean.error("global - MySQL数据库通讯链路故障,请检查数据库连接!");
        }
        if (e instanceof UsernameNotFoundException) {
            return ResponseBean.error("global - 此用户不存在!");
        }
        if (e instanceof AmqpConnectException) {
            return ResponseBean.error("global - 操作可能已成功! 但RabbitMQ消息队列相关操作失败, MQ连接异常!");
        }
        if(e instanceof NullPointerException){
            return ResponseBean.error("global - 空指针异常!请联系开发者.");
        }
        return ResponseBean.error("global - 未捕获的RuntimeException!", e.getMessage());
    }

    @ExceptionHandler(CJCommunicationsException.class)
    public ResponseBean CJCommunicationsException(CJCommunicationsException e) {
        return ResponseBean.error("global - MySQL数据库通讯链路故障,请检查数据库连接!----由CJ异常捕获");
    }

    @ExceptionHandler(IOException.class)
    public ResponseBean IOException(IOException e) {
        if (e instanceof ConnectException) {
            return ResponseBean.error("global - 连接异常");
        }
        return ResponseBean.error("global - 未捕获的IOException!", e.getMessage());
    }

}
