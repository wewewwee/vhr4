package com.qfedu.vhr.admin.controller.system.basic;

import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.system.entity.Department;
import com.qfedu.vhr.system.entity.vo.AddDepartmentVO;
import com.qfedu.vhr.system.entity.vo.DepartmentChildrenVO;
import com.qfedu.vhr.system.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @GetMapping("/")
    public List<DepartmentChildrenVO> getAllDepts() {
        return departmentService.getAllDepts();
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody AddDepartmentVO department) {
        return departmentService.addDepartment(department);
    }

    @GetMapping("/list")
    public List<Department> getAllDepartments() {
        return departmentService.list();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepartmentById(@PathVariable Integer id) {
        return departmentService.deleteDepartmentById(id);
    }
}
