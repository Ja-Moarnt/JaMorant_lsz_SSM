package com.JaMorant.SSM.utils;

import java.lang.reflect.Type;
import java.sql.Time;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

/**
 * 自定义 {@link java.sql.Time} 时间类型反序列化规则。<br>
 * 支持格式为 <b>HH:ss</b>
 * @author jerryjin
 *
 */
public class SqlTimeDeserializer implements ObjectDeserializer  {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object val = parser.parse();
        if(val != null){
            String timeStr = val.toString(); //格式为 HH:ss
            if( timeStr.indexOf(":") == 2 && timeStr.length() == 5 ){
                return (T) Time.valueOf(val + ":00");
            }
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
