package com.zz.convert;

import com.zz.base.BaseConverter;
import com.zz.entity.Pond;
import com.zz.vo.response.PondComResponse;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/22
 * Time: 16:41
 * Description: No Description
 */
@Component
public class PondPondComResponseConverter extends BaseConverter<Pond, PondComResponse> {

    @Override
    protected void convert(Pond pond, PondComResponse pondComResponse){
        super.convert(pond, pondComResponse);
        pondComResponse.setPondId(pond.getId());
    }
}
