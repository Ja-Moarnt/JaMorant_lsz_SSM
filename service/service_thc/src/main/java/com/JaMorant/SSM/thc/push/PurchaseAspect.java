package com.JaMorant.SSM.thc.push;

import com.JaMorant.SSM.model.thc.Invitation;
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
public class PurchaseAspect {

    @Autowired
    private Pushcontroller pushcontroller;

    @AfterReturning(value = "execution(public * com.JaMorant.SSM.thc.controller.PurchaseInfoController.EndPurchase(..)) && args(purchaseId)", returning = "result")
    public void afterEndPurchase(Long purchaseId, Result result) throws IOException {
        // 在该方法执行后调用其他接口
        pushcontroller.SendPurchase(purchaseId);
    }

    @AfterReturning(value = "execution(public * com.JaMorant.SSM.thc.controller.InvitationController.SaveInvitation(..)) && args(invitation)", returning = "result")
    public void afterSaveInvitation(Invitation invitation, Result result) throws IOException {
        // 在该方法执行后调用其他接口
        pushcontroller.SendInvitation(invitation.getId());
    }






}


