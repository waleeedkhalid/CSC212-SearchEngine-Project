package Process;

import List.LinkedList;

public class FrequencyManager {
    // I want to control frequency of words in list of all documents
    // I want to be able to get the frequency of a word in a document
    // I want to be able to get the frequency of a word in all documents

    private LinkedList<Document> documents;

    public FrequencyManager(LinkedList<Document> documents) {
        this.documents = documents;
    }

    public int getWordFrequencyInDocument(String word, Document document) {
        int frequency = 0;
        document.getWords().findFirst();
        while (!document.getWords().last()) {
            if (document.getWords().retrieve().equals(word)) {
                frequency++;
            }
            document.getWords().findNext();
        }
        if (document.getWords().retrieve().equals(word)) {
            frequency++;
        }
        return frequency;
    }

    public int getWordFrequencyInAllDocuments(String word) {
        int frequency = 0;
        documents.findFirst();
        while (!documents.last()) {
            frequency += getWordFrequencyInDocument(word, documents.retrieve());
            documents.findNext();
        }
        frequency += getWordFrequencyInDocument(word, documents.retrieve());
        return frequency;
    }
}
