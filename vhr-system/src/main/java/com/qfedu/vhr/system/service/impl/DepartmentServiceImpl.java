package com.qfedu.vhr.system.service.impl;

import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.system.entity.Department;
import com.qfedu.vhr.system.entity.vo.AddDepartmentVO;
import com.qfedu.vhr.system.entity.vo.DepartmentChildrenVO;
import com.qfedu.vhr.system.mapper.DepartmentMapper;
import com.qfedu.vhr.system.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentChildrenVO> getAllDepts() {
        return departmentMapper.getDepartmentByPid(-1);
    }

    @Override
    public RespBean addDepartment(AddDepartmentVO department) {
        department.setEnabled(true);
        department.setIsParent(false);
        departmentMapper.addDepartment(department);
        if (department.getResult() == 1) {
            return RespBean.ok("添加成功", department);
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleteDepartmentById(Integer id) {
        AddDepartmentVO addDepartmentVO = new AddDepartmentVO();
        addDepartmentVO.setId(id);
        departmentMapper.deleteDepartmentById(addDepartmentVO);
        if (addDepartmentVO.getResult() == -2) {
            return RespBean.error("该部门下有子部门或者该部门不存在，删除失败");
        } else if (addDepartmentVO.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if (addDepartmentVO.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
