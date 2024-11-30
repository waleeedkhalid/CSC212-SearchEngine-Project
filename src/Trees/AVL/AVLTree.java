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
        } else
            ; // Duplicate key; do nothing
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

//package Trees.AVL;
//
//import Process.Word;
//import Trees.BST.Order;
//
//public class AVLTree {
//    private AVLNode root, current;
//
//    public AVLTree() {
//        root = current = null;
//    }
//
//    private int height(AVLNode node) {
//        if(node == null) {
//            return -1;
//        } else {
//            return node.getHeight();
//        }
//    }
//
//    private AVLNode rr_Rotation(AVLNode A) {
//        AVLNode B = A.right;
//        A.right = B.left;
//        B.left = A;
//        A.height = Math.max( height(A.left), height(A.right) ) + 1;
//        B.height = Math.max( height(B.right), A.height ) + 1;
//        return B;
//    }
//
//    private AVLNode ll_Rotation(AVLNode A) {
//        AVLNode B = A.left;
//        A.left = B.right;
//        B.right = A;
//        A.height = Math.max( height(A.left), height(A.right) ) + 1;
//        B.height = Math.max( height(B.left), A.height ) + 1;
//        return B;
//    }
//
//    private AVLNode lr_Rotation(AVLNode A) {
//        A.left = rr_Rotation( A.left );
//        return ll_Rotation( A );
//    }
//
//    private AVLNode rl_Rotation(AVLNode A){
//        A.right = ll_Rotation( A.right );
//        return rr_Rotation( A );
//    }
//
//
//
//    public boolean empty() {
//        return root == null;
//    }
//
//    public Word retrieve() {
//        return current.getWord();
//    }
//
//    public void update(Word val) {
//        current.setWord(val);
//    }
//
//    public boolean full() {
//        return false;
//    }
//
//
//    // search for a String word in the BST - O(log n)
//    public boolean search(String word) {
//        AVLNode p = root;
//
//        // Check if the tree is empty
//        if (empty()) {
//            System.out.println("Tree is empty.");
//            return false;
//        }
//
//        // Traverse the tree to search for the word
//        while (p != null) {
//            int comparison = word.compareTo(p.getWord().getWord());  // Compare the word
//            System.out.println("Comparing " + word + " with " + p.getWord().getWord());
//
//            if (comparison == 0) {  // Word found
//                current = p;  // Set the current node to the found node
//                System.out.println("Found: " + word);
//                return true;
//            } else if (comparison < 0) {
//                p = p.getLeft();  // Search in the left subtree
//            } else {
//                p = p.getRight();  // Search in the right subtree
//            }
//        }
//
//        // Word not found
//        current = null;  // Set current to null when not found
//        System.out.println("Word not found: " + word);
//        return false;
//    }
//
//
//
//    public void insert(Word word) {
//        if(search(word.getWord())) {
//            return;
//        }
//        AVLNode newNode = new AVLNode(word);
//        root = insert(newNode, root);
//    }
//
//    // O(log n)
//    public AVLNode insert(AVLNode newNode, AVLNode r) { //r is root on first call
//        if (r == null)
//            r = new AVLNode(newNode.getWord());
//        else if (newNode.getWord().compareTo(r.getWord()) < 0) {
//            r.left = insert(newNode, r.left);
//            if (height(r.right) - height(r.left) == -2 ) // need to rebalance
//                if(newNode.getWord().compareTo(r.getLeft().getWord()) < 0 )
//                    r = ll_Rotation(r);
//                else
//                    r = lr_Rotation(r);
//        } else if(newNode.getWord().compareTo(r.getWord()) > 0) {
//            r.right = insert(newNode, r.right );
//            if( height( r.right ) - height( r.left ) == 2 ) // need to rebalance
//                if(newNode.getWord().compareTo(r.getRight().getWord()) > 0)
//                    r = rr_Rotation(r);
//                else
//                    r = rl_Rotation(r);
//        } else
//            ; // Duplicate key; do nothing
//        r.height = Math.max( height( r.left ), height( r.right ) ) + 1;
//        return r;
//    }
//
//
//    public void traverse(Order order) {
//        switch (order) {
//            case INORDER:
//                inOrder(root);
//                break;
//            case PREORDER:
//                preOrder(root);
//                break;
//            case POSTORDER:
//                postOrder(root);
//                break;
//        }
//    }
//
//    private void inOrder(AVLNode node) {
//        if(node != null) {
//            inOrder(node.getLeft());
//            System.out.println(node.getWord() + " ");
//            inOrder(node.getRight());
//        }
//    }
//
//    private void preOrder(AVLNode node) {
//        if(node != null) {
//            System.out.println(node.getWord() + " ");
//            preOrder(node.getLeft());
//            preOrder(node.getRight());
//        }
//    }
//
//    private void postOrder(AVLNode node) {
//        if(node != null) {
//            postOrder(node.getLeft());
//            postOrder(node.getRight());
//            System.out.println(node.getWord() + " ");
//        }
//    }
//
//    public int size() {
//        return size(root);
//    }
//
//    private int size(AVLNode node) {
//        if(node == null) {
//            return 0;
//        } else {
//            return 1 + size(node.getLeft()) + size(node.getRight());
//        }
//    }
//}
