package com.qfedu.vhr.framework.mapper;

import com.qfedu.vhr.framework.entity.Hr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qf
 * @since 2022-07-22
 */
public interface HrMapper extends BaseMapper<Hr> {

    Set<String> getRoleNameByHrid(Integer id);
}
