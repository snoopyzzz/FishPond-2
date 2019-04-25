package com.zz.service;

import com.zz.base.BaseService;
import com.zz.entity.Pond;
import com.zz.vo.response.PondComResponse;

import java.util.List;

/**池塘Service
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 20:43
 * Description: No Description
 */
public interface PondService extends BaseService<Pond> {

    List<PondComResponse> getPondCom();
}
