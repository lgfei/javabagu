package com.lgfei.javabagu.algorithm.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class _4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        _4_MedianOfTwoSortedArrays test = new _4_MedianOfTwoSortedArrays();
        test.findMedianSortedArrays(new int[]{}, new int[]{2});
        test.findMedianSortedArrays(new int[]{1,3}, new int[]{2});
        test.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        test.findMedianSortedArrays(new int[]{1,2,3}, new int[]{2,3,4});
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Map<Integer,Integer> map = new TreeMap<>();
        toMap(map, nums1);
        toMap(map, nums2);
        double[] merge = new double[m+n];
        int index = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            Integer key = entry.getKey();
            Integer val = entry.getValue();
            for (int i = 0; i < val; i++) {
                merge[index + i] = (double)key;
            }
            index += val;
        }
        for (int i = 0; i < merge.length; i++) {
            System.out.print(merge[i] + ",");
        }
        System.out.println();
        double media = 0;
        if((m+n) % 2 == 0){
            media = (merge[(m+n)/2 -1] + merge[(m+n)/2])/2;
        }else{
            media = merge[(m+n)/2];
        }
        System.out.println("media=" + media);
        return media;
    }

    private void toMap(Map<Integer,Integer> map, int[] arr){
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            Integer key = arr[i];
            if(map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else{
                map.put(key, 1);
            }
        }
    }
}
