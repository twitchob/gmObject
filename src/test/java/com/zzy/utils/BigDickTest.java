package com.zzy.utils;

/**
 * @author zzypersonally@gmail.com
 * @description 平时的一些测试
 * @since 2023/11/17 16:55
 */
public class BigDickTest {
    /**
     * 测试 byte[] 是值传递还是引用传递
     */
    public static void main(String[] args) {
        byte[] bytes = "123".getBytes();
        System.out.println("before change: " + new String(bytes));
        change(bytes);
        System.out.println("after change: " + new String(bytes));
    }

    private static void change(byte[] bytes) {
        bytes = "456".getBytes();
        System.out.println("inner" + new String(bytes));
    }
}
