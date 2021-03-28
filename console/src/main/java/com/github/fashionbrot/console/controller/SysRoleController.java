package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.annotation.PersistentLog;
import com.github.fashionbrot.common.req.SysRoleReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysRoleEntity;
import com.github.fashionbrot.core.service.SysRoleService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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


    @MarsPermission("sys:role:index")
    @GetMapping("/index")
    public String index(){
        return "system/role/role";
    }

    @GetMapping("/index/add")
    public String indexAdd(){
        return "system/role/add";
    }

    @GetMapping("/index/edit")
    public String indexEdit(){
        return "system/role/edit";
    }

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @PostMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysRoleReq req) {
        return RespVo.success(service.pageReq(req));
    }


    @PersistentLog
    @MarsPermission(":insert")
    @ApiOperation("新增")
    @PostMapping("/insert")
    @ResponseBody
    public RespVo add(@RequestBody SysRoleEntity entity){
        service.add(entity);
        return RespVo.success();
    }


    @PersistentLog
    @MarsPermission(":updateById")
    @ApiOperation("修改")
    @PostMapping("/updateById")
    @ResponseBody
    public RespVo updateById(@RequestBody SysRoleEntity entity){
        service.edit(entity);
        return RespVo.success();
    }

    @PersistentLog
    @MarsPermission(":deleteById")
    @ApiOperation("根据id删除")
    @PostMapping("/deleteById")
    @ResponseBody
    public RespVo deleteById(Long id){
        service.deleteById(id);
        return RespVo.success();
    }

}