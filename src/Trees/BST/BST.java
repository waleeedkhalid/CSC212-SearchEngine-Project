package Trees.BST;

import Process.Word;

public class BST {
    private BSTNode root, current;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public Word retrieve() {
        return current.getWord();
    }

    public void update(Word val) {
        current.setWord(val);
    }

    public boolean full() {
        return false;
    }

    public boolean search(Word word) {
        BSTNode p = root, q = root;
        if(empty())
            return false;
        while(p != null) {
            q = p;
            if(p.getWord().equals(word)) {
                current = p;
                return true;
            }
            else if(word.getWord().compareTo(p.getWord().getWord()) < 0)
                p = p.getLeft();
            else
                p = p.getRight();
        }

        current = q;
        return false;
    }

    // search for a String word in the BST - O(log n)
    public boolean search(String word) {
        BSTNode p = root, q = root;
        if(empty())
            return false;
        while(p != null) {
            q = p;
            if(p.getWord().getWord().equals(word)) {
                current = p;
                return true;
            }
            else if(word.compareTo(p.getWord().getWord()) < 0)
                p = p.getLeft();
            else
                p = p.getRight();
        }

        current = q;
        return false;
    }

    public boolean insert(Word word) {
        BSTNode p, q = current;
        if(search(word)) {
            current = q;
            return false;
        }
        p = new BSTNode(word);
        if(empty()) {
            root = current = p;
        } else {
            if(word.compareTo(current.getWord()) < 0)
                current.setLeft(p);
            else
                current.setRight(p);
            current = p;
        }
        return true;
    }

    public void traverse(Order order) {
        switch (order) {
            case INORDER:
                inOrder(root);
                break;
            case PREORDER:
                preOrder(root);
                break;
            case POSTORDER:
                postOrder(root);
                break;
        }
    }

    private void inOrder(BSTNode node) {
        if(node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getWord() + " ");
            inOrder(node.getRight());
        }
    }

    private void preOrder(BSTNode node) {
        if(node != null) {
            System.out.println(node.getWord() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    private void postOrder(BSTNode node) {
        if(node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getWord() + " ");
        }
    }

    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if(node == null) {
            return 0;
        } else {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }
}
