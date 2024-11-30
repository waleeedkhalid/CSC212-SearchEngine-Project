package Index;

import Process.*;
import List.*;
import Trees.BST.BST;
import Trees.BST.Order;

public class InvertedIndexBST implements IndexInterface {
    private BST words;

    public InvertedIndexBST() {
        words = new BST();
    }

    // This method is used to add a document to the inverted index with BST - O(n)
    public void addDocument(Document doc) {
        LinkedList<Word> docWords = doc.getWords();
        docWords.findFirst();
        while (!docWords.last()) {
            Word word = docWords.retrieve();
            if (!words.search(word)) {
                words.insert(word);
            }
            docWords.findNext();
        }
        Word word = docWords.retrieve();
        if (!words.search(word)) {
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

    // This method is used to find word documents in the inverted index - O(log n) FASTER than InvertedIndex
    public LinkedList<Document> find(String word) {
        if (words.search(word)) {
            return words.retrieve().getDocs();
        }
        return null;
    }

    public BST getWords() {
        return words;
    }

    // This method is used to process AND queries - Worst case O(n^2) - Average case O(n log n)
//    public LinkedList<Document> processBooleanQuery(String query) {
//        LinkedList<Document> result = new LinkedList<Document>();
//        String[] words = query.toLowerCase().split(" ");
//        LinkedList<String> processedWords = new LinkedList<String>();
//
//        // First, process all AND operations
//        for (int i = 1; i < words.length; i+=2) { // O(n)
//            String operator = words[i];
//            if (operator.equalsIgnoreCase("and")) {
//                String word1 = words[i - 1];
//                String word2 = words[i + 1];
//                LinkedList<Document> wordDocs1 = find(word1); // O(log n)
//                LinkedList<Document> wordDocs2 = find(word2); // O(log n)
//                result = processAndQuery(wordDocs1, wordDocs2); // O(n)
//                if (!processedWords.find(word1)) {
//                    processedWords.insert(word1);
//                }
//                if (!processedWords.find(word2)) {
//                    processedWords.insert(word2);
//                }
//            }
//        }
//
//        // Then, process all OR operations
//        for (int i = 0; i < words.length; i += 2) {
//            String word = words[i];
//            if (!processedWords.find(word)) {
//                LinkedList<Document> wordDocs = find(word);
//                result = processOrQuery(result, wordDocs);
//            }
//        }
//
//        return result;
//    }
//
//    // This method used to process AND queries - O(n)
//    private LinkedList<Document> processAndQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
//        LinkedList<Document> result = new LinkedList<Document>();
//
//        if(docs1 == null || docs2 == null || docs1.empty() || docs2.empty()) {
//            return null;
//        }
//
//        docs1.findFirst();
//        while (!docs1.last()) { // O(n)
//            Document doc = docs1.retrieve();
//            if(docs2 != null && docs2.find(doc)) {
//                result.insert(doc);
//            }
//            docs1.findNext();
//        }
//        Document doc = docs1.retrieve();
//        if(docs2 != null && docs2.find(doc)) {
//            result.insert(doc);
//        }
//
//        return result;
//    }
//
//    // This method used to process OR queries - O(n)
//    private LinkedList<Document> processOrQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
//        LinkedList<Document> result = new LinkedList<Document>();
//
//        if(docs1 == null && docs2 == null) {
//            return null;
//        } else if(docs1 == null || docs1.empty()) {
//            return docs2;
//        } else if(docs2 == null || docs2.empty()) {
//            return docs1;
//        }
//
//        // docs1 iteration
//        docs1.findFirst();
//        while (!docs1.last()) { // O(n)
//            Document doc = docs1.retrieve();
//            if(!result.find(doc)) {
//                result.insert(doc);
//            }
//            docs1.findNext();
//        }
//        Document doc = docs1.retrieve();
//        if(!result.find(doc)) {
//            result.insert(doc);
//        }
//
//        // docs2 iteration
//        docs2.findFirst();
//        while (!docs2.last()) {
//            Document doc2 = docs2.retrieve();
//            if(!result.find(doc2)) {
//                result.insert(doc2);
//            }
//            docs2.findNext();
//        }
//        Document doc2 = docs2.retrieve();
//        if(!result.find(doc2)) {
//            result.insert(doc2);
//        }
//
//        return result;
//    }
//
//    // This method is used to process ranked queries - O(n^2)
//    public void processRankedQuery(String query) {
//        LinkedList<Document> result = new LinkedList<Document>();
//        String[] words = query.toLowerCase().split(" ");
//
//        System.out.println("Ranked retrieval for: " + query);
//        System.out.println("Document ID - Rank");
//
//        for (String word : words) { // O(n)
//            LinkedList<Document> docs = find(word);
//            if (docs == null) {
//                continue;
//
//            }
//            docs.findFirst();
//            while (!docs.last()) { // O(n)
//                Document doc = docs.retrieve();
//                if (!result.find(doc)) {
//                    result.insert(doc);
//                    doc.setRank(doc.getWordFrequencyInDocument(word));
//                } else {
//                    doc.setRank(doc.getRank() + doc.getWordFrequencyInDocument(word));
//                }
//                docs.findNext();
//            }
//            Document doc = docs.retrieve();
//            if (!result.find(doc)) {
//                result.insert(doc);
//                doc.setRank(doc.getWordFrequencyInDocument(word));
//            } else {
//                doc.setRank(doc.getRank() + doc.getWordFrequencyInDocument(word));
//            }
//        }
//
//        result.sortByRankDescending();
//
//        result.display();
//    }
}