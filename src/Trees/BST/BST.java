package Trees.BST;

import Process.Word;

public class BST<T> {
    private BSTNode<T> root, current;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public T retrieve() {
        return current.getData();
    }

    public void update(T val) {
        current.setData(val);
    }

    public boolean full() {
        return false;
    }

    public boolean search(Word word) {
        BSTNode<T> p = root, q = root;
        if(empty())
            return false;
        while(p != null) {
            q = p;
            if(p.getWord().equals(word)) {
                current = p;
                return true;
            }
            else if(word.getWord().compareTo(((Word)p.getData()).getWord()) < 0)
                p = p.getLeft();
            else
                p = p.getRight();
        }

        current = q;
        return false;
    }

    public boolean insert(Word word, T data) {
        BSTNode<T> p, q = current;
        if(search(word)) {
            current = q;
            return false;
        }
        p = new BSTNode<T>(word, data);
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

    private void inOrder(BSTNode<T> node) {
        if(node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getWord() + " ");
            inOrder(node.getRight());
        }
    }

    private void preOrder(BSTNode<T> node) {
        if(node != null) {
            System.out.println(node.getWord() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    private void postOrder(BSTNode<T> node) {
        if(node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getWord() + " ");
        }
    }
}
