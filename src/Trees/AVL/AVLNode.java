package Trees.AVL;

import Process.Word;

public class AVLNode {
    Word data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(Word data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public Word getData() {
        return data;
    }

    public void setData(Word data) {
        this.data = data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}