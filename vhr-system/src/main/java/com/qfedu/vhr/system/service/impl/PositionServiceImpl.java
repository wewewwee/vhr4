package com.qfedu.vhr.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.framework.entity.RespPageBean;
import com.qfedu.vhr.system.entity.Position;
import com.qfedu.vhr.system.excel.PositionListener;
import com.qfedu.vhr.system.mapper.PositionMapper;
import com.qfedu.vhr.system.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Override
    public int savePosition(Position position) {
        QueryWrapper<Position> qw = new QueryWrapper<>();
        qw.lambda().eq(Position::getName, position.getName());
        Position one = getOne(qw);
        if (one != null) {
            //说明职位名称已经存在
            return -1;
        }
        position.setCreateDate(LocalDateTime.now());
        position.setEnabled(true);
        return save(position) ? 1 : -2;
    }

    @Override
    public int deletePositionById(Integer id) {
        Position p = getById(id);
        if (p == null) {
            //说明要删除的数据不存在
            return -1;
        }
        return removeById(id) ? 1 : -2;
    }

    @Override
    public RespBean importPositionData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), Position.class,new PositionListener(this)).sheet().doRead();
            return RespBean.ok("导入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }

    @Override
    public RespPageBean getPositionsByPage(Integer page, Integer size) {
        Page<Position> p = page(Page.of(page, size));
        return new RespPageBean(p.getTotal(), p.getRecords());
    }
}
