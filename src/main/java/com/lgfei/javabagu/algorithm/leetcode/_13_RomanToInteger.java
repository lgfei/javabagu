package com.lgfei.javabagu.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _13_RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("D"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("MMXXIV"));
        System.out.println(romanToInt("IXX"));
    }

    public static int romanToInt(String s) {
        if(s.length() < 1 || s.length() > 15){
            throw new RuntimeException("s length must between 1~15");
        }
        Map<Character,Integer> map = new HashMap<>(7);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int len = chars.length;
        if(len == 1){
            return map.get(chars[0]);
        }
        int result = 0;
        for (int i = 0; i < len - 1; i++) {
            Character preChar = chars[i];
            Integer preInt = map.get(preChar);
            if(null == preInt){
                throw new RuntimeException("invalid char '" + preChar + "'");
            }
            Character nextChar = chars[i+1];
            Integer nextInt = map.get(nextChar);

            if(nextInt > preInt){
                result -= preInt;
            }else{
                result += preInt;
            }
            if(i == len - 2){
                result += nextInt;
            }
        }
        return result;
    }
}
