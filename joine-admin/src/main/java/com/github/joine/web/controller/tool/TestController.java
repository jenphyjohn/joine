package com.github.joine.web.controller.tool;

import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.utils.StringUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * swagger 用户测试方法
 *
 * @author JenphyJohn
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController extends BaseController {
    private final static Map<Integer, UserEntity> USERS = new LinkedHashMap<Integer, UserEntity>();

    {
        USERS.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        USERS.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ResponseResult userList() {
        List<UserEntity> userList = new ArrayList<UserEntity>(USERS.values());
        return ResponseResult.success(userList);
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{userId}")
    public ResponseResult getUser(@PathVariable Integer userId) {
        if (!USERS.isEmpty() && USERS.containsKey(userId)) {
            return ResponseResult.success(USERS.get(userId));
        } else {
            return error("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "userEntity", value = "新增用户信息", dataType = "UserEntity")
    @PostMapping("/save")
    public ResponseResult save(UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        return ResponseResult.success(USERS.put(user.getUserId(), user));
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "userEntity", value = "新增用户信息", dataType = "UserEntity")
    @PutMapping("/update")
    public ResponseResult update(UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        if (USERS.isEmpty() || !USERS.containsKey(user.getUserId())) {
            return error("用户不存在");
        }
        USERS.remove(user.getUserId());
        return ResponseResult.success(USERS.put(user.getUserId(), user));
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{userId}")
    public ResponseResult delete(@PathVariable Integer userId) {
        if (!USERS.isEmpty() && USERS.containsKey(userId)) {
            USERS.remove(userId);
            return success();
        } else {
            return error("用户不存在");
        }
    }
}

@ApiModel("用户实体")
class UserEntity {
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户手机")
    private String mobile;

    public UserEntity() {

    }

    public UserEntity(Integer userId, String username, String password, String mobile) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
