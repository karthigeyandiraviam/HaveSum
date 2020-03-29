package com.ddk;

class MyListNode {
    int val;
    MyListNode next;

    public MyListNode(int val) {
        this.val = val;
    }
}

public class RemoveNthNodeFromLast {
    MyListNode myListNode;

    public RemoveNthNodeFromLast(int[] array) {
        MyListNode temp = null;
        for ( int a : array ) {
            if (temp == null) {
                temp = new MyListNode(a);
                myListNode = temp;
            } else {
                temp.next = new MyListNode(a);
                temp = temp.next;
            }
        }
    }

    public void printMyListNode(MyListNode myListNode) {
        MyListNode temp = myListNode;
        while ( temp != null ) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public MyListNode getHead() {
        return myListNode;
    }

    public MyListNode removeNthFromEnd(MyListNode head, int n) {
        if ( head == null )
            return head;

        if ( n <= 0 )
            return head;

        // Find number of nodes
        MyListNode currNode = head, prevNode = null;

        int totNode = 0;
        while ( currNode != null ) {
            totNode++;
            currNode = currNode.next;
        }

        int idxFromLast = totNode - n + 1;
        if ( idxFromLast < 0 )
            return head;
        totNode = 0;
        currNode = head;

        while ( totNode < idxFromLast-1 ) {
            prevNode = currNode;
            currNode = currNode.next;
            totNode++;
        }

        if ( totNode == idxFromLast-1 ) {
            if ( currNode == null )
                prevNode.next = null;
            else {
                if ( prevNode == null )
                    head = currNode.next;
                else
                    prevNode.next = currNode.next;
            }
        }
        return head;
    }
    
    public static void main(String[] args) {
        RemoveNthNodeFromLast removeNthNodeFromLast = new RemoveNthNodeFromLast(new int[]{1, 2, 3, 4, 5, 8, 9, 10});
        MyListNode result = removeNthNodeFromLast.removeNthFromEnd(removeNthNodeFromLast.getHead(), 5);
        removeNthNodeFromLast.printMyListNode(result);
    }
}
