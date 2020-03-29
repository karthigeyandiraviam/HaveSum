package com.ddk.common;

public class LLNode<T> {
    T data;
    LLNode next;

    public LLNode(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
