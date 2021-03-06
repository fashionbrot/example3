package com.github.fashionbrot.console.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.fashionbrot.core.service.UserLoginService;
import com.github.fashionbrot.core.service.impl.SysUserServiceImpl;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FieldMetaObjectHandler implements MetaObjectHandler, BeanFactoryAware {

    private final static String CREATE_ID = "createId";
    private final static String CREATE_DATE = "createDate";
    private final static String UPDATE_ID = "updateId";
    private final static String UPDATE_DATE = "updateDate";
    private final static String DEL_FLAG = "delFlag"; //删除标志位 1删除 0未删除





    /**
     * insert 表时添加必填字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (hasFiled(CREATE_ID,metaObject)) {
            setFieldValByName(CREATE_ID, getUserId(), metaObject);
        }
        if (hasFiled(CREATE_DATE,metaObject)) {
            Date date = new Date();
            setFieldValByName(CREATE_DATE, date, metaObject);
        }
        if (hasFiled(DEL_FLAG,metaObject)) {
            setFieldValByName(DEL_FLAG, 0, metaObject);
        }
    }

    /**
     * update 表时更新必填字段
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (hasFiled(UPDATE_ID,metaObject)) {
            setFieldValByName(UPDATE_ID,getUserId(),metaObject);
        }
        if (hasFiled(UPDATE_DATE,metaObject)) {
            Date date = new Date();
            setFieldValByName(UPDATE_DATE, date, metaObject);
        }
    }

    public boolean hasFiled(String filed, MetaObject metaObject){
        if (metaObject.hasGetter(filed) || metaObject.hasGetter("et."+filed)) {
            return true;
        }
        return false;
    }

    private Long getUserId(){
        LoginModel safeLogin = userLoginService.getSafeLogin();
        if (safeLogin!=null){
            return safeLogin.getUserId();
        }
        return 0L;
    }

    UserLoginService userLoginService;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        userLoginService = beanFactory.getBean(UserLoginService.class);
    }
}
