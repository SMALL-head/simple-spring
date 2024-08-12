package com.zyc.springframework.test.bean;

/**
 * @author zyc
 * @version 1.0
 */
public class UserService {

    private String uId;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }


}
