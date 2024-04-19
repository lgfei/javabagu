package com.lgfei.javabagu.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _12_IntegerToRoman {
    private static Map<Integer,Character> map = new HashMap<>(7);
    private static List<Integer> keys = null;
    static {
        map.put(1,'I');
        map.put(5,'V');
        map.put(10,'X');
        map.put(50,'L');
        map.put(100,'C');
        map.put(500,'D');
        map.put(1000,'M');

        keys = map.keySet().stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1.compareTo(o2)) * -1;
            }
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 3999; i++){
            String roman = intToRoman(i);
            System.out.println(i + "=" + roman);
        }
    }

    public static String intToRoman(int num) {
        if(num < 1 || num > 3999){
            throw new RuntimeException("num must between 1~3999");
        }
        StringBuilder s = new StringBuilder();
        handle(s, num, 0);

        String result = s.toString();
        result = replaceSpecial(result, "IIII", "IV", "VIIII", "IX");
        result = replaceSpecial(result, "XXXX", "XL", "LXXXX", "XC");
        result = replaceSpecial(result, "CCCC", "CD", "DCCCC", "CM");

        return result;
    }

    private static String replaceSpecial(String src, String first, String firstReplace, String second, String secondReplace){
        String result = null;
        if(src.contains(first)){
            if(src.contains(second)){
                result = src.replace(second, secondReplace);
            }else{
                result = src.replace(first, firstReplace);
            }
        }else{
            result = src;
        }
        return result;
    }

    private static void handle(StringBuilder s, int num, int index){
        if(num == 0){
            return;
        }
        for (int i = index; i < keys.size(); i++) {
            int key = keys.get(i);
            char val = map.get(key);
            int flag = num - key;
            if(flag >= 0){
                s.append(val);
                handle(s, flag, i);
                break;
            }
        }
    }
}
