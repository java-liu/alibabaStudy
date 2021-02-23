package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/2/18 16:41
 * @Version: 1.0
 */
@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;


    /***
     * 减库存
     * @return
     */
    public String decStockNoLock(){


        Integer stock;
        List<Map<String,Object>> result = jdbcTemplate.queryForList("select * from shop_order where id = 1");

        if(result == null || result.isEmpty() || (stock = (Integer)result.get(0).get("stock")) <= 0){
            logger.info("下单失败，已经没有库存了");
            return "下单失败，已经没有库存了";
        }
        stock--;
        jdbcTemplate.update("update shop_order set stock = ? where id = 1",stock);
        logger.info("下单成功，当前剩余产品数：---->" + stock);

        return "下单成功，当前剩余产品数：---->" + stock;
    }
}
