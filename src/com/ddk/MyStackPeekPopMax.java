package com.ddk;

import com.ddk.common.MaxStack;

import java.util.Stack;
import java.util.TreeMap;

public class MyStackPeekPopMax<T> implements MaxStack {
    Stack<T> elements;
    TreeMap<T, Integer> maxElement;

    public MyStackPeekPopMax() {
        elements = new Stack<>();
        maxElement = new TreeMap<>();
    }

    @Override
    public void push(Comparable toPush) {
        elements.push((T)toPush);
        maxElement.put((T)toPush, (maxElement.containsKey(toPush)) ? (maxElement.get(toPush)+1):1);
    }

    public Comparable peek() {
        return (Comparable)elements.peek();
    }

    public Comparable pop() {
        updateMaxElement(elements.peek());
        return (Comparable) elements.pop();
    }

    @Override
    public Comparable peekMax() {
        return (Comparable) maxElement.lastKey();
    }

    @Override
    public Comparable popMax() {
        // Element
        T element = maxElement.lastKey();
        Stack<T> temp = new Stack();
        while ( element != elements.peek() ) {
            temp.push(elements.pop());
        }
        T ret = (! elements.isEmpty()) ? elements.pop() : null;
        while ( ! temp.isEmpty() )
            elements.push(temp.pop());
        if ( ret != null ) {
            updateMaxElement(element);
        }
        return (Comparable) ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( T e : elements )
            sb.append(e + " ");
        return sb.toString();
    }

    private void removeMaxElement(T element) {
        maxElement.remove(element);
    }
    private void updateMaxElement(T element) {
        maxElement.put(element, ( maxElement.containsKey(element) ) ? (maxElement.get(element)-1) : 1);
        if (maxElement.lastEntry().getValue() <= 0)
            removeMaxElement(element);
    }

    /*
     * peek() -> 2, peekMax() -> 5
     * pop() -> 2; peek() -> 5, peekMax() -> 5
     * pop() -> 5; peek() -> 4, peekMax() -> 5
     * push(6); peek() -> 6, peekMax() -> 6
     * popMax() -> 6; peek -> 4, peekMax() -> 5
     * popMax() -> 5; peek -> 4, peekMax() -> 4
     */
    public static void main(String[] args) {
        MyStackPeekPopMax myStack = new MyStackPeekPopMax();
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
