package com.cry.qa.utils;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 21:44
 * @Modified By:
 */
public class ClassNameUtils {

    /**
     * 字符串首部长度
     */
    private static final int HEAD = 24;

    /**
     * 字符串尾部长度
     */
    private static final int TAIL = 11;

    /**
     * 字符串尾部长度
     */
    private static final int PACKAGE = 11;

    //com.cry.qa.service.impl.DonateServiceImpl
    public static String findServiceDomainClassName(String className) {
        return className.substring(HEAD, className.length() - TAIL);

    }

    public static Class reflectClassName(String className) throws ClassNotFoundException {
        String DomainName = className.substring(HEAD, className.length() - TAIL);
        String PackageName = className.substring(0,PACKAGE);
        String RealClassName = PackageName + "domain." + DomainName;
        return Class.forName(RealClassName);

    }
}
