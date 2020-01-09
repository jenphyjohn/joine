package com.github.joine.web.controller.system;

import com.github.joine.common.annotation.Log;
import com.github.joine.common.constant.UserConstants;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.core.page.TableDataInfo;
import com.github.joine.common.enums.BusinessType;
import com.github.joine.common.utils.StringUtils;
import com.github.joine.common.utils.poi.ExcelUtil;
import com.github.joine.framework.shiro.service.SysPasswordService;
import com.github.joine.framework.util.ShiroUtils;
import com.github.joine.system.domain.SysUser;
import com.github.joine.system.service.ISysPostService;
import com.github.joine.system.service.ISysRoleService;
import com.github.joine.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户信息
 *
 * @author JenphyJohn
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public ResponseResult export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public ResponseResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return successMsg(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public ResponseResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
        return util.importTemplateExcel("用户导入模板");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("roles", roleService.selectRoleAll());
        modelMap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public ResponseResult addSave(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("保存用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("保存用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("保存用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap modelMap) {
        modelMap.put("user", userService.selectUserById(userId));
        modelMap.put("roles", roleService.selectRolesByUserId(userId));
        modelMap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public ResponseResult editSave(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("保存用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("保存用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap modelMap) {
        modelMap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public ResponseResult resetPwdSave(SysUser user) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(userService.resetUserPwd(user));
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public ResponseResult remove(String ids) {
        try {
            return toAjax(userService.deleteUserByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public ResponseResult changeStatus(SysUser user) {
        return toAjax(userService.changeStatus(user));
    }
}