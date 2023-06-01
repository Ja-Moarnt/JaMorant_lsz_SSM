package com.JaMorant.SSM.thc.service;

import com.JaMorant.SSM.model.thc.Chengshi;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-19
 */
public interface ChengshiService extends IService<Chengshi> {

    List<Chengshi> selectChengshiList(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
