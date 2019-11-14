package com.github.joine.common.config;

import com.github.joine.common.utils.StringUtils;
import com.github.joine.common.utils.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 *
 * @author JenphyJohn
 */
public class Global {
    private static final Logger log = LoggerFactory.getLogger(Global.class);

    private static String NAME = "application.yml";

    /**
     * 当前对象实例
     */
    private static Global global;

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap();

    private Global() {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized Global getInstance() {
        if (global == null) {
            global = new Global();
        }
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            Map<?, ?> yamlMap;
            try {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            } catch (FileNotFoundException e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取项目名称
     */
    public static String getName() {
        return StringUtils.nvl(getConfig("joine.name"), "Joine");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion() {
        return StringUtils.nvl(getConfig("joine.version"), "1.1.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear() {
        return StringUtils.nvl(getConfig("joine.copyrightYear"), "2019");
    }

    /**
     * 获取ip地址开关
     */
    public static Boolean isAddressEnabled() {
        return Boolean.valueOf(getConfig("joine.addressEnabled"));
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile() {
        return getConfig("joine.profile");
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getConfig("joine.profile") + "avatar/";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getConfig("joine.profile") + "download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getConfig("joine.profile") + "/upload";
    }
}
