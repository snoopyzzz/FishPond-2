package com.zz.base;

import com.zz.util.Jo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/16
 * Time: 10:19
 * Description: No Description
 */
public abstract class BaseController<E extends BaseEntity, D extends BaseService> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract D getService();

    /**
     * 新增实体
     * @param entity
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Jo save(@Valid @RequestBody E entity, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()){
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList){
                return new Jo().fail(error.getDefaultMessage());
            }
        }
        return new Jo().success().sendData(getService().save(entity));
    }

    /**
     * 根据id删除
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Jo delete(@PathVariable Long id) throws Exception{
        return new Jo().success().sendData(getService().delById(id));
    }

    /**
     * 修改
     * @param entity
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "update" , method = RequestMethod.POST, consumes = "application/json")
    public Jo update(@RequestBody E entity, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()){
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList){
                return new Jo().fail(error.getDefaultMessage());
            }
        }
        return new Jo().success().sendData(getService().modify(entity));
    }

    /**
     * 根据id获取
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}" ,method = RequestMethod.GET)
    public Jo get(@PathVariable Long id) throws Exception{
        return new Jo().success().sendData(getService().get(id));
    }

    /**
     * 根据实体对象获取
     * @param entity
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "findBy", method = RequestMethod.GET)
    public Jo findBy(E entity) throws Exception{
        return new Jo().success().sendData(getService().findBy(entity));
    }

    /**
     * 分页查找
     * @param entity
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Jo findPage(E entity,
                       @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws Exception{
        return new Jo().success().sendData(getService().findPage(entity, pageNum, pageSize));
    }
}
