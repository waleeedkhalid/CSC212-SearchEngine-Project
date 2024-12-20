package List;

import Process.Document;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> current;

    public LinkedList() {
        head = current = null;
    }

    public LinkedList(T val) {
        head = new Node<T>(val);
        current = head;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        current = current.next;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T e) {
        current.data = e;
    }

    public void insert(T e) { // insert after current
        Node<T> tmp;
        if(empty()) {
            head = current = new Node<>(e);
        } else {
            tmp = current.next; // to save the pointer.
            current.next = new Node<>(e);
            current = current.next;
            current.next = tmp;
        }
    }

    public void remove() {
        if(current == head) {
            head = head.next;
        } else {
            Node<T> tmp = head;
            while(tmp.next != current)
                tmp = tmp.next;
            tmp.next = current.next;
        }

        if(current.next == null)
            current = head;
        else
            current = current.next;
    }

    public boolean full() {
        return false; // List.LinkedList won't be full.
    }

    public boolean empty() {
        return head == null; // if there is no head, there is no list.
    }

    public boolean last() {
        return current.next == null; // if there is no next, then we in the last.
    }


    // helper methods

    // this method is used to find a specific value in the list - O(n)
    public boolean find(T val) {
        Node<T> tmp = head;
        while(tmp != null) {
            if(tmp.data.equals(val)) {
                current = tmp;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
    

    // this method is used to find the index of a specific value in the list - O(n)
    public int size() {
        Node<T> tmp = head;
        int count = 0;
        while(tmp != null) {
            count++;
            tmp = tmp.next;
        }
        return count;
    }


    public void sortByRankDescending() {
        if (head == null || head.next == null) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node<T> currentNode = head;

            while (currentNode != null && currentNode.next != null) {
                Document doc1 = (Document) currentNode.data;
                Document doc2 = (Document) currentNode.next.data;


                if (doc1.getRank() < doc2.getRank()) {
                    T temp = currentNode.data;
                    currentNode.data = currentNode.next.data;
                    currentNode.next.data = temp;
                    swapped = true;
                }
                else if (doc1.getRank() == doc2.getRank()) {
                    if (doc1.getDocId() > doc2.getDocId()) {
                        T temp = currentNode.data;
                        currentNode.data = currentNode.next.data;
                        currentNode.next.data = temp;
                        swapped = true;
                    }
                }

                currentNode = currentNode.next;
            }
        } while (swapped);
    }

    // this method is used to display the list - O(n)
    public void display() {
        Node<T> tmp = head;
        while(tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }
}
