package com.sunwul.cloudoffice.generator.service.impl;

import com.sunwul.cloudoffice.generator.entity.MailLog;
import com.sunwul.cloudoffice.generator.mapper.MailLogMapper;
import com.sunwul.cloudoffice.generator.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
