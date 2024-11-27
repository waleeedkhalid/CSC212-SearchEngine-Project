package Trees.BST;

import Process.Word;

public class BSTNode {
    private Word word;
    private BSTNode left, right;

    public BSTNode(Word key) {
        this.word = key;
        left = right = null;
    }

    public Word getWord() {
        return word;
    }

    public BSTNode getLeft() {
        return left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

}
