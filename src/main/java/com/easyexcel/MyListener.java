package com.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MyListener extends AnalysisEventListener<DemoData> {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(demoData));
        list.add(demoData);
        if(list.size() > BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        logger.info("所有数据解析完成");
    }

    private void saveData(){
        logger.info("{}条数据开始存储，",list.size());
        list.forEach(
                demoData -> System.out.println(demoData.toString())
        );
        logger.info("成功");
    }
}
