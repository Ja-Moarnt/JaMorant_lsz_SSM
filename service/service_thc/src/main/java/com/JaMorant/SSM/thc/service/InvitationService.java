package com.JaMorant.SSM.thc.service;

import com.JaMorant.SSM.model.thc.Invitation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 补货邀约 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-02
 */
public interface InvitationService extends IService<Invitation> {

    List<Invitation> GetInvitationListByCarid(Long carId);

    List<Invitation> GetInvitationListByTungyId(Long tungyId);

    List<Invitation> GetInvitationListByteacherId(Long teacherId);

    Invitation GetInvitationById(Long id);
}
