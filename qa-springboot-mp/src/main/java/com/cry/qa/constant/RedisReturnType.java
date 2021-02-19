package com.cry.qa.constant;

/**
 * redis返回类型自定义
 * @author ASUS
 */
public enum RedisReturnType {

    /**
     * 返回值类型 = map
     */
    MAP("map"),

    /**
     * 返回值类型 = mapList
     */
    MAPLIST("mapList"),

    /**
     * 返回值类型 = string
     */
    STRING("string"),

    /**
     * 返回值类型 = list
     */
    LIST("list");

    /**
     * redis返回值数据类型
     */
    private String type;

    RedisReturnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
