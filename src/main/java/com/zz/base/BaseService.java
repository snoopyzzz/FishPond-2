package com.zz.base;

import com.github.pagehelper.PageInfo;
import com.zz.util.FishPondRuntimeException;

import java.util.List;

/**公共Service
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/16
 * Time: 9:58
 * Description: No Description
 */
public interface BaseService<E extends BaseEntity> {

    /**
     * 保存对象
     * @param t
     * @return
     * @throws FishPondRuntimeException
     */
    Integer save(E t) throws FishPondRuntimeException;

    /**
     * 根据Id删除
     * @param id
     * @return
     * @throws FishPondRuntimeException
     */
    Integer delById(Long id) throws FishPondRuntimeException;

    /**
     * 按编号修改对象
     * @param t
     * @return
     * @throws FishPondRuntimeException
     */
    Integer modify(E t) throws FishPondRuntimeException;

    /**
     * 根据Id查询
     * @param var1
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> T get(Long var1) throws FishPondRuntimeException;

    /**
     * 根据实体查询
     * @param var1
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> List<T> findBy(E var1) throws FishPondRuntimeException;

    /**
     * 分页查询
     * @param var1
     * @param var2
     * @param var3
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> PageInfo<T> findPage(E var1, int var2, int var3) throws FishPondRuntimeException;

    /**
     * 根据排列顺序分页查询
     * @param var1
     * @param var2
     * @param var3
     * @param var4
     * @param <T>
     * @return
     * @throws FishPondRuntimeException
     */
    <T> PageInfo<T> findPage(E var1, int var2, int var3, String var4) throws FishPondRuntimeException;
}
