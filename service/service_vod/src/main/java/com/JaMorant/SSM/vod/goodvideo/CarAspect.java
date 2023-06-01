package com.JaMorant.SSM.vod.goodvideo;

import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.result.Result;
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
public class CarAspect {

    @Autowired
    private TextController textController;

    @AfterReturning(value = "execution(public * com.JaMorant.SSM.vod.controller.CarController.updateCarShoping(..)) && args(car)", returning = "result")
    public void afterUpdateCarShoping(Car car, Result result) throws IOException {
        // 在该方法执行后调用其他接口
        textController.sendMessage(car.getId());
    }

    @AfterReturning(value = "execution(public * com.JaMorant.SSM.vod.controller.CarController.updateCarVideo(..)) && args(carId,orderId)", returning = "result")
    public void afterUpdateCarVideo(Long carId,Long orderId, Result result) throws IOException {
        // 在该方法执行后调用其他接口
        textController.sendMessage(carId);
    }




}


