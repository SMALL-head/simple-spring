package com.zyc.springframework.test.bean;

/**
 * @author zyc
 * @version 1.0
 */
public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
        this.name = "";
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
