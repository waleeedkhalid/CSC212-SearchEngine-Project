package Process;

import List.LinkedList;

public class Word {
    String word;
    LinkedList<Document> docs;
    int Frequency;

    // This is the constructor of the Word class - O(1)
    public Word(String word, Document doc) {
        this.word = word;
        docs = new LinkedList<Document>();
        docs.insert(doc);
        Frequency = 1;
        WordManager.addWord(this);
    }

    // This method is used to get the word - O(1)
    public String getWord() {
        return word;
    }

    // This method is used to increase the frequency of the word - O(1)
    public void increaseFrequency() {
        Frequency++;
    }

    // This method is used to get the frequency of the word in all documents - O(1)
    public int getFrequency() {
        return Frequency;
    }

    // This method is used to get the frequency of the word in specific document - O(n)
    public int getFrequency(Document doc) {
        int frequency = 0;
        LinkedList<Word> words = doc.getWords();
        words.findFirst();
        while(!words.last()) {
            if(words.retrieve().getWord().equals(word))
                frequency++;
            words.findNext();
        }
        if(words.retrieve().getWord().equals(word))
            frequency++;
        return frequency;
    }

    // This method is used to set the frequency of the word - O(1)
    public void setFrequency(int frequency) {
        Frequency = frequency;
    }

    // This method is used to get the documents - O(1)
    public LinkedList<Document> getDocs() {
        return docs;
    }

    // This method is used to get the documents by word - O(n)
    public static LinkedList<Document> getDocsByWord(LinkedList<Document> docs, String word) {
        LinkedList<Document> docsByWord = new LinkedList<Document>();
        docs.findFirst();
        while(!docs.last()) {
            LinkedList<Word> words = docs.retrieve().getWords();
            words.findFirst();
            while(!words.last()) {
                if(words.retrieve().getWord().equals(word)) {
                    docsByWord.insert(docs.retrieve());
                }
                words.findNext();
            }
            if(words.retrieve().getWord().equals(word)) {
                docsByWord.insert(docs.retrieve());
            }
            docs.findNext();
        }
        LinkedList<Word> words = docs.retrieve().getWords();
        words.findFirst();
        while(!words.last()) {
            if(words.retrieve().getWord().equals(word)) {
                docsByWord.insert(docs.retrieve());
            }
            words.findNext();
        }
        if(words.retrieve().getWord().equals(word)) {
            docsByWord.insert(docs.retrieve());
        }
        return docsByWord;
    }

    // This method is used to get the documents Ids - O(n)
    public LinkedList<Integer> getDocsIds() {
        LinkedList<Integer> docsIds = new LinkedList<Integer>();
        docs.findFirst();
        while(!docs.last()) {
            docsIds.insert(docs.retrieve().getDocId());
            docs.findNext();
        }
        docsIds.insert(docs.retrieve().getDocId());
        return docsIds;
    }

    // This method is used to set the word - O(1)
    public void setWord(String word) {
        this.word = word;
    }

    // This method is used to add the document to word object - O(1)
    public void addDoc(Document doc) {
        // if the document is not in the list, add the document to the list of documents
        if(!docs.find(doc)) {
            docs.insert(doc);
        }

        // increase the frequency of the word
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

    // Method print the word and the frequency - O(1)
    public void printWordFrequency() {
        System.out.println("Word: " + word + " [" + Frequency + "] ");
    }

    // method print all words from list of documents - O(n)
    static public void displayListOfWord(LinkedList<Document> docs) {
        docs.findFirst();
        while(!docs.last()) {
            LinkedList<Word> words = docs.retrieve().getWords();
            words.findFirst();
            while(!words.last()) {
                words.retrieve().printWordFrequency();
                System.out.println("Document: " + docs.retrieve().getDocId());
                words.findNext();
            }
            docs.findNext();
        }
        LinkedList<Word> words = docs.retrieve().getWords();
        words.findFirst();
        while(!words.last()) {
            words.retrieve().printWordFrequency();
            System.out.println("Document: " + docs.retrieve().getDocId());
            words.findNext();
        }
        words.retrieve().printWordFrequency();
    }


    // This method is used to compare the word - O(1)
    public int compareTo(Word w) {
        return word.compareTo(w.getWord());
    }

    // Factory method to get or create a Word object - O(n)
    public static Word getOrCreateWord(String word, Document doc) {
        return WordManager.getOrCreateWord(word, doc);
    }

    public static int TermFrequency(String word, Document doc) {
        int wordCount = 0;
        int totalWords = doc.getWords().size();

        LinkedList<Word> words = doc.getWords();
        words.findFirst();
        while(!words.last()) {
            if(words.retrieve().getWord().equalsIgnoreCase(word)) {
                wordCount++;
            }
            words.findNext();
        }
        if(words.retrieve().getWord().equalsIgnoreCase(word)) {
            wordCount++;
        }

        return wordCount / totalWords;
    }

    // This method is used to get the word and the documents - O(1)
    public String toString() {
        return "Word: " + word + " [" + Frequency + "] Documents: " + docs.toString();
    }
}