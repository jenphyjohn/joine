package com.github.joine.quartz.controller;

import com.github.joine.common.annotation.Log;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.AjaxResult;
import com.github.joine.common.core.page.TableDataInfo;
import com.github.joine.common.enums.BusinessType;
import com.github.joine.common.exception.job.TaskException;
import com.github.joine.common.utils.poi.ExcelUtil;
import com.github.joine.quartz.domain.SysJob;
import com.github.joine.quartz.service.ISysJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 *
 * @author JenphyJohn
 */
@Controller
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String job() {
        return prefix + "/job";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJob job) {
        startPage();
        List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJob job) {
        List<SysJob> list = jobService.selectJobList(job);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
    }

    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws SchedulerException {
        jobService.deleteJobByIds(ids);
        return success();
    }

    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap modelMap) {
        modelMap.put("name", "job");
        modelMap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysJob job) throws SchedulerException {
        SysJob sysJob = jobService.selectJobById(job.getJobId());
        sysJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(sysJob));
    }

    /**
     * 任务调度立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(SysJob job) throws SchedulerException {
        jobService.run(job);
        return success();
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @RequiresPermissions("monitor:job:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysJob job) throws SchedulerException, TaskException {
        return toAjax(jobService.insertJobCron(job));
    }

    /**
     * 修改调度
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap modelMap) {
        modelMap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     *
     * @throws Exception
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysJob job) throws Exception {
        return toAjax(jobService.updateJobCron(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
