package com.ouwen.craftmanspirit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ouwen
 * @since 2023-05-13
 */
@TableName("o_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
            "username = " + username +
            ", password = " + password +
            ", id = " + id +
        "}";
    }
}
