package com.zz.service.impl;

import com.zz.dao.UserDao;
import com.zz.entity.User;
import com.zz.service.UserService;
import com.zz.util.FishPondRuntimeException;
import com.zz.util.Jo;
import com.zz.util.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 17:16
 * Description: No Description
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param user
     * @return
     * @throws FishPondRuntimeException
     */
    @Override
    public Jo login(User user) throws FishPondRuntimeException {
        try{
            User checkName = userDao.findByUsername(user.getUsername());
            //先判断用户名是否存在
            if (checkName == null){
                return new Jo().fail("该用户名不存在");
            }
            //判断密码是否为空
            if (user.getPassword() == null || user.getPassword().equals("")){
                return new Jo().fail("密码不能为空");
            }
            //判断密码是否正确
            List<User> checkPwd = userDao.findBy(user);
            if ( checkPwd == null || checkPwd.size() == 0){
                return new Jo().fail("密码错误");
            }
            return new Jo().success().sendData(checkPwd);
        }catch (FishPondRuntimeException e){
            logger.error(e.getMessage());
            throw new FishPondRuntimeException(ReturnCodeEnum.USER_ERROR, "用户登录异常");
        }
    }
}
