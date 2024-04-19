package com.lgfei.javabagu.algorithm.leetcode;

import java.math.BigInteger;

public class _2_AddTwoNumbers {
    public static void main(String[] args) {
        _2_AddTwoNumbers test = new _2_AddTwoNumbers();
        test.excute(new int[]{2,4,3}, new int[]{5,6,4});
        test.excute(new int[]{0}, new int[]{0});
        test.excute(new int[]{9,9,9,9,9,9,9}, new int[]{9,9,9,9});
        test.excute(new int[]{1}, new int[]{1,0,9});
        test.excute(new int[]{9,0,1}, new int[]{1,0,9});
    }

    private void excute(int[] arr1, int[] arr2){
        ListNode l1 = new ListNode();
        for (int i = 0; i < arr1.length; i++) {
            l1.append(arr1[i]);
        }
        l1.print();

        ListNode l2 = new ListNode();
        for (int i = 0; i < arr2.length; i++) {
            l2.append(arr2[i]);
        }
        l2.print();

        ListNode example = addTwoNumbers(l1, l2);
        example.print();
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode();
        BigInteger sum = l1.toNumber().add(l2.toNumber());
        if(sum.compareTo(BigInteger.ZERO) == 0){
            l.push(BigInteger.ZERO.intValue());
        }else{
            l.toNode(sum);
        }
        return l;
    }

    private class Node {
        Integer data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private class ListNode {
        Node head;

        public ListNode() {
            this.head = null;
        }

        public void append(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }

            Node last = head;
            while (last.next != null) {
                last = last.next;
            }

            last.next = newNode;
        }

        public void push(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        public void print() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + "->");
                current = current.next;
            }
            System.out.println("sum=" + this.toNumber());
        }

        public BigInteger toNumber(){
            BigInteger position = BigInteger.ONE;
            BigInteger sum = BigInteger.ZERO;
            Node current = head;
            while (current != null) {
                sum = sum.add(new BigInteger(current.data.toString()).multiply(position));
                current = current.next;
                position = position.multiply(BigInteger.TEN);
            }
            return sum;
        }

        public ListNode toNode(BigInteger sum){
            BigInteger position = BigInteger.ONE;
            for(int i = 1; i <= 100; i++){
                BigInteger flag = sum.divide(position);
                if(flag.compareTo(BigInteger.ZERO) == 0){
                    break;
                } else if(flag.compareTo(BigInteger.ZERO) > 0 && flag.compareTo(BigInteger.TEN) < 0){
                    this.push(flag.intValue());
                    sum = sum.subtract(flag.multiply(position));
                    BigInteger temp = BigInteger.TEN;
                    while (true){
                        if(sum.compareTo(position.divide(temp)) < 0){
                            this.push(BigInteger.ZERO.intValue());
                        }else{
                            break;
                        }
                        temp = temp.multiply(BigInteger.TEN);
                    }
                    toNode(sum);
                }
                position = position.multiply(BigInteger.TEN);
            }
            return this;
        }
    }
}

