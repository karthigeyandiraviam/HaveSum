package com.ddk.common;

public interface MaxStack<T extends Comparable<T>> {
    // The standard three Stack methods:

    /** Add an element to the stack. */
    public void push(T toPush);

    /** Return the top value on the stack. */
    public T peek();

    /** Remove and return the top value from the stack. */
    public T pop();

    // Two special methods, so this isn't just 'implement a stack
    // Return the largest value in the stack.
    public T peekMax();

    /** Remove and return the largest value from the stack. */
    public T popMax();
}
