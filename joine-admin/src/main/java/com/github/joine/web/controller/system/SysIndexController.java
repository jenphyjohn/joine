package com.github.joine.web.controller.system;

import com.github.joine.common.config.Global;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.framework.util.ShiroUtils;
import com.github.joine.system.domain.SysMenu;
import com.github.joine.system.domain.SysUser;
import com.github.joine.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 *
 * @author JenphyJohn
 */
@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        modelMap.put("menus", menus);
        modelMap.put("user", user);
        modelMap.put("copyrightYear", Global.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap modelMap) {
        modelMap.put("version", Global.getVersion());
        return "main";
    }
}
