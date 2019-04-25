package com.zz.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.shiro.WebSubjectUtils;
import com.zz.util.FishPondRuntimeException;
import com.zz.util.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/16
 * Time: 10:09
 * Description: No Description
 */
public abstract class BaseServiceImpl<E extends BaseEntity> implements BaseService<E> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseServiceImpl() {
    }

    protected abstract BaseDao getDao();

    @Override
    public Integer save(E t) throws FishPondRuntimeException {
        try {
            t.setCreateId(WebSubjectUtils.getSessionValue().getId());
            return getDao().save(t);
        }catch (FishPondRuntimeException e) {
            logger.error("save", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR,"数据解析异常");
        }
    }

    @Override
    public Integer delById(Long id) throws FishPondRuntimeException {
        try {
            return getDao().delById(id);
        } catch (Exception e) {
            logger.error("delById",e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR,"数据解析异常");
        }
    }

    @Override
    public Integer modify(E t) throws FishPondRuntimeException {
        try {
            t.setUpdateId(WebSubjectUtils.getSessionValue().getId());
            return getDao().modify(t);
        }catch (FishPondRuntimeException e){
            logger.error("modify", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据解析异常");
        }
    }

    @Override
    public <T> T get(Long key) throws FishPondRuntimeException {
        try {
            return this.getDao().get(key);
        }catch (FishPondRuntimeException e){
            this.logger.error("get", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据分析异常");
        }
    }

    @Override
    public <T> List<T> findBy(E t) throws FishPondRuntimeException {
        try {
            return getDao().findBy(t);
        }catch (FishPondRuntimeException e){
            this.logger.error("findBy", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据分析异常");
        }
    }

    @Override
    public <T> PageInfo<T> findPage(E entity, int pageNum, int pageSize) throws FishPondRuntimeException {
        try {
            return findPage(entity, pageNum, pageSize, "id DESC");
        }catch (FishPondRuntimeException e){
            this.logger.error("findBy", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据分析异常");
        }
    }

    @Override
    public <T> PageInfo<T> findPage(E entity, int pageNum, int pageSize, String orderBy) throws FishPondRuntimeException {
        try {
            PageHelper.startPage(pageNum, pageSize).setOrderBy(orderBy);
            List<T> t = getDao().findBy(entity);
            return new PageInfo<>(t);
        }catch (FishPondRuntimeException e){
            this.logger.error("findBy", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据分析异常");
        }
    }


}
