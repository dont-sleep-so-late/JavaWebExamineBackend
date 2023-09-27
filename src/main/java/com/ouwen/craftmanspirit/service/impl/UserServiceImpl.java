package com.ouwen.craftmanspirit.service.impl;

import com.ouwen.craftmanspirit.entity.User;
import com.ouwen.craftmanspirit.mapper.UserMapper;
import com.ouwen.craftmanspirit.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ouwen
 * @since 2023-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public boolean reg(User user) {
        //插入新用户数据
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/craftmanspirit?useSSL=false&serverTimezone=UTC", "root", "123qwe");

            String sql = "INSERT INTO o_user (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;
    }



    //    @Autowired
//    private RedisTemplate<String, User> redisTemplate;
    @Override
    public Map<String, Object> login(User user) {
        //根据用户名和密码查询
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/craftmanspirit?useSSL=false&serverTimezone=UTC", "root", "123qwe");
            String sql = "SELECT * FROM o_user WHERE username=? AND password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String key = "user" + UUID.randomUUID();
                String token = key.toString();
                Map<String,Object> data = new HashMap<>();
                data.put("token",token);
                int userId = rs.getInt("id");
                String updateSql = "UPDATE o_user SET token=? WHERE id=?";
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setString(1, token);
                pstmt.setInt(2, userId);
                pstmt.executeUpdate();
                return data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void logout(String token) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Get a database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/craftmanspirit?useSSL=false&serverTimezone=UTC", "root", "123qwe");
            String sql = "UPDATE o_user SET token=NULL WHERE token=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, token);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // 3. Close the database resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
