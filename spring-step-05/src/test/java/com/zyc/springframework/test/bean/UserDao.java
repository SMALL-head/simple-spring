package com.zyc.springframework.test.bean;

import cn.hutool.core.lang.hash.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();
    static {
        hashMap.put("10001", "张三");
        hashMap.put("10002", "李四");
        hashMap.put("10003", "王五");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
