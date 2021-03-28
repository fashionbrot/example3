package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.SysLogReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysLogEntity;
import com.github.fashionbrot.core.service.SysLogService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统日志
 *
 * @author fahsionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */

@MarsPermission(value="sys:log")
@Controller
@RequestMapping("sys/log")
@Api(tags="系统日志")
@ApiSort(23102637)
public class SysLogController extends BaseController<SysLogService, SysLogEntity>  {
    /**
    @Autowired
    private SysLogService sysLogService;
     */

    /**
     * 权限 注解 MarsPermission
     * 默认接口以下
     * 分页       sys/log/page        权限：sys:log:page
     * 数据列表    sys/log/queryList   权限：sys:log:queryList
     * 根据id查询  sys/log/selectById  权限：sys:log:selectById
     * 新增       sys/log/insert      权限：sys:log:insert
     * 修改       sys/log/updateById  权限：sys:log:updateById
     * 根据id删除  sys/log/deleteById  权限：sys:log:deleteById
     * 多个id删除  sys/log/deleteByIds 权限：sys:log:deleteByIds
     */

    @GetMapping("index")
    public String index(){
        return "/system/log/log";
    }

    @MarsPermission(":page")
    @ApiOperation("数据列表—分页")
    @PostMapping("/page")
    @ResponseBody
    public RespVo pageReq(SysLogReq req) {
        return RespVo.success(service.pageReq(req));
    }



    @MarsPermission("sys:log:index:detail")
    @GetMapping("/index/detail")
    public String detail( Long id, ModelMap modelMap){
        modelMap.put("operLog",service.getById(id));
        return "system/log/detail";
    }

}