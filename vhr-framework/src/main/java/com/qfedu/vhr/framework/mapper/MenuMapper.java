package com.qfedu.vhr.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.vhr.framework.entity.Menu;
import com.qfedu.vhr.framework.entity.vo.MenuRoleVO;
import com.qfedu.vhr.framework.entity.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuVO> getAllMenusByHrId(Integer hrid);

    List<MenuRoleVO> getAllMenuRoles();

    List<MenuVO> getAllMenusWithChildren();

    List<Integer> getSelectedMenus(String name);

    void deleteMenuRoleByRid(Integer rid);

    void addRoleMenus(@Param("rid") Integer rid, @Param("mids") Integer[] mid);
}
