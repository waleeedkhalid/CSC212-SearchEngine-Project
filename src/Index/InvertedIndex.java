package Index;

import Process.*;
import List.*;

public class InvertedIndex {
    private LinkedList<Word> words;

    public InvertedIndex() {
        words = new LinkedList<Word>();
    }

    public void addDocument(Document doc) {
        LinkedList<Word> docWords = doc.getWords();
        docWords.findFirst();
        while (!docWords.last()) {
            Word word = docWords.retrieve();
            if (!words.find(word)) {
                words.insert(word);
            }
            docWords.findNext();
        }
        Word word = docWords.retrieve();
        if (!words.find(word)) {
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
        words.findFirst();
        while (!words.last()) {
            Word word = words.retrieve();
            System.out.println(word.toString());
            words.findNext();
        }
        Word word = words.retrieve();
        System.out.println(word.toString());
    }

    // This method is used to find word documents in the inverted index - O(n)
    public LinkedList<Document> find(String word) {
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