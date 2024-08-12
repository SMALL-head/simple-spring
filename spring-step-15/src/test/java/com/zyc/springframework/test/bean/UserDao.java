package com.zyc.springframework.test.bean;

import com.zyc.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
@Component
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();
    static {
        hashMap.put("10001", "小明");
        hashMap.put("10002", "小红");
    }


    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
