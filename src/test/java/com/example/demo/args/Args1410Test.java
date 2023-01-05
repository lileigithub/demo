package com.example.demo.args;

import java.text.ParseException;

/**
 * description
 * @author lilei
 * @date 2022/4/12 16:32
 */
public class Args1410Test {
    public static void main(String[] args) throws Exception {
        Args1410 args1410 = null;
        String[] args2 = new String[]{"-b", "-s", "A", "-S", "B", "c"};
        try {
            args1410 = new Args1410("b,s*,S*", args2);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("usage:%s", args1410.usage());
        System.out.println();
        System.out.printf("boolean:%b", args1410.getBoolean('b'));
        System.out.println();
        System.out.printf("String:%s", args1410.getString('s'));
        System.out.println();
        System.out.printf("String:%s", args1410.getString('S'));
        System.out.println();
        System.out.printf("isValid:%s", args1410.isValid());
        System.out.println();
        System.out.printf("errorMessage:%s", args1410.errorMessage());
    }
}
