package com.JaMorant.SSM.vod.service;

import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import com.JaMorant.SSM.vo.vod.GoodsQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-30
 */
public interface GoodsService extends IService<Goods> {
    //查询所有课程
    List<Goods> findlist();
    public Map<String, Object> findPageCourse(Page<Goods> pageParam,
                                              GoodsQueryVo goodsQueryVo);
    //添加课程基本信息
    Long saveGoodsInfo(GoodsFormVo goodsFormVo);

    //根据id查询课程信息
    public GoodsFormVo getGoodsInfoById(Long id);

    //修改课程信息
    public void updateGoodsId(GoodsFormVo goodsFormVo);

    //课程最终发布
    public void publishGoods(Long id);

    //删除商品
    public void removeGoodsId(Long id);

    //批量删除
    public void PiremoveGoodsId(List<Long> idList);

    public List<Goods> GetgoodsBysubid(Long subjectId);

    Map<String, Object> getInfoById(Long goodsId);
}
