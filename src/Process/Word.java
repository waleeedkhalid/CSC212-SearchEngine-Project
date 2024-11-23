package Process;

import List.LinkedList;

public class Word {
    String word;
    LinkedList<Document> docs;
    int Frequency;

    // This is the constructor of the Word class - O(1)
    public Word(String word, Document doc) {
        this.word = word;
        this.docs = new LinkedList<>();
        docs.insert(doc);
    }

    // This method is used to get the word - O(1)
    public String getWord() {
        return word;
    }

    // This method is used to increase the frequency of the word - O(1)
    public void increaseFrequency() {
        Frequency++;
    }

    // This method is used to get the frequency of the word - O(1)
    public int getFrequency() {
        return Frequency;
    }

    // This method is used to get the documents - O(1)
    public LinkedList<Document> getDocs() {
        return docs;
    }

    // This method is used to set the word - O(1)
    public void setWord(String word) {
        this.word = word;
    }

    // This method is used to add the document to word object - O(1)
    public void addDoc(Document doc) {
        this.docs.insert(doc);
        increaseFrequency();
    }

    // This method is used to check if the Word object is in Specific Document or not - O(n)
    public boolean isWordInDoc(Document doc) {
        docs.findFirst();
        while(!docs.last()) {
            if(docs.retrieve().getDocId() == doc.getDocId()) {
                return true;
            }
            docs.findNext();
        }
        return docs.retrieve().getDocId() == doc.getDocId();
    }

    // This method is used to print the word and the documents - O(n)
    public void print() {
        System.out.println("Word: " + word);
        System.out.println("Documents: ");
        docs.findFirst();
        while(!docs.last()) {
            System.out.println(docs.retrieve().getDocId());
            docs.findNext();
        }
        System.out.println(docs.retrieve().getDocId());
    }

    // This method is used to compare the word - O(1)
    public int compareTo(Word w) {
        return word.compareTo(w.getWord());
    }
}
