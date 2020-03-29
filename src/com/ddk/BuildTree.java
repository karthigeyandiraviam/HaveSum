package com.ddk;

import java.util.ArrayList;
import java.util.List;

class BinNode<T> {
    List<BinNode<T>> children = new ArrayList<BinNode<T>>();
    BinNode<T> parent = null;
    T data = null;

    public BinNode(T data) {
        this.data = data;
    }

    public BinNode(T data, BinNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<BinNode<T>> getChildren() {
        return children;
    }

    public void setParent(BinNode<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        BinNode<T> child = new BinNode<T>(data);
        children.add(child);

    }

    public void addChild(BinNode<T> child) {
        child.setParent(this);
        this.children.add(child);
    }
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
}

public class BuildTree {
    public static void main(String[] args) {
        BinNode<String> root = new BinNode<>("Root");

        BinNode<String> child1 = new BinNode<>("Child1");
        child1.addChild("Grandchild1");
        child1.addChild("Grandchild2");

        BinNode<String> child2 = new BinNode<>("Child2");
        child2.addChild("Grandchild3");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild("Child3");

        root.addChild(new BinNode<>("Child4"));
        root.addChild(new BinNode<>("Child5"));
        root.addChild(new BinNode<>("Child6"));

        for(BinNode binNode : root.getChildren()) {
            System.out.println(binNode.getData());
        }
    }
}
