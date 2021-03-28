package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.annotation.PersistentLog;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysUserReq;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysUserEntity;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.fashionbrot.core.service.UserLoginService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */

@MarsPermission(value = "sys:user")
@Controller
@RequestMapping("sys/user")
@Api(tags = "系统用户表")
@ApiSort(23371307)
public class SysUserController {

    @Autowired
    private SysUserService service;

    @Autowired
    private UserLoginService userLoginService;

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @PostMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysUserReq req) {
        return RespVo.success(service.pageReq(req));
    }


    @GetMapping("index")
    public String index() {
        return "/system/user/user";
    }

    @GetMapping("/index/add")
    public String indexAdd() {
        return "/system/user/add";
    }

    @RequestMapping("/index/edit/")
    public String edit(Long id, ModelMap modelMap) {
        modelMap.put("info", service.getById(id));
        return "system/user/edit";
    }

    @GetMapping("/profile/resetPwd")
    public String test(ModelMap mmap) {
        LoginModel loginModel = userLoginService.getLogin();
        mmap.put("user", loginModel);
        return "system/user/resetPwd" ;
    }

    @PersistentLog
    @ApiOperation("登录")
    @PostMapping("/doLogin")
    @ResponseBody
    public RespVo doLogin(SysUserReq req) {
        return RespVo.success(service.doLogin(req));
    }


    @MarsPermission(":insert")
    @ApiOperation("新增")
    @PostMapping("/insert")
    @ResponseBody
    public RespVo add(@RequestBody SysUserEntity entity){
        service.insert(entity);
        return RespVo.success();
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response);
        return "login" ;
    }


    @PersistentLog
    @RequestMapping("/resetPwd")
    @ResponseBody
    @MarsPermission("sys:user:resetPwd")
    public RespVo resetPwd(String oldPassword, String newPassword) {
        service.resetPwd(oldPassword, newPassword);
        return RespVo.success();
    }

    @MarsPermission(":updateById")
    @ApiOperation("修改")
    @PostMapping("/updateById")
    @ResponseBody
    public RespVo updateById(@RequestBody SysUserEntity entity){
        service.edit(entity);
        return RespVo.success();
    }


    @MarsPermission(":deleteById")
    @ApiOperation("根据id删除")
    @PostMapping("/deleteById")
    @ResponseBody
    public RespVo deleteById(Long id){
        service.removeById(id);
        return RespVo.success();
    }

}