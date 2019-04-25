package com.zz.shiro;

import com.zz.dao.UserDao;
import com.zz.entity.User;
import com.zz.util.FishPondRuntimeException;
import com.zz.util.ReturnCodeEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/12
 * Time: 19:44
 * Description: No Description
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("sys:manager");
        info.addStringPermission("user");
        System.out.println("开始授权");
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名
        String username = (String)authenticationToken.getPrincipal();
        if ( username == null || username.length() == 0 || username.trim().length() == 0 ) {
            throw new UnknownAccountException("帐号不存在!");
        }
        //得到密码
        String password = new String((char[])authenticationToken.getCredentials());
        //通过用户名获取帐号信息
        System.out.println("UserRealm-findByUsername ================================");
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UnknownAccountException("帐号不存在!");//没找到帐号
        }
        //校验密码
        try {
            User check = new User();
            check.setPassword(password);
            check.setUsername(username);
            System.out.println("UserRealm-findBy ================================");
            List<User> users = userDao.findBy(check);
            if(users==null || users.size()==0){
                throw new IncorrectCredentialsException("密码错误！");
            }
        }catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException("密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new FishPondRuntimeException(ReturnCodeEnum.USER_ERROR, "用户登录异常");
        }

        //每次登陆成功获取权限
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
        return simpleAuthenticationInfo;
    }

}
