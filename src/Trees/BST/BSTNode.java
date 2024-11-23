package Trees.BST;

import Process.Word;

public class BSTNode<T> {
    private Word word;
    private T data;
    private BSTNode<T> left, right;

    public BSTNode(Word key, T data) {
        this.word = key;
        this.data = data;
        left = right = null;
    }

    public Word getWord() {
        return word;
    }

    public T getData() {
        return data;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

}
