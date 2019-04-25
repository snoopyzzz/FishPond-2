package com.zz.service;

import com.zz.entity.User;
import com.zz.util.FishPondRuntimeException;
import com.zz.util.Jo;

/**用户Service
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 17:15
 * Description: No Description
 */
public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     * @throws FishPondRuntimeException
     */
    Jo login(User user) throws FishPondRuntimeException;
}
