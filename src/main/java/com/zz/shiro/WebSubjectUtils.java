package com.zz.shiro;

import com.zz.entity.User;
import org.apache.shiro.SecurityUtils;


/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/16
 * Time: 22:35
 * Description: No Description
 */
public class WebSubjectUtils {

    /**
     * 获取当前用户
     * @return
     */
    public static User getSessionValue(){
        return ((User) SecurityUtils.getSubject().getPrincipal());
    }
}
