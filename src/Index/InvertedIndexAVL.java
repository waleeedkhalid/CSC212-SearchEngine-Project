package Index;

import Process.*;
import List.*;
import Trees.AVL.AVLTree;
import Trees.BST.Order;

public class InvertedIndexAVL {
    private AVLTree words;

    public InvertedIndexAVL() {
        words = new AVLTree();
    }

    public void addDocument(Document doc) {
        LinkedList<Word> docWords = doc.getWords();
        docWords.findFirst();
        while (!docWords.last()) {
            Word word = docWords.retrieve();
            if (!words.find(word.getWord())) {
                words.insert(word);
            }
            docWords.findNext();
        }
        Word word = docWords.retrieve();
        if (!words.find(word.getWord())) {
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

    // Returns the list of documents that contain the word - O(log n)
    public LinkedList<Document> find(String word) {
        return words.search(word).getDocs();
    }

    public AVLTree getWords() {
        return words;
    }
}