package Index;

import Process.*;
import List.*;

public class Index {
    private LinkedList<Document> documents;

    public Index() {
        documents = new LinkedList<Document>();
    }

    public void addDocument(Document doc) {
        documents.insert(doc);
    }

    public void addDocuments(LinkedList<Document> docs) {
        docs.findFirst();
        while (!docs.last()) {
            Document doc = docs.retrieve();
            documents.insert(doc);
            docs.findNext();
        }
        Document doc = docs.retrieve();
        documents.insert(doc);
    }

    public void setDocuments(LinkedList<Document> documents) {
        this.documents = documents;
    }

    public LinkedList<Document> getDocuments() {
        return documents;
    }

    public void displayDocuments() {
        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            System.out.println(doc.toString());
            documents.findNext();
        }
        Document doc = documents.retrieve();
        System.out.println(doc.toString());
    }

    public void displayDocuments(LinkedList<Document> docs) {
        docs.findFirst();
        while (!docs.last()) {
            Document doc = docs.retrieve();
            // print word and doc ids
            System.out.println(doc.toString());
            docs.findNext();
        }
        Document doc = docs.retrieve();
        System.out.println(doc.toString());
    }

    // This method is used to find word documents in the index - O(n^2)
    public LinkedList<Document> find(String word) {
        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            LinkedList<Word> words = doc.getWords();
            words.findFirst();
            while (!words.last()) {
                Word w = words.retrieve();
                if (w.getWord().equals(word)) {
                    return w.getDocs();
                }
                words.findNext();
            }
            Word w = words.retrieve();
            if (w.getWord().equals(word)) {
                return w.getDocs();
            }
            documents.findNext();
        }
        Document doc = documents.retrieve();
        LinkedList<Word> words = doc.getWords();
        words.findFirst();
        while (!words.last()) {
            Word w = words.retrieve();
            if (w.getWord().equals(word)) {
                return w.getDocs();
            }
            words.findNext();
        }
        Word w = words.retrieve();
        if (w.getWord().equals(word)) {
            return w.getDocs();
        }
        return null;
    }
}