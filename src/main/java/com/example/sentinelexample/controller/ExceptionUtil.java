package com.example.sentinelexample.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public final class ExceptionUtil {

    public static String handleException(String str, BlockException ex) {
        System.out.println("Oops: " + ex.getClass().getCanonicalName());
        return "ggggggggg";
    }
}