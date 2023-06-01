package com.JaMorant.SSM.thc.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.thc.mapper.ChengshiMapper;
import com.JaMorant.SSM.vo.thc.ChengshiEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChengshiListener extends AnalysisEventListener<ChengshiEeVo> {

    //注入mapper
    @Autowired
    private ChengshiMapper chengshiMapper;

    //一行一行，从第二行
    @Override
    public void invoke(ChengshiEeVo chengshiEeVo, AnalysisContext analysisContext) {
        Chengshi chengshi = new Chengshi();
        //  ChengshiEeVo -- Chengshi
        BeanUtils.copyProperties(chengshiEeVo,chengshi);
        //添加
        chengshiMapper.insert(chengshi);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
