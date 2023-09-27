package com.ouwen.craftmanspirit.controller;

import com.ouwen.craftmanspirit.common.vo.Result;
import com.ouwen.craftmanspirit.entity.User;
import com.ouwen.craftmanspirit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ouwen
 * @since 2023-05-13
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin //跨域处理
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/reg")
    public Result<?> reg(@RequestBody User user){ //转换为JSON
        boolean data = userService.reg(user);
        if(data) {
            return Result.success("注册成功");
        }
        return Result.fail(201,"用户已存在");
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){ //转换为JSON
        Map<String,Object> data = userService.login(user);
        if(data != null) {
            return Result.success(data);
        }
        return Result.fail(201,"用户或密码错误");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("Token") String token){
        userService.logout(token);
        return Result.success();
    }
}
