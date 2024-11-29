package Trees.AVL;

import Process.Word;
import Trees.BST.Order;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean update(AVLNode root, Word data) {
        return update(this.root, data);
    }

    public boolean find(AVLNode root, Word data) {
        return find(this.root, data);
    }

    // find return the node
    public AVLNode getNode(AVLNode root, String word) {
        if (root == null) {
            return null;
        }
        if (root.getData().getWord().compareTo(word) == 0) {
            return root;
        }
        if (root.getData().getWord().compareTo(word) > 0) {
            return getNode(root.getLeft(), word);
        }
        return getNode(root.getRight(), word);
    }

    public AVLNode getRoot() {
        return root;
    }

    public void insert(Word data) {
        root = insert(data, root);
    }

    public AVLNode insert(Word newNode, AVLNode r) { //r is root on first call
        if (r == null)
            r = new AVLNode (newNode);
        else if (newNode.getWord().compareTo(r.getData().getWord()) < 0) {
            r.left = insert(newNode, r.left);
            if (height(r.right) - height(r.left) == -2 ) // need to rebalance
                if(newNode.getWord().compareTo(r.left.getData().getWord()) < 0)
                    r = leftRotate(r);
                else
                    r = lrRotate(r);
        } else if(newNode.getWord().compareTo(r.getData().getWord()) > 0) {
            r.right = insert(newNode, r.right );
            if( height( r.right ) - height( r.left ) == 2 ) // need to rebalance
                if(newNode.getWord().compareTo(r.right.getData().getWord()) > 0)
                    r = rightRotate(r);
                else
                    r = rlRotate(r);
        }
        r.height = Math.max( height( r.left ), height( r.right ) ) + 1;
        return r;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode rightRotate(AVLNode A) {
        AVLNode B = A.getRight();
        A.setRight(B.getLeft());
        B.setLeft(A);
        A.setHeight(1 + Math.max(height(A.getLeft()), height(A.getRight())));
        B.setHeight(1 + Math.max(height(B.getRight()), height(A)));
        return B;
    }

    private AVLNode leftRotate(AVLNode A) {
        AVLNode B = A.getLeft();
        A.setLeft(B.getRight());
        B.setRight(A);
        A.setHeight(1 + Math.max(height(A.getLeft()), height(A.getRight())));
        B.setHeight(1 + Math.max(height(B.getLeft()), height(B)));
        return B;
    }

    private AVLNode lrRotate(AVLNode A) {
        A.setLeft(rightRotate(A.getLeft()));
        return leftRotate(A);
    }

    private AVLNode rlRotate(AVLNode A) {
        A.setRight(leftRotate(A.getRight()));
        return rightRotate(A);
    }

    private int height(AVLNode node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
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

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getData());
            inOrder(node.getRight());
        }
    }

    private void preOrder(AVLNode node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    private void postOrder(AVLNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getData());
        }
    }

    public Word retrieve() {
        return root.getData();
    }

    // Returns the word object if found, otherwise returns null - O(log n)
    public Word search(String word) {
        return search(root, word);
    }

    public boolean find(String word) {
        return find(root, word);
    }

    private boolean find(AVLNode node, String word) {
        if (node == null) {
            return false;
        }
        if (node.getData().getWord().compareTo(word) == 0) {
            return true;
        }
        if (node.getData().getWord().compareTo(word) > 0) {
            return find(node.left, word);
        }
        return find(node.getRight(), word);
    }


    private Word search(AVLNode node, String word) {
        if (node == null) {
            return null;
        }
        if (node.getData().getWord().compareTo(word) == 0) {
            return node.getData();
        }
        if (node.getData().getWord().compareTo(word) > 0) {
            return search(node.getLeft(), word);
        }
        return search(node.getRight(), word);
    }

    public int size() {
        return size(root);
    }

    private int size(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}
