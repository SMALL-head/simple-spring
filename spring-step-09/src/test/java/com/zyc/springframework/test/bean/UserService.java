package com.zyc.springframework.test.bean;

import com.zyc.springframework.beans.factory.DisposableBean;
import com.zyc.springframework.beans.factory.InitializingBean;

/**
 * @author zyc
 * @version 1.0
 */
public class UserService {

    private String uId;
    private IUserDao userDao;
    private String location;
    private String company;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String queryUserInfo() {
        return "查询用户信息：" + userDao.queryUserName(uId);
    }
}
