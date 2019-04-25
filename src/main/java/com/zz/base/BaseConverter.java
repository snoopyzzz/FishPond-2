package com.zz.base;


import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/22
 * Time: 13:31
 * Description: No Description
 */
@Component
public class BaseConverter<S, T> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseConverter() {
    }

    public T convert(S s, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            logger.error("获取转换类型对象失败{}。", clazz, e);
        }

        if (s != null) {
            convert(s, t);
        }
        return t;
    }

    public List<T> convert(List<S> fromList, Class<T> clazz){
        if (CollectionUtils.isEmpty(fromList)){
            return new ArrayList<>();
        }

        List<T> toList = new ArrayList<>();
        for (S from : fromList){
            toList.add(convert(from,clazz));
        }
        return toList;
    }

    protected void convert(S s, T t) {
        BeanUtils.copyProperties(s, t);
    }
}
