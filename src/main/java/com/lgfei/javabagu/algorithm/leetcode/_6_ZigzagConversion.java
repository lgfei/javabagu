package com.lgfei.javabagu.algorithm.leetcode;

import java.util.*;

public class _6_ZigzagConversion {
    public static void main(String[] args) {
        _6_ZigzagConversion test = new _6_ZigzagConversion();
        System.out.println(test.convert("PAYPALISHIRING",3));
        System.out.println(test.convert("PAYPALISHIRING",4));
        System.out.println(test.convert("ABCDEFGHIJK",1));
        System.out.println(test.convert("ABCDEFGHIJK",3));
        System.out.println(test.convert("A",1));
        System.out.println(test.convert("AB",2));
    }

    public String convert(String s, int numRows) {
        if(null == s || s.length() <= 2 || numRows == 1){
            return s;
        }
        int len = s.length();
        if(len <= numRows){
            return s;
        }
        char[] arr = s.toCharArray();
        List<Character[]> list = new ArrayList<>();
        int index = 0;
        boolean isNext = false;
        while (index < len){
            Character[] yChars = new Character[numRows];
            int flag = list.size() % (numRows - 1);
            if(flag == 0){
                isNext = true;
            }
            for(int i = 0; i < numRows; i++){
                if(isNext){
                    yChars[i] = arr[index];
                    index++;
                }else{
                    if(i == flag){
                        yChars[numRows - 1 - i] = arr[index];
                        index++;
                        break;
                    }
                }
                if(index == len){
                    break;
                }
            }
            list.add(yChars);
            isNext = false;
        }
        StringBuilder sb = new StringBuilder();
        for (int x =0;x<numRows;x++){
            for (int y = 0;y<list.size();y++){
                Character c = list.get(y)[x];
                if(null != c){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
