package com.zyc.springframework.test.bean;

/**
 * @author zyc
 * @version 1.0
 */
public class UserService {

    private String uId;
    private UserDao userDao;
    private String location;
    private String company;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
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
        return this.toString();
    }

    @Override
    public String toString() {
        return "UserService{" +
                "uId='" + uId + '\'' +
                ", userDao=" + userDao.queryUserName(uId) +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
