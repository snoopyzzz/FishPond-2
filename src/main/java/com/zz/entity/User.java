package com.zz.entity;

import com.google.common.base.MoreObjects;
import com.zz.base.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/7
 * Time: 23:27
 * Description: No Description
 */
public class User extends BaseEntity {
    private String username;
    private String password;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("username",username)
                .add("password",password)
                .add("name",name)
                .toString();
    }
}
