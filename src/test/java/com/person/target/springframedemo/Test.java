package com.person.target.springframedemo;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        String str = new String();
        int[] a = new int[2];
        long[] l = new long[1];
        char[] c = new char[2];
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }


}
