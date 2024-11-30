package Process;

import List.LinkedList;

public class Document {
    private int docId;
    private String content;
    private LinkedList<Word> words;
    private int rank;

    // This is the constructor of the Document class - O(1)
    public Document(int docId, String content) {
        this.docId = docId;
        this.content = content;
        this.words = new LinkedList<Word>();
        this.rank = 0;
    }

    // This method is used to get the document id - O(1)
    public int getDocId() {
        return docId;
    }

    // This method is used to get the content of the document - O(1)
    public String getContent() {
        return content;
    }

    // This method is used to get the words of the document - O(1)
    public LinkedList<Word> getWords() {
        return words;
    }

    // This method is used to get the rank of the document - O(1)
    public int getRank() {
        return rank;
    }

    // This method is used to set the words of the document - O(1)
    public void setWords(LinkedList<Word> words) {
        this.words = words;
        setRank(words);
    }

    // This method is used to set the rank of the document - O(1)
    public void setRank(LinkedList<Word> words) {
        int rank = 0;
    }

    // This method is used to set the rank of the document - O(1)
    public void setRank(int rank) {
        this.rank = rank;
    }

    // This method is used to print the content of each document in the list - O(n)
    static public void printDocumentsRank(LinkedList<Document> list) {
        list.findFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().getDocId() + ": " + list.retrieve().getRank());
            list.findNext();
        }
        System.out.println(list.retrieve().getDocId() + ": " + list.retrieve().getRank());
    }

    public Word findWord(String word) {
        words.findFirst();
        while (!words.last()) {
            if(words.retrieve().getWord().equals(word)) {
                return words.retrieve();
            }
            words.findNext();
        }
        if(words.retrieve().getWord().equals(word)) {
            return words.retrieve();
        }
        return null;
    }

    public int getWordFrequencyInDocument(String word) {
        int frequency = 0;
        words.findFirst();
        while (!words.last()) {
            if(words.retrieve().getWord().equals(word)) {
//                System.out.println("FREQUENCY !! - "+ docId);
                frequency++;
            }
            words.findNext();
        }
        if(words.retrieve().getWord().equals(word)) {
//            System.out.println("FREQUENCY !! - "+ docId);
            frequency++;
        }
        return frequency;
    }

    // This method is used to print the words of each document in the list - O(n^2)
    static public void printDocumentsContentWordsList(LinkedList<Document> list) {
        try {
            list.findFirst();
            while (!list.last()) {
                LinkedList<Word> words = list.retrieve().getWords();
                words.findFirst();
                System.out.print("DocumentId " + list.retrieve().getDocId() + ": ");
                while (!words.last()) {
                    System.out.print(words.retrieve().getWord() + " ");
                    words.findNext();
                }
                System.out.print(words.retrieve().getWord() + " ");
                list.findNext();
                System.out.println();
            }
            LinkedList<Word> words = list.retrieve().getWords();
            words.findFirst();
            System.out.print("DocumentId " + list.retrieve().getDocId() + ": ");
            while (!words.last()) {
                System.out.print(words.retrieve().getWord() + " ");
                words.findNext();
            }
            System.out.print(words.retrieve().getWord()+ " ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // This method is used to print the number of tokens in the list of documents - O(n)
    static public void printNumberOfTokens(LinkedList<Document> documents) {
        int totalTokens = 0;

        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            String content = doc.getContent();
            String[] wordsList = content.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s");
            LinkedList<String> words = new LinkedList<String>();
            for(String word : wordsList) {
                words.insert(word);
            }

            totalTokens += words.size();
            documents.findNext();
        }

        Document doc = documents.retrieve();
        String content = doc.getContent();
        String[] wordsList = content.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s");
        LinkedList<String> words = new LinkedList<String>();
        for(String word : wordsList) {
            words.insert(word);
        }

        totalTokens += words.size();

        System.out.println("Number of tokens: " + totalTokens);
    }

    // This method is used to print the number of unique tokens in the list of documents - O(n^2)
    static public void printNumberOfUniqueTokens(LinkedList<Document> documents) {
        int totalUniqueTokens = 0;

        LinkedList<String> uniqueWords = new LinkedList<String>();


        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            String content = doc.getContent();
            String[] wordsList = content.toLowerCase().replaceAll("[^a-zA-Z0-9\\s+]", "").split("\\s");
            for(String word : wordsList) {
                if(!uniqueWords.find(word)) {
                    uniqueWords.insert(word);
                }
            }

            documents.findNext();
        }

        Document doc = documents.retrieve();
        String content = doc.getContent();
        String[] wordsList = content.toLowerCase().replaceAll("[^a-zA-Z0-9\\s+]", "").split("\\s");
        for(String word : wordsList) {
            if(!uniqueWords.find(word)) {
                uniqueWords.insert(word);
            }
        }

        totalUniqueTokens = uniqueWords.size();

        System.out.println("Number of unique tokens: " + totalUniqueTokens);
    }

    // This method is used to print Document object - O(1)
    public String toString() {
        return docId+" - "+rank;
    }

    // This method is used to print the documents in the list - O(n)
    static public void printDocuments(LinkedList<Document> documents) {
        documents.findFirst();
        while (!documents.last()) {
            System.out.println(documents.retrieve().toString());
            documents.findNext();
            System.out.println();
        }
        System.out.println(documents.retrieve().toString());
    }

    // This method is used to search for word in list of documents and return Word object - O(n)
    static public Word find(LinkedList<Document> documents, String word) {
        Word wordObject = null;

        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            LinkedList<Word> words = doc.getWords();
            words.findFirst();
            while (!words.last()) {
                Word wordInDoc = words.retrieve();
                if(wordInDoc.getWord().equals(word)) {
                    wordObject = wordInDoc;
                    break;
                }
                words.findNext();
            }
            if(wordObject != null) {
                break;
            }
            documents.findNext();
        }

        Document doc = documents.retrieve();
        LinkedList<Word> words = doc.getWords();
        words.findFirst();
        while (!words.last()) {
            Word wordInDoc = words.retrieve();
            if(wordInDoc.getWord().equals(word)) {
                wordObject = wordInDoc;
                break;
            }
            words.findNext();
        }

        return wordObject;
    }


}
