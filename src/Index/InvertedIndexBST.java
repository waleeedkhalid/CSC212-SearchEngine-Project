package Index;

import Process.*;
import List.*;
import Trees.BST.BST;
import Trees.BST.Order;

public class InvertedIndexBST {
    private BST words;

    public InvertedIndexBST() {
        words = new BST();
    }

    // This method is used to add a document to the inverted index with BST - O(n)
    public void addDocument(Document doc) {
        LinkedList<Word> docWords = doc.getWords();
        docWords.findFirst();
        while (!docWords.last()) {
            Word word = docWords.retrieve();
            if (!words.search(word)) {
                words.insert(word);
            }
            docWords.findNext();
        }
        Word word = docWords.retrieve();
        if (!words.search(word)) {
            words.insert(word);
        }
    }

    public void addDocuments(LinkedList<Document> documents) {
        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            addDocument(doc);
            documents.findNext();
        }
        Document doc = documents.retrieve();
        addDocument(doc);
    }

    public void displayWords() {
        words.traverse(Order.INORDER);
    }

    // This method is used to find word documents in the inverted index - O(log n) FASTER than InvertedIndex
    public LinkedList<Document> find(String word) {
        if (words.search(word)) {
            return words.retrieve().getDocs();
        }
        return null;
    }

    public BST getWords() {
        return words;
    }
}