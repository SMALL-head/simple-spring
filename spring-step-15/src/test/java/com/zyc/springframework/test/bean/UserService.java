package com.zyc.springframework.test.bean;

import com.zyc.springframework.beans.factory.annotation.Autowired;
import com.zyc.springframework.beans.factory.annotation.Value;
import com.zyc.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author zyc
 * @version 1.0
 */
public class UserService implements IUserService {
    String token;
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
        return "小傅哥，100001，深圳，" + token;
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
