package com.github.fashionbrot.console.controller;



import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.SysOrgReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysOrgEntity;
import com.github.fashionbrot.core.service.SysOrgService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 机构表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */

@MarsPermission(value="sys:org")
@Controller
@RequestMapping("sys/org")
@Api(tags="机构表")
@ApiSort(23292972)
public class SysOrgController extends BaseController<SysOrgService, SysOrgEntity>  {

    @Autowired
    private SysOrgService sysOrgService;


    /**
     * 权限 注解 MarsPermission
     * 默认接口以下
     * 分页       sys/org/page        权限：sys:org:page
     * 数据列表    sys/org/queryList   权限：sys:org:queryList
     * 根据id查询  sys/org/selectById  权限：sys:org:selectById
     * 新增       sys/org/insert      权限：sys:org:insert
     * 修改       sys/org/updateById  权限：sys:org:updateById
     * 根据id删除  sys/org/deleteById  权限：sys:org:deleteById
     * 多个id删除  sys/org/deleteByIds 权限：sys:org:deleteByIds
     */

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @GetMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysOrgReq req) {
        return RespVo.success(service.pageReq(req));
    }



}