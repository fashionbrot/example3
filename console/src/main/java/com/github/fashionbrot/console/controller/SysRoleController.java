package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.SysRoleReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysRoleEntity;
import com.github.fashionbrot.core.service.SysRoleService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */

@MarsPermission(value="sys:role")
@Controller
@RequestMapping("sys/role")
@Api(tags="角色表")
@ApiSort(21500494)
public class SysRoleController extends BaseController<SysRoleService, SysRoleEntity>  {
    /**
     * 权限 注解 MarsPermission
     * 默认接口以下
     * 分页       sys/role/page        权限：sys:role:page
     * 数据列表    sys/role/queryList   权限：sys:role:queryList
     * 根据id查询  sys/role/selectById  权限：sys:role:selectById
     * 新增       sys/role/insert      权限：sys:role:insert
     * 修改       sys/role/updateById  权限：sys:role:updateById
     * 根据id删除  sys/role/deleteById  权限：sys:role:deleteById
     * 多个id删除  sys/role/deleteByIds 权限：sys:role:deleteByIds
     */

    @GetMapping("index")
    public String index(){
        return "/system/role/role";
    }

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @PostMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysRoleReq req) {
        return RespVo.success(service.pageReq(req));
    }



}