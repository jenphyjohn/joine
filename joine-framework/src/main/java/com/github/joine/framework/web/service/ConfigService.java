package com.github.joine.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.joine.system.service.ISysConfigService;

/**
 * @author JenphyJohn
 */
@Service("config")
public class ConfigService {
    @Autowired
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键值
     * @return 参数值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }
}
