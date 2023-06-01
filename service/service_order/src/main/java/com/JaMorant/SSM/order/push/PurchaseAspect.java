package com.JaMorant.SSM.order.push;

import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.OrderEndVo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author:JaMorant
 * @time:2023/3/6 16:45
 * @explain:
 */
@Component
@Aspect
public class PurchaseAspect {

    @Autowired
    private Pushcontroller pushcontroller;

    @AfterReturning(value = "execution(public * com.JaMorant.SSM.order.api.OrderInfoApiController.endOrder(..)) && args(orderEndVo)", returning = "result")
    public void afterendOrder(OrderEndVo orderEndVo, Result result) throws IOException {
        Object data = result.getData();
        Long orderId=(Long) data;
        // 在该方法执行后调用其他接口
        pushcontroller.SendOrder(orderId);
    }




}


