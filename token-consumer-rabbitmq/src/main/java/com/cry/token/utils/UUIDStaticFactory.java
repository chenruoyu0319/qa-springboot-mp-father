package com.cry.token.utils;

import java.util.UUID;

/**
 * @Author: Chen ruoyu
 * @Description: 创建Uuid(Guid)
 * @Date Created in:  2021-01-20 19:36
 * @Modified By:
 */
public class UUIDStaticFactory {

    /**
     * 得到1个32位的uuid(8+4+4+16)
     *
     * @return
     */
    public static String getUUID32() {
        // return UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return UUID.randomUUID().toString();
    }

    /**
     * 得到一组指定数量的UUID, 以数组的形式返回
     *
     * @param num
     * @return
     */
    public static String[] getUUIDs(int num) {

        if (num <= 0) {
            return null;
        }
        String[] uuidArr = new String[num];
        for (int i = 0; i < uuidArr.length; i++) {
            uuidArr[i] = getUUID32();
        }
        return uuidArr;
    }

}
