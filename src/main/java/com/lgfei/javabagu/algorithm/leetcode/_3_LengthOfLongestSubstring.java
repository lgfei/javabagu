package com.lgfei.javabagu.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class _3_LengthOfLongestSubstring {
    public static void main(String[] args) {
        _3_LengthOfLongestSubstring test = new _3_LengthOfLongestSubstring();
        test.lengthOfLongestSubstring(" ");
        test.lengthOfLongestSubstring("abcabcbb");
        test.lengthOfLongestSubstring("bbbbb");
        test.lengthOfLongestSubstring("pwwkew");
        test.lengthOfLongestSubstring("adfkjaskdfhgweurfgsmvnauwsefh");
    }
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0){
            return 0;
        }
        if(len == 1){
            return 1;
        }
        char[] arr = s.toCharArray();
        Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < len - 1; i++) {
            StringBuilder key = new StringBuilder();
            key.append(arr[i]);
            Map<Character, Integer> temp = new HashMap<>();
            temp.put(arr[i], 1);
            for (int j = i + 1; j < len; j++) {
                if(temp.containsKey(arr[j])){
                    break;
                }else{
                    temp.put(arr[j], 1);
                    key.append(arr[j]);
                }
            }
            map.put(key.length(), key.toString());
        }
        int result = 0;
        for (Map.Entry<Integer,String> entry:map.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
            result = entry.getKey();
            break;
        }
        return result;
    }
}
