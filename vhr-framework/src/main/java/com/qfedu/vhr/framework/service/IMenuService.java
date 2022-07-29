package com.qfedu.vhr.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfedu.vhr.framework.entity.Menu;
import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.framework.entity.vo.MenuRoleVO;
import com.qfedu.vhr.framework.entity.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
public interface IMenuService extends IService<Menu> {

    List<MenuVO> getAllMenus();

    List<MenuRoleVO> getAllMenuRoles();

    List<MenuVO> getAllMenusWithChildren();

    List<Integer> getSelectedMenus(String name);

    RespBean updateRoleMenus(Integer rid, Integer[] mid);
}
