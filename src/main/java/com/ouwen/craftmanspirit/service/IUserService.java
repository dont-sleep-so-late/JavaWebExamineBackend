package com.ouwen.craftmanspirit.service;

import com.ouwen.craftmanspirit.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ouwen
 * @since 2023-05-13
 */
public interface IUserService extends IService<User> {
    boolean reg(User user);

    Map<String, Object> login(User user);

    void logout(String token);

}
