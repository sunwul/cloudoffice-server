package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunwul.cloudoffice.server.entity.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid  角色ID
     * @param mids 菜单数组
     * @return 受影响的行数
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
