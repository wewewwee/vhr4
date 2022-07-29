package com.qfedu.vhr.system.entity.vo;

import com.qfedu.vhr.system.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentChildrenVO extends Department {
    private List<Department> children = new ArrayList<>();

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
