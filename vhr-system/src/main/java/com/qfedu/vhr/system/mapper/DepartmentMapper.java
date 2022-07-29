package com.qfedu.vhr.system.mapper;

import com.qfedu.vhr.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.vhr.system.entity.vo.AddDepartmentVO;
import com.qfedu.vhr.system.entity.vo.DepartmentChildrenVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<DepartmentChildrenVO> getDepartmentByPid(int pid);

    void addDepartment(AddDepartmentVO department);

    void deleteDepartmentById(AddDepartmentVO addDepartmentVO);
}
