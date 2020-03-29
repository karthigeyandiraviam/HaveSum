package com.ddk;

class TreeNode {

    private String value;
    private TreeNode parentNode;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(final TreeNode parent) {
        parentNode = parent;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(final TreeNode left) {
        leftNode = left;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(final TreeNode right) {
        rightNode = right;
    }

}

class BinarySearchTree {

    private TreeNode root;

    public void printTree(final TreeNode tree, String indent) {
        if (tree == null) {
            System.out.print(indent + " |-+*\n");
            return;
        }
        indent += " ";
        if (tree.getParentNode() == null) {
            printLeaf(tree, indent, " ", "+", " ");
            return;
        }
        if (tree.getParentNode().getRightNode() == tree) {
            printLeaf(tree, indent, " ", "|+", "|");
            return;
        }
        printLeaf(tree, indent, "|", "|+", " ");
    }

    private void printLeaf(final TreeNode tree, final String indent, final String indentSuffix1, final String indentSuffix2, final String indentSuffix3) {
        printTree(tree.getRightNode(), indent + indentSuffix1);
        System.out.print(indent + indentSuffix2 + tree.getValue() + "\n");
        printTree(tree.getLeftNode(), indent + indentSuffix3);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(final TreeNode root) {
        this.root = root;
    }

}

public class MirrorTree {
    public static void main(String[] arg) {
        final BinarySearchTree bst = new BinarySearchTree();

        // this is a sample, but a method to add the nodes should be writen
        bst.setRoot(new TreeNode("a"));

        final TreeNode root = bst.getRoot();
        root.setLeftNode(new TreeNode("b"));

        TreeNode leftNode = root.getLeftNode();
        leftNode.setParentNode(root);
        root.setRightNode(new TreeNode("c"));

        TreeNode rightNode = root.getRightNode();
        rightNode.setParentNode(root);

        bst.printTree(root, "\t");
    }
}
