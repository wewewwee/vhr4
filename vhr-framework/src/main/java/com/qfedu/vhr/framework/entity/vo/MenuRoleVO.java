package com.qfedu.vhr.framework.entity.vo;


import com.qfedu.vhr.framework.entity.Menu;
import com.qfedu.vhr.framework.entity.Role;

import java.util.List;
import java.util.Set;

public class MenuRoleVO extends Menu {
    //这个菜单项，有哪些角色可以访问
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
