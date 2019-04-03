package com.github.joine.web.controller.monitor;

import com.github.joine.common.core.controller.BaseController;
import com.github.joine.framework.web.domain.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控
 *
 * @author JenphyJohn
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController extends BaseController {
    private String prefix = "monitor/server";

    @RequiresPermissions("monitor:server:view")
    @GetMapping()
    public String server(ModelMap modelMap) throws Exception {
        Server server = new Server();
        server.copyTo();
        modelMap.put("server", server);
        return prefix + "/server";
    }
}
