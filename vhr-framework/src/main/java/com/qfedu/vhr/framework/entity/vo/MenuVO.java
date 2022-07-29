package com.qfedu.vhr.framework.entity.vo;


import com.qfedu.vhr.framework.entity.Menu;

import java.util.List;

public class MenuVO extends Menu {
    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
