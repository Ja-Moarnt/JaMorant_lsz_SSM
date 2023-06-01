package com.JaMorant.SSM.wechat.service.impl;

import com.JaMorant.SSM.client.course.CourseFeignClient;
import com.JaMorant.SSM.model.vod.Course;
import com.JaMorant.SSM.wechat.service.MessageService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private CourseFeignClient courseFeignClient;

    @Autowired
    private WxMpService wxMpService;

    //接收微信服务器发送来的消息
    @Override
    public String receiveMessage(Map<String, String> param) {
        String content = "";
        String msgType = param.get("MsgType");
        //判断什么类型消息
        switch (msgType) {
            case "text":   //普通文本类型，输入关键字java
                content = this.search(param);
                break;
            case "event":  //关注  取消关注  点击关于我们
                String event = param.get("Event");
                String eventKey = param.get("EventKey");
                //关注
                if("subscribe".equals(event)) {
                    content = this.subscribe(param);
                } else if("unsubscribe".equals(event)) { //取消关注
                    content = this.unsubscribe(param);
                } else if("CLICK".equals(event) && "aboutUs".equals(eventKey)) { //关于我们
                    content = this.aboutUs(param);
                } else {
                    content = "success";
                }
                break;
            default:  //其他情况
                content = "success";
        }

        return content;
    }

    //订单成功
    @Override
    public void pushPayMessage(long id) {
        //微信openid
        String openid = "oRL-_6vGtUYUxZlkFHkW3-xPEO8I";
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openid)//要推送的用户openid
                .templateId("d6RLIV34t9Cl4XvtmFWJTq9T9nvsglu6NtQ6TdBuT7k")//模板id
                .url("http://xff.jamorantxff.fun/#/pay/"+id)//点击模板消息要访问的网址
                .build();
        //3,如果是正式版发送消息，，这里需要配置你的信息
        templateMessage.addData(new WxMpTemplateData("first", "亲爱的用户：您有一笔订单支付成功。", "#FF4500"));
        templateMessage.addData(new WxMpTemplateData("keyword1", "1314520", "#40E0D0"));
        templateMessage.addData(new WxMpTemplateData("keyword2", "红牛", "#B0E0E6"));
        templateMessage.addData(new WxMpTemplateData("keyword3", "2022-01-11", "#B0C4DE"));
        templateMessage.addData(new WxMpTemplateData("keyword4", "100", "#EE82EE"));
        templateMessage.addData(new WxMpTemplateData("remark", "感谢你的购买，如有疑问，随时咨询！", "#4169E1"));
        try {
            String msg = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    //关于我们
    private String aboutUs(Map<String, String> param) {
        StringBuffer msg = this.text(param, "江理理工大学摆烂队");
        return msg.toString();
    }

    //取消
    private String unsubscribe(Map<String, String> param) {
        return "success";
    }

    //关注
    private String subscribe(Map<String, String> param) {
        return this.text(param,"感谢你关注“随手买”，可以根据关键字搜索您想看的需要的商品，如：冰红茶，红牛等").toString();
    }

    /**
     * 处理关键字搜索事件
     * 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     * @param param
     * @return
     */
    private String search(Map<String, String> param) {
        String fromusername = param.get("FromUserName");
        String tousername = param.get("ToUserName");
        String content = param.get("Content");
        //单位为秒，不是毫秒
        Long createTime = new Date().getTime() / 1000;
        StringBuffer text = new StringBuffer();
        List<Course> courseList = courseFeignClient.findByKeyword(content);
        if(CollectionUtils.isEmpty(courseList)) {
            text = this.text(param, "请重新输入关键字，没有匹配到相关视频课程");
        } else {
            //一次只能返回一个
            Random random = new Random();
            int num = random.nextInt(courseList.size());
            Course course = courseList.get(num);
            StringBuffer articles = new StringBuffer();
            articles.append("<item>");
            articles.append("<Title><![CDATA["+course.getTitle()+"]]></Title>");
            articles.append("<Description><![CDATA["+course.getTitle()+"]]></Description>");
            articles.append("<PicUrl><![CDATA["+course.getCover()+"]]></PicUrl>");
            articles.append("<Url><![CDATA[http://glkt.atguigu.cn/#/liveInfo/"+course.getId()+"]]></Url>");
            articles.append("</item>");

            text.append("<xml>");
            text.append("<ToUserName><![CDATA["+fromusername+"]]></ToUserName>");
            text.append("<FromUserName><![CDATA["+tousername+"]]></FromUserName>");
            text.append("<CreateTime><![CDATA["+createTime+"]]></CreateTime>");
            text.append("<MsgType><![CDATA[news]]></MsgType>");
            text.append("<ArticleCount><![CDATA[1]]></ArticleCount>");
            text.append("<Articles>");
            text.append(articles);
            text.append("</Articles>");
            text.append("</xml>");
        }
        return text.toString();
    }

    /**
     * 回复文本
     * @param param
     * @param content
     * @return
     */
    private StringBuffer text(Map<String, String> param, String content) {
        String fromusername = param.get("FromUserName");
        String tousername = param.get("ToUserName");
        //单位为秒，不是毫秒
        Long createTime = new Date().getTime() / 1000;
        StringBuffer text = new StringBuffer();
        text.append("<xml>");
        text.append("<ToUserName><![CDATA["+fromusername+"]]></ToUserName>");
        text.append("<FromUserName><![CDATA["+tousername+"]]></FromUserName>");
        text.append("<CreateTime><![CDATA["+createTime+"]]></CreateTime>");
        text.append("<MsgType><![CDATA[text]]></MsgType>");
        text.append("<Content><![CDATA["+content+"]]></Content>");
        text.append("</xml>");
        return text;
    }
}
