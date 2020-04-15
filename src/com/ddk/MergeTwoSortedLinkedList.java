package com.ddk;

import java.util.Scanner;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class MergeTwoSortedLinkedList {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode listNode1;
    ListNode listNode2;

    public MergeTwoSortedLinkedList() {
        this.listNode1 = null;
        this.listNode2 = null;
    }

    public MergeTwoSortedLinkedList(int[] list1, int[] list2) {
        this.listNode1 = generateList(list1);
        this.listNode2 = generateList(list2);
    }

    public ListNode getListNode1() {
        return this.listNode1;
    }

    public ListNode getListNode2() {
        return this.listNode2;
    }

    public ListNode generateList(int[] array) {
        ListNode temp = null;
        ListNode reference = null;
        for ( int a : array ) {
            if (temp == null) {
                temp = new ListNode(a);
                reference = temp;
            } else {
                temp.next = new ListNode(a);
                temp = temp.next;
            }
        }
        return reference;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode head = null;
        while ( l1 != null && l2 != null ) {
            ListNode temp;
            if ( l1.val < l2.val ) {
                System.out.println("Adding l1.val: " + l1.val + " to the result");
                temp = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                System.out.println("Adding l2.val: " + l2.val + " to the result");
                temp = new ListNode(l2.val);
                l2 = l2.next;
            }
            temp.next = null;
            if ( result == null ) {
                result = temp;
                result.next = null;
                head = result;
            } else {
                result.next = temp;
                result = result.next;
            }
        }
        while ( l1 != null ) {
            System.out.println("Adding reminder of l1 - l1.val: " + l1.val + " to the result");
            if ( result == null ) {
                result = new ListNode(l1.val);
                result.next = null;
                head = result;
            } else {
                result.next = new ListNode(l1.val);
                result = result.next;
            }
            l1 = l1.next;
        }
        while ( l2 != null ) {
            System.out.println("Adding reminder of l2 - l2.val: " + l2.val + " to the result");
            if ( result == null ) {
                result = new ListNode(l2.val);
                result.next = null;
                head = result;
            } else {
                result.next = new ListNode(l2.val);
                result = result.next;
            }
            l2 = l2.next;
        }
        result = head;
        while ( result != null ) {
            System.out.println("result.val: " + result.val);
            result = result.next;
        }
        return head;
    }

    public void printListNode(ListNode listNode) {
        while ( listNode != null ) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MergeTwoSortedLinkedList addTwoLinkedList = new MergeTwoSortedLinkedList(
                new int[]{1, 2, 4},
                new int[]{1, 3, 4}
                );

        // Add LinkedLists
        ListNode result = addTwoLinkedList.mergeTwoLists(addTwoLinkedList.getListNode1(), addTwoLinkedList.getListNode2());
        addTwoLinkedList.printListNode(result);
    }
}
