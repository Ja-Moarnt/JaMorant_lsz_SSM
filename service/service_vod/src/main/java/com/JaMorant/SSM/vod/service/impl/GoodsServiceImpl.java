package com.JaMorant.SSM.vod.service.impl;

import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.model.vod.Subject;
import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import com.JaMorant.SSM.vo.vod.GoodsQueryVo;
import com.JaMorant.SSM.vod.mapper.GoodsMapper;
import com.JaMorant.SSM.vod.service.GoodsService;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import com.JaMorant.SSM.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-30
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private SubjectService subjectService;


    @Autowired
    private GoodsVideoService goodsVideoService;

    //查询所有课程
    @Override
    public List<Goods> findlist() {
        List<Goods> list = baseMapper.selectList(null);
        list.stream().forEach(item -> {
            this.getTeacherAndSubjectName(item);
        });
        return list;
    }

    //封装其他数据（获取讲师名称 和 课程分类名称）
    private Goods getTeacherAndSubjectName(Goods goods) {

        //课程分类名称
        Long subjectParentId = goods.getSubjectParentId();
        Subject oneSubject = subjectService.getById(subjectParentId);
        if (oneSubject != null) {
            goods.getParam().put("subjectParentTitle", oneSubject.getTitle());
        }
        Long subjectId = goods.getSubjectId();
        Subject twoSubject = subjectService.getById(subjectId);
        if (twoSubject != null) {
            goods.getParam().put("subjectTitle", twoSubject.getTitle());
        }
        return goods;
    }

    //商品列表
    @Override
    public Map<String, Object> findPageCourse(Page<Goods> pageParam,
                                              GoodsQueryVo goodsQueryVo) {
        //获取条件值
        String title = goodsQueryVo.getTitle();
        Long subjectId = goodsQueryVo.getSubjectId();//二层分类
        Long subjectParentId = goodsQueryVo.getSubjectParentId();//一层分类
        BigDecimal price1 = goodsQueryVo.getPrice1();//单价
        BigDecimal price2 = goodsQueryVo.getPrice2();//单价
//判断条件为空，封装条件
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(price1)) {
            wrapper.ge("price", price1);//大于等于
        }

        if (!StringUtils.isEmpty(price2)) {
            wrapper.le("price", price2);//小于等于
        }

        //调用方法实现条件查询分页
        Page<Goods> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        List<Goods> list = pages.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        list.stream().forEach(item -> {
            this.getTeacherAndSubjectName(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", list);
        return map;
    }

    @Override
    public Long saveGoodsInfo(GoodsFormVo goodsFormVo) {
        //添加课程基本信息，操作course表
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsFormVo, goods);
        baseMapper.insert(goods);
        return goods.getId();
    }

    //根据id查询课程信息
    @Override
    public GoodsFormVo getGoodsInfoById(Long id) {
        //课程基本信息
        Goods goods = baseMapper.selectById(id);
        if (goods == null) {
            return null;
        }
        //封装
        GoodsFormVo goodsFormVo = new GoodsFormVo();
        BeanUtils.copyProperties(goods, goodsFormVo);
        return goodsFormVo;
    }

    //修改课程信息
    @Override
    public void updateGoodsId(GoodsFormVo goodsFormVo) {
        //修改课程基本信息
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsFormVo, goods);
        baseMapper.updateById(goods);
        //修改课程描述信息
    }


    //课程最终发布
    @Override
    public void publishGoods(Long id) {
        Goods goods = baseMapper.selectById(id);
        goods.setStatus(1);//已经发布
        goods.setPublishTime(new Date());
        baseMapper.updateById(goods);
    }

    //删除课程
    @Override
    public void removeGoodsId(Long id) {
//        //根据课程id删除小节
//        videoService.removeVideoByCourseId(id);
//
//        //根据课程id删除章节
//        chapterService.removeChapterByCourseId(id);
//
//        //根据课程id删除课程描述
//        descriptionService.removeById(id);
        goodsVideoService.removeGoodsVideoByGoodsId(id);

        //根据课程id删除课程
        baseMapper.deleteById(id);
    }

    //批量删除
    //删除课程
    @Override
    public void PiremoveGoodsId(List<Long> idList) {
//        //根据课程id删除小节
//        videoService.removeVideoByCourseId(id);
//
//        //根据课程id删除章节
//        chapterService.removeChapterByCourseId(id);
//
//        //根据课程id删除课程描述
//        descriptionService.removeById(id);
        for (Long id:idList) {
            goodsVideoService.removeGoodsVideoByGoodsId(id);

            //根据课程id删除课程
            baseMapper.deleteById(id);
        }
    }

    @Override
    public List<Goods> GetgoodsBysubid( Long subjectId) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        //调用方法实现条件查询分页
        List<Goods> goodsList = baseMapper.selectList(wrapper);
        return goodsList;
    }

    //根据课程id查询课程详情
    @Override
    public Map<String, Object> getInfoById(Long goodsId) {
            //view_count浏览数量 +1
        Goods goods = baseMapper.selectById(goodsId);
//        course.setViewCount(course.getViewCount()+1);
//            baseMapper.updateById(course);

//            //根据课程id查询
//            //课程详情数据
//            CourseVo courseVo = baseMapper.selectCourseVoById(courseId);
//            //课程章节小节数据
//            List<ChapterVo> chapterVoList = chapterService.getTreeList(courseId);
//            //课程描述信息
//            CourseDescription courseDescription = descriptionService.getById(courseId);
//            //课程所属讲师信息
//            Teacher teacher = teacherService.getById(course.getTeacherId());

            //封装map集合，返回
            Map<String,Object> map = new HashMap();
//            map.put("courseVo", courseVo);
//            map.put("chapterVoList", chapterVoList);
//            map.put("description", null != courseDescription ? courseDescription.getDescription() : "");
//            map.put("teacher", teacher);
//            map.put("isBuy", false);//是否购买
            return map;

    }


}
