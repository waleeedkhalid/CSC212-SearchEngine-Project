package Index;

import Process.*;
import List.*;
import Trees.AVL.AVLTree;
import Trees.BST.Order;

public class InvertedIndexAVL implements IndexInterface {
    private AVLTree words;

    public InvertedIndexAVL() {
        this.words = new AVLTree();
    }

    public void addDocument(Document doc) {
        LinkedList<Word> docWords = doc.getWords();
        docWords.findFirst();
        while (!docWords.last()) {
            Word word = docWords.retrieve();
            words.insert(word);
            docWords.findNext();
        }
        Word word = docWords.retrieve();
        words.insert(word);
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


    // Returns the list of documents that contain the word - O(log n)
    public LinkedList<Document> find(String word) {
        return words.search(word).getDocs();
    }

    public AVLTree getWords() {
        return words;
    }
}