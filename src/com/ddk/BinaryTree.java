package com.ddk;

import java.util.concurrent.atomic.AtomicInteger;

// Java program to demonstrate insert operation in binary search tree
class BinaryTree {

    /* Class containing left and right child of current node and key value*/
    class Node {
        int key;
        int count;
        int index;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;
    AtomicInteger index = new AtomicInteger(0);

    // Constructor
    BinaryTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) {
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            root.count++;
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key) {
            root.count++;
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.count++;
            root.right = insertRec(root.right, key);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    void inorder()  {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            root.index = index.incrementAndGet();
            System.out.println(root.key + " => " + root.count + " index: " + root.index);
            inorderRec(root.right);
        }
    }

    void printTree() {
        printTreeNodes(this.root, "Root");
    }

    void printTreeNodes(Node node, String prefix) {
        if ( node != null ) {
            System.out.println(prefix + " " + node.key + " (" + node.count + ")");
            if ( node.left != null )
                printTreeNodes(node.left, "Left");
            if ( node.right != null )
                printTreeNodes(node.right, "Right");
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(40);
        tree.insert(20);
        tree.insert(30);
        tree.insert(10);
        tree.insert(60);
        tree.insert(50);
        tree.insert(70);

/*
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);
*/

/*
        tree.insert(70);
        tree.insert(60);
        tree.insert(50);
        tree.insert(40);
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
*/
        tree.inorder();
        // print inorder traversal of the BST
        tree.printTree();
    }
}
