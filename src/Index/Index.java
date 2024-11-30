package Index;

import Process.*;
import List.*;

public class Index {
    private LinkedList<Document> documents;

    // This is the constructor of the Index class - O(1)
    public Index() {
        documents = new LinkedList<Document>();
    }

    // This method is used to add a document to the index - O(1)
    public void addDocument(Document doc) {
        documents.insert(doc);
    }

    // This method is used to add documents to the index - O(n)
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

    // This method is used to set the documents of the index - O(1)
    public void setDocuments(LinkedList<Document> documents) {
        this.documents = documents;
    }

    // This method is used to get the documents of the index - O(1)
    public LinkedList<Document> getDocuments() {
        return documents;
    }

    // This method is used to display the documents of the index - O(n)
//    public void displayDocuments() {
//        documents.findFirst();
//        while (!documents.last()) {
//            Document doc = documents.retrieve();
//            System.out.println(doc.toString());
//            documents.findNext();
//        }
//        Document doc = documents.retrieve();
//        System.out.println(doc.toString());
//    }

    // This method is used to display the documents of a word - O(n)
//    public void displayDocuments(LinkedList<Document> docs) {
//        docs.findFirst();
//        while (!docs.last()) {
//            Document doc = docs.retrieve();
//            // print word and doc ids
//            System.out.println(doc.toString());
//            docs.findNext();
//        }
//        Document doc = docs.retrieve();
//        System.out.println(doc.toString());
//    }

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

    // This method is used to find a word in the index - O(n^2)
//    public Word findWord(String word) {
//        documents.findFirst();
//        while (!documents.last()) {
//            Document doc = documents.retrieve();
//            LinkedList<Word> words = doc.getWords();
//            words.findFirst();
//            while (!words.last()) {
//                Word w = words.retrieve();
//                if (w.getWord().equals(word)) {
//                    return w;
//                }
//                words.findNext();
//            }
//            Word w = words.retrieve();
//            if (w.getWord().equals(word)) {
//                return w;
//            }
//            documents.findNext();
//        }
//        Document doc = documents.retrieve();
//        LinkedList<Word> words = doc.getWords();
//        words.findFirst();
//        while (!words.last()) {
//            Word w = words.retrieve();
//            if (w.getWord().equals(word)) {
//                return w;
//            }
//            words.findNext();
//        }
//        Word w = words.retrieve();
//        if (w.getWord().equals(word)) {
//            return w;
//        }
//        return null;
//    }

    // This method is used to process Boolean queries - O(n^2)
    public LinkedList<Document> processBooleanQuery(String query) {
        LinkedList<Document> result = new LinkedList<Document>();
        String[] words = query.toLowerCase().split(" ");
        LinkedList<String> processedWords = new LinkedList<String>();

        // First, process all AND operations
        for (int i = 1; i < words.length; i++) { // O(n)
            String operator = words[i];
            if (operator.equalsIgnoreCase("and")) {
                String word1 = words[i - 1];
                String word2 = words[i + 1];
                LinkedList<Document> wordDocs1 = find(word1); // O(n^2)
                LinkedList<Document> wordDocs2 = find(word2); // O(n^2)
                result = processAndQuery(wordDocs1, wordDocs2);
                if (!processedWords.find(word1)) {
                    processedWords.insert(word1);
                }
                if (!processedWords.find(word2)) {
                    processedWords.insert(word2);
                }
            }
        }

        // Then, process all OR operations
        for (int i = 0; i < words.length; i += 2) {
            String word = words[i];
            if (!processedWords.find(word)) {
                LinkedList<Document> wordDocs = find(word);
                result = processOrQuery(result, wordDocs);
            }
        }

        return result;
    }

    // This method is used to process Boolean queries - O(n^2)
//    public LinkedList<Document> processBooleanQueryX(String query) {
//        LinkedList<Document> result = new LinkedList<Document>();
//
//        String[] words = query.toLowerCase().split(" ");
//
//        if(words.length == 1) {
//            System.out.println("Please enter a valid query. Example: market and sports");
//            return null;
//        }
//
//
//        result = find(words[0]);
//        for(int i = 1; i < words.length; i+=2) {
//            String operator = words[i];
//            String word = words[i+1];
//
//            LinkedList<Document> wordDocs = find(word);
//
//            // AND has precedence over OR
//            if(operator.equalsIgnoreCase("and")) {
//                result = processAndQuery(result, wordDocs);
//            } else if(operator.equalsIgnoreCase("or")) {
//                result = processOrQuery(result, wordDocs);
//            } else {
//                System.out.println("Please enter a valid query. Example: market and sports");
//                return null;
//            }
//        }
//
//        return result;
//    }

    // This method used to process AND queries - O(n)
    private LinkedList<Document> processAndQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
        LinkedList<Document> result = new LinkedList<Document>();

        if(docs1 == null || docs2 == null || docs1.empty() || docs2.empty()) {
            return null;
        }

        docs1.findFirst();
        while (!docs1.last()) {
            Document doc = docs1.retrieve();
            if(docs2 != null && docs2.find(doc)) {
                result.insert(doc);
            }
            docs1.findNext();
        }
        Document doc = docs1.retrieve();
        if(docs2 != null && docs2.find(doc)) {
            result.insert(doc);
        }

        return result;
    }

    // This method used to process OR queries - O(n)
    private LinkedList<Document> processOrQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
        LinkedList<Document> result = new LinkedList<Document>();

        if(docs1 == null && docs2 == null) {
            return null;
        } else if(docs1 == null || docs1.empty()) {
            return docs2;
        } else if(docs2 == null || docs2.empty()) {
            return docs1;
        }

        // docs1 iteration
        docs1.findFirst();
        while (!docs1.last()) {
            Document doc = docs1.retrieve();
            if(!result.find(doc)) {
                result.insert(doc);
            }
            docs1.findNext();
        }
        Document doc = docs1.retrieve();
        if(!result.find(doc)) {
            result.insert(doc);
        }

        // docs2 iteration
        docs2.findFirst();
        while (!docs2.last()) {
            Document doc2 = docs2.retrieve();
            if(!result.find(doc2)) {
                result.insert(doc2);
            }
            docs2.findNext();
        }
        Document doc2 = docs2.retrieve();
        if(!result.find(doc2)) {
            result.insert(doc2);
        }

        return result;
    }

    // This method is used to process ranked queries - O(n^2)
    public void processRankedQuery(String query) {
        LinkedList<Document> result = new LinkedList<Document>();
        String[] words = query.toLowerCase().split(" ");

        System.out.println("Ranked retrieval for: " + query);
        System.out.println("Document ID - Rank");

        for (String word : words) {
            LinkedList<Document> docs = find(word);
            if (docs == null) {
                continue;

            }
            docs.findFirst();
            while (!docs.last()) {
                Document doc = docs.retrieve();
                if (!result.find(doc)) {
                    result.insert(doc);
                    doc.setRank(doc.getWordFrequencyInDocument(word));
                } else {
                    doc.setRank(doc.getRank() + doc.getWordFrequencyInDocument(word));
                }
                docs.findNext();
            }
            Document doc = docs.retrieve();
            if (!result.find(doc)) {
                result.insert(doc);
                doc.setRank(doc.getWordFrequencyInDocument(word));
            } else {
                doc.setRank(doc.getRank() + doc.getWordFrequencyInDocument(word));
            }
        }

        result.sortByRankDescending();

        result.display();
    }
}
