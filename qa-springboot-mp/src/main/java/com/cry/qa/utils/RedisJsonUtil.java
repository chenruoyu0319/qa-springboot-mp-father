package com.cry.qa.utils;

import com.alibaba.fastjson.JSON;
import com.cry.qa.response.UserViewModel;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-27 5:26
 * @Modified By:
 */
public class RedisJsonUtil {

    /**
     * RedisJson => Object
     *
     * @param json
     * @return
     */
    public static UserViewModel jsonToUserViewModel(String json) {

        Object parse = JSON.parse(json);
        String s = parse.toString();
        return JSON.parseObject(s, UserViewModel.class);
    }
}
