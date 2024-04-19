package com.lgfei.javabagu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _5_LongestPalindrome {
    public static void main(String[] args) {
        _5_LongestPalindrome test = new _5_LongestPalindrome();
        List<String> testData = new ArrayList<>();
        testData.add("a");
        testData.add("aa");
        testData.add("ab");
        testData.add("babad");
        testData.add("cbbd");
        testData.add("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        testData.add("abacab");
        testData.forEach(input -> {
            long start = System.currentTimeMillis();
            String output = test.longestPalindrome(input);
            long end = System.currentTimeMillis();
            System.out.println("input=" + input + " -> outpot=" + output + " -> spend:" + (end-start) + "ms");
        });
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }

        }
        return s.substring(maxStart, maxEnd + 1);
    }

    public String longestPalindromeByReverse(String s) {
        int len = s.length();
        if(len == 1){
            return s;
        }
        Map<Integer,String> map = new TreeMap<>(Comparator.reverseOrder());
        char[] arr = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            StringBuilder asc = new StringBuilder();
            asc.append(arr[i]);
            for (int j = i + 1; j < len; j++) {
                asc.append(arr[j]);
                if(arr[i] != arr[j]){
                    continue;
                }
//                System.out.println(asc);
                StringBuilder desc = new StringBuilder(asc).reverse();
//                System.out.println(desc);
                if(asc.toString().equals(desc.toString())){
                    map.put(asc.length(), asc.toString());
                }
            }
        }
//        map.forEach((k,v)->{
//            System.out.println(k + "->" + v);
//        });
//        System.out.println("---");
        if(map.size() == 0){
            return String.valueOf(arr[0]);
        }
        String result = "";
        for (Map.Entry<Integer,String> entry:map.entrySet()) {
            result = entry.getValue();
            break;
        }
        return result;
    }
}
