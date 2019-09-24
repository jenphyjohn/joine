package com.github.joine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * 启动程序
 *
 * @author JenphyJohn
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ErrorMvcAutoConfiguration.class})
@MapperScan("com.github.joine.*.mapper")
public class JoineRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JoineRestApplication.class, args);
    }
}