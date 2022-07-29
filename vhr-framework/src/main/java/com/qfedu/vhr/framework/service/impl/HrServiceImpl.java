package com.qfedu.vhr.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfedu.vhr.framework.entity.Hr;
import com.qfedu.vhr.framework.mapper.HrMapper;
import com.qfedu.vhr.framework.service.IHrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qf
 * @since 2022-07-22
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService, UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Hr> qw = new QueryWrapper<>();
        qw.lambda().eq(Hr::getUsername, username);
        Hr hr = getOne(qw);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在，登录失败");
        }
        hr.setRoles(hrMapper.getRoleNameByHrid(hr.getId()));
        return hr;
    }
}
