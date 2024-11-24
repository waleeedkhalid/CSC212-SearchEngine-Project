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

    // Ranked Retrieval
    public void rankedRetrieval(String query) {
        // 1. Tokenize query
        // 2. For each token, get the document frequency
        // 3. For each token, get the term frequency
        // 4. Calculate the weight of each token
        // 5. Calculate the weight of the document
        // 6. Calculate the similarity of the document
        // 7. Sort the documents by similarity
        // 8. Display the documents
    }

    // Boolean Retrieval
    public void booleanRetrieval(String query) {

    }
}