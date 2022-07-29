package com.qfedu.vhr.admin.controller;

import com.qfedu.vhr.framework.entity.vo.MenuVO;
import com.qfedu.vhr.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping("/menus")
    public List<MenuVO> getAllMenus() {
        return menuService.getAllMenus();
    }
}
