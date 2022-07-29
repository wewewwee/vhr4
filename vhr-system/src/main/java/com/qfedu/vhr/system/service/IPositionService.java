package com.qfedu.vhr.system.service;

import com.qfedu.vhr.framework.entity.RespBean;
import com.qfedu.vhr.framework.entity.RespPageBean;
import com.qfedu.vhr.system.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qf
 * @since 2022-07-25
 */
public interface IPositionService extends IService<Position> {

    int savePosition(Position position);

    int deletePositionById(Integer id);

    RespBean importPositionData(MultipartFile file);

    RespPageBean getPositionsByPage(Integer page, Integer size);
}
