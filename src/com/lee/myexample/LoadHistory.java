package com.lee.myexample;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class LoadHistory {
    private static int len = 10;
    private static String[] history = new String[len];
    public static void setHistory(String input) throws UnsupportedEncodingException {
        String string = URLDecoder.decode(input, "UTF-8");
        push(history, string);
    }

    public static void loadHistory() {
        System.out.println("\n<< 검색 기록 >>\n+--------------------+");
        for(int i=0;i<len;i++) {
            if(history[i] == null) break;
            System.out.println((i + 1) + ". " + history[i]);
        }
        System.out.println("+--------------------+");
    }

    private static void push(String[] history, String input) {
        String tmp = history[len - 1];
        for(int i=len-1;i>=1;i--) {
            history[i] = history[i - 1];
        }
        history[0] = input;
    }
}
