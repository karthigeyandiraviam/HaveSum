package com.ddk.common;


import java.util.TreeMap;

public class LList<T> {
    LLNode head;
    TreeMap<T, Integer> maxElements;

    public void push(T data) {
        // Create a new node with given data
        LLNode new_node = new LLNode(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            this.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            LLNode last = this.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
            if ( maxElements == null )
                maxElements = new TreeMap<>();
            maxElements.put((T)data, (maxElements.containsKey(data)) ? (maxElements.get(data) + 1) : 1);
        }
    }

    // Method to delete a node in the LinkedList by KEY
    public LLNode pop() {
        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            return null;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            LLNode last = this.head, prev = null;
            while (last.next != null) {
                prev = last;
                last = last.next;
            }

            // Insert the new_node at last node
            prev.next = null;
            reduceMaxElement((T) last.data);
            return last;
        }
    }

    public LLNode peek() {
        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            return null;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            LLNode last = this.head;
            while (last.next != null) {
                last = last.next;
            }

            return last;
        }
    }

    public LLNode peekMax() {
        LLNode last = null;
        LLNode curr = head;
        while (curr != null) {
            if ( curr.data == maxElements.lastKey() )
                last = curr;
            curr = curr.next;
        }
        return last;
    }

    public LLNode popMax() {
        LLNode last = null;
        LLNode curr = head, prev = head;
        int count = maxElements.lastEntry().getValue();
        T element = maxElements.lastKey();

        while (curr != null) {
            if ( element == curr.data )
                count--;
            if ( count == 0 ) {
                prev.next = curr.next;
                last = curr;
                reduceMaxElement((T) last.data);
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        return last;
    }

    private void reduceMaxElement(T element) {
        maxElements.put(element, ( maxElements.containsKey(element) ) ? (maxElements.get(element)-1) : 1);
        if (maxElements.lastEntry().getValue() <= 0)
            maxElements.remove(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LLNode currNode = this.head;
        while ( currNode != null ) {
            sb.append(currNode.toString() + " ");
            currNode = currNode.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LList myStack = new LList();
        myStack.push(1);
        myStack.push(3);
        myStack.push(2);
        myStack.push(5);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(2);
        System.out.println(myStack.toString());
        System.out.println(myStack.peek() + ", " + myStack.peekMax());
        System.out.println(myStack.pop() + ", " + myStack.peek() + ", " + myStack.peekMax());
        System.out.println(myStack.pop() + ", " + myStack.peek() + ", " + myStack.peekMax());
        myStack.push(6); System.out.println(myStack.peek() + ", " + myStack.peekMax());
        System.out.println(myStack.popMax() + ", " + myStack.peek() + ", " + myStack.peekMax());
        System.out.println(myStack.popMax() + ", " + myStack.peek() + ", " + myStack.peekMax());
    }
}
