package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface AdminMapper extends BaseMapper<Admin> {


    /**
     * 获取所有操作员
     *
     * @param keywords 关键字
     * @return 操作员列表
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
