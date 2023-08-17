package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunwul.cloudoffice.server.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员角色
     *
     * @param adminId 操作员id
     * @param rids    角色id数组
     * @return 操作结果
     */
    Integer insertAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
