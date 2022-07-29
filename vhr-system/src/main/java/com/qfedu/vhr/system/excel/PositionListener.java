package com.qfedu.vhr.system.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.qfedu.vhr.system.entity.Position;
import com.qfedu.vhr.system.service.IPositionService;

import java.util.ArrayList;
import java.util.List;

public class PositionListener implements ReadListener<Position> {

    private List<Position> positions = new ArrayList<>();
    private IPositionService positionService;

    public PositionListener(IPositionService positionService) {
        this.positionService = positionService;
    }

    /**
     * Excel 文件是被 EasyExcel 自动解析的，每解析一行数据，就通过反射生成一个 Position 对象
     * @param position
     * @param analysisContext
     */
    @Override
    public void invoke(Position position, AnalysisContext analysisContext) {
        position.setId(null);
        positions.add(position);
    }

    /**
     * 整个 Excel 文件解析完成时，会触发整个方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        positionService.saveBatch(positions);
    }
}
