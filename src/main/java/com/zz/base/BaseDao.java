package com.zz.base;

import com.zz.util.FishPondRuntimeException;

import java.util.List;

/**基础Dao
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/15
 * Time: 21:52
 * Description: No Description
 */
public interface BaseDao {

    /**
     * 保存对象
     * @param var1
     * @return
     * @throws FishPondRuntimeException
     */
    Integer save(Object var1) throws FishPondRuntimeException;

    /**
     * 按ID删除对象
     * @param var1
     * @return
     * @throws FishPondRuntimeException
     */
    Integer delById(Long var1) throws FishPondRuntimeException;

    /**
     * 按实体修改对象
     * @param var1
     * @return
     * @throws FishPondRuntimeException
     */
    Integer modify(Object var1) throws FishPondRuntimeException;


    /**
     * 按ID获取实体
     * @param var1
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> T get(long var1) throws FishPondRuntimeException;

    /**
     * 根据实体查找实体
     * @param var1
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> List<T> findBy(Object var1) throws FishPondRuntimeException;
}
