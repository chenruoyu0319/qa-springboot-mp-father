package com.cry.token.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-20 20:56
 * @Modified By:
 */
public class TimestampUtil {

    public static Timestamp getTimestampNow(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

}
