package com.qfedu.vhr.system.service;

import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfedu.vhr.system.entity.vo.AddDepartmentVO;
import com.qfedu.vhr.system.entity.vo.DepartmentChildrenVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
public interface IDepartmentService extends IService<Department> {

    List<DepartmentChildrenVO> getAllDepts();

    RespBean addDepartment(AddDepartmentVO department);

    RespBean deleteDepartmentById(Integer id);
}
