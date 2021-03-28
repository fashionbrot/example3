package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.SysMenuReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysMenuEntity;
import com.github.fashionbrot.core.service.SysMenuService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 菜单表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */

@MarsPermission(value="sys:menu")
@Controller
@RequestMapping("sys/menu")
@Api(tags="菜单表")
@ApiSort(23371928)
public class SysMenuController extends BaseController<SysMenuService, SysMenuEntity>  {
    /**
    @Autowired
    private SysMenuService sysMenuService;
     */

    /**
     * 权限 注解 MarsPermission
     * 默认接口以下
     * 分页       sys/menu/page        权限：sys:menu:page
     * 数据列表    sys/menu/queryList   权限：sys:menu:queryList
     * 根据id查询  sys/menu/selectById  权限：sys:menu:selectById
     * 新增       sys/menu/insert      权限：sys:menu:insert
     * 修改       sys/menu/updateById  权限：sys:menu:updateById
     * 根据id删除  sys/menu/deleteById  权限：sys:menu:deleteById
     * 多个id删除  sys/menu/deleteByIds 权限：sys:menu:deleteByIds
     */

    @GetMapping("index")
    public String index(){
        return "/system/menu/menu";
    }


    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @PostMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysMenuReq req) {
        return RespVo.success(service.pageReq(req));
    }

    @ApiOperation("数据列表—分页")
    @GetMapping("/queryListAll")
    @ResponseBody
    public List<SysMenuEntity> queryListAll() {
        return service.queryListAll();
    }

    @RequestMapping("loadAllMenu")
    @ResponseBody
    public List<SysMenuEntity> loadAllMenu(Long roleId, Integer isShowCode) {
        return service.loadAllMenu(roleId, isShowCode);
    }

}