package com.zyc.springframework.test.bean;

import com.zyc.springframework.beans.factory.annotation.Autowired;
import com.zyc.springframework.beans.factory.annotation.Value;
import com.zyc.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author zyc
 * @version 1.0
 */
@Component("userService")
public class UserService implements IUserService {
    @Value("${token}")
    String token;


    @Autowired
    UserDao userDao;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + ", token = " + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
