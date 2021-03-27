package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.SysUserReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysUserEntity;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */

@MarsPermission(value="sys:user")
@Controller
@RequestMapping("sys/user")
@Api(tags="系统用户表")
@ApiSort(23371307)
public class SysUserController extends BaseController<SysUserService, SysUserEntity>  {
    /**
    @Autowired
    private SysUserService sysUserService;
     */

    /**
     * 权限 注解 MarsPermission
     * 默认接口以下
     * 分页       sys/user/page        权限：sys:user:page
     * 数据列表    sys/user/queryList   权限：sys:user:queryList
     * 根据id查询  sys/user/selectById  权限：sys:user:selectById
     * 新增       sys/user/insert      权限：sys:user:insert
     * 修改       sys/user/updateById  权限：sys:user:updateById
     * 根据id删除  sys/user/deleteById  权限：sys:user:deleteById
     * 多个id删除  sys/user/deleteByIds 权限：sys:user:deleteByIds
     */

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @GetMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysUserReq req) {
        return RespVo.success(service.pageReq(req));
    }

}