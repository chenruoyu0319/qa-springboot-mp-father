package com.cry.token.utils;

import java.io.*;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:44
 * @Modified By:
 */
public class SerializeUtil {

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     *
     * @param byteArray
     * @return
     */
    public static Object unSerialize(byte[] byteArray) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
