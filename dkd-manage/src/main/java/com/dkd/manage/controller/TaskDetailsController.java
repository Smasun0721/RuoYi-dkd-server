package com.dkd.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dkd.common.annotation.Log;
import com.dkd.common.core.controller.BaseController;
import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.enums.BusinessType;
import com.dkd.manage.domain.TaskDetails;
import com.dkd.manage.service.ITaskDetailsService;
import com.dkd.common.utils.poi.ExcelUtil;
import com.dkd.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 工单详情Controller
 *
 * @author Smasun
 * @date 2026-05-15
 */
@Api(tags = "工单详情管理")
@RestController
@RequestMapping("/manage/taskDetails")
public class TaskDetailsController extends BaseController {
    @Autowired
    private ITaskDetailsService taskDetailsService;

    /**
     * 查询工单详情列表
     */
    @ApiOperation("查询工单详情列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "detailsId", value = "详情ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "taskId", value = "工单ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "channelCode", value = "货道编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "skuId", value = "商品ID", dataType = "long", paramType = "query")
    })
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskDetails taskDetails) {
        startPage();
        List<TaskDetails> list = taskDetailsService.selectTaskDetailsList(taskDetails);
        return getDataTable(list);
    }

    /**
     * 导出工单详情列表
     */
    @ApiOperation("导出工单详情列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "详情ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "taskId", value = "工单ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "channelCode", value = "货道编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "skuId", value = "商品ID", dataType = "long", paramType = "query")
    })
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:export')")
    @Log(title = "工单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskDetails taskDetails) {
        List<TaskDetails> list = taskDetailsService.selectTaskDetailsList(taskDetails);
        ExcelUtil<TaskDetails> util = new ExcelUtil<TaskDetails>(TaskDetails.class);
        util.exportExcel(response, list, "工单详情数据");
    }

    /**
     * 获取工单详情详细信息
     */
    @ApiOperation(value = "获取工单详情详细信息", response = AjaxResult.class)
    @ApiImplicitParam(name = "detailsId", value = "工单详情ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:query')")
    @GetMapping(value = "/{detailsId}")
    public AjaxResult getInfo(@PathVariable("detailsId") Long detailsId) {
        return success(taskDetailsService.selectTaskDetailsByDetailsId(detailsId));
    }

    /**
     * 新增工单详情
     */
    @ApiOperation(value = "新增工单详情", response = AjaxResult.class)
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:add')")
    @Log(title = "工单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskDetails taskDetails) {
        return toAjax(taskDetailsService.insertTaskDetails(taskDetails));
    }

    /**
     * 修改工单详情
     */
    @ApiOperation(value = "修改工单详情", response = AjaxResult.class)
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:edit')")
    @Log(title = "工单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskDetails taskDetails) {
        return toAjax(taskDetailsService.updateTaskDetails(taskDetails));
    }

    /**
     * 删除工单详情
     */
    @ApiOperation(value = "删除工单详情", response = AjaxResult.class)
    @ApiImplicitParam(name = "detailsIds", value = "工单详情ID数组", required = true, dataType = "long[]", paramType = "path")
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:remove')")
    @Log(title = "工单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailsIds}")
    public AjaxResult remove(@PathVariable Long[] detailsIds) {
        return toAjax(taskDetailsService.deleteTaskDetailsByDetailsIds(detailsIds));
    }

    /**
     * 查看工单补货详情
     */
    @ApiOperation(value = "查看工单补货详情", response = AjaxResult.class)
    @ApiImplicitParam(name = "taskId", value = "工单ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:list')")
    @GetMapping(value = "/byTaskId/{taskId}")
    public AjaxResult byTaskId(@PathVariable("taskId") Long taskId) {
        TaskDetails taskDetailsParam = new TaskDetails();
        taskDetailsParam.setTaskId(taskId);
        return success(taskDetailsService.selectTaskDetailsList(taskDetailsParam));
    }
}
