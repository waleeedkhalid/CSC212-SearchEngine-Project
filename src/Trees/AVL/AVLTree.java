package Trees.AVL;

import Process.Word;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public AVLNode getRoot() {
        return root;
    }

    public void insert(Word data) {
        root = insert(root, data);
    }

    private AVLNode insert(AVLNode node, Word word) {
        if (node == null) {
            return new AVLNode(word);
        }
        if (word.compareTo(word) > 0) {
            node.setLeft(insert(node.getLeft(), word));
        } else {
            node.setRight(insert(node.getRight(), word));
        }
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);
        if (balance > 1 && node.getLeft().getData().compareTo(word) > 0) {
            return rightRotate(node);
        }
        if (balance < -1 && node.getRight().getData().compareTo(word) < 0) {
            return leftRotate(node);
        }
        if (balance > 1 && node.getLeft().getData().compareTo(word) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && node.getRight().getData().compareTo(word) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        return y;
    }

    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }
}
