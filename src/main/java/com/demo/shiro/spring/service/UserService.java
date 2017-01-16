package com.demo.shiro.spring.service;

import com.demo.shiro.spring.pojo.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/14.
 */
@Service
public class UserService {

    public User getUserByName(String name) {
        if (name.equals("yao")) {
            // yao & 131
            return new User("yao", "1afa34a7f984eeabdbb0a7d494132ee5");
        }
        return null;
    }
}
