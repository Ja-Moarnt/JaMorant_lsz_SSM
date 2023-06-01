package com.JaMorant.SSM.thc.service.impl;

import com.JaMorant.SSM.thc.listener.ChengshiListener;
import com.alibaba.excel.EasyExcel;
import com.JaMorant.SSM.exception.GgktException;
import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.thc.mapper.ChengshiMapper;
import com.JaMorant.SSM.thc.service.ChengshiService;
import com.JaMorant.SSM.vo.thc.ChengshiEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-19
 */
@Service
public class ChengshiServiceImpl extends ServiceImpl<ChengshiMapper, Chengshi> implements ChengshiService {

        @Autowired
        private ChengshiListener chengshiListener;
        //课程分类列表
        //懒加载，每次查询一层数据
        @Override
        public List<Chengshi> selectChengshiList(Long id) {
            //SELECT * FROM SUBJECT WHERE parent_id=0
            QueryWrapper<Chengshi> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id",id);
            List<Chengshi> chengshiList = baseMapper.selectList(wrapper);
            //chengshiList遍历，得到每个chengshi对象，判断是否有下一层数据，有hasChildren=true
            for (Chengshi chengshi:chengshiList) {
                //获取chengshi的id值
                Long chengshiId = chengshi.getId();
                //查询
                boolean isChild = this.isChildren(chengshiId);
                //封装到对象里面
                chengshi.setHasChildren(isChild);
            }
            return chengshiList;
        }

        //课程分类导出
        @Override
        public void exportData(HttpServletResponse response) {
            try {
                //设置下载信息
                response.setContentType("application/vnd.ms-excel");
                response.setCharacterEncoding("utf-8");
                // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
                String fileName = URLEncoder.encode("城市分类", "UTF-8");
                response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

                //查询课程分类表所有数据
                List<Chengshi> chengshiList = baseMapper.selectList(null);

                //List<Chengshi> ---  List<ChengshiEeVo>
                List<ChengshiEeVo> chengshiEeVoList = new ArrayList<>();
                for (Chengshi chengshi: chengshiList) {
                    ChengshiEeVo chengshiEeVo = new ChengshiEeVo();
//                chengshiEeVo.setId(chengshi.getId());
//                chengshiEeVo.setParentId(chengshi.getParentId());
                    BeanUtils.copyProperties(chengshi,chengshiEeVo);
                    chengshiEeVoList.add(chengshiEeVo);
                }

                //EasyExcel写操作
                EasyExcel.write(response.getOutputStream(), ChengshiEeVo.class)
                        .sheet("城市分类")
                        .doWrite(chengshiEeVoList);
            }catch(Exception e) {
                throw new GgktException(20001,"导出失败");
            }
        }

        //课程分类导入
        @Override
        public void importData(MultipartFile file) {
            try {
                EasyExcel.read(file.getInputStream(),
                        ChengshiEeVo.class,
                        chengshiListener).sheet().doRead();
            } catch (IOException e) {
                throw new GgktException(20001,"导入失败");
            }
        }

        //判断是否有下一层数据
        private boolean isChildren(Long chengshiId) {
            QueryWrapper<Chengshi> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id",chengshiId);
            Integer count = baseMapper.selectCount(wrapper);
            // 1>0  true   0>0 false
            return count>0;
        }


}
