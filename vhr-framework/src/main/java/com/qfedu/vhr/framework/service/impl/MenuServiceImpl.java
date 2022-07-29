package com.qfedu.vhr.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfedu.vhr.framework.entity.Hr;
import com.qfedu.vhr.framework.entity.Menu;
import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.framework.entity.vo.MenuRoleVO;
import com.qfedu.vhr.framework.entity.vo.MenuVO;
import com.qfedu.vhr.framework.mapper.MenuMapper;
import com.qfedu.vhr.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<MenuVO> getAllMenus(MenuServiceImpl this) {
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getAllMenusByHrId(hr.getId());
    }

    @Override
    public List<MenuRoleVO> getAllMenuRoles() {
        return menuMapper.getAllMenuRoles();
    }

    @Override
    public List<MenuVO> getAllMenusWithChildren() {
        return menuMapper.getAllMenusWithChildren();
    }

    @Override
    public List<Integer> getSelectedMenus(String name) {
        return menuMapper.getSelectedMenus(name);
    }

    @Override
    @Transactional
    public RespBean updateRoleMenus(Integer rid, Integer[] mid) {
        menuMapper.deleteMenuRoleByRid(rid);
        menuMapper.addRoleMenus(rid, mid);
        return RespBean.ok("更新成功");
    }
}
