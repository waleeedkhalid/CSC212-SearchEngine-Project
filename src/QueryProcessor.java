import List.LinkedList;
import Process.*;
import Index.*;

public class QueryProcessor {

    // This method is used to process AND queries - Worst case O(n^2) - Average case O(n log n)
    public static LinkedList<Document> processBooleanQuery(String query, IndexInterface index) {
        LinkedList<Document> result = new LinkedList<Document>();
//        String[] words = query.toLowerCase().split(" ");
//        LinkedList<String> processedWords = new LinkedList<String>();

        // Process all AND operations
        if(!query.toLowerCase().contains(" or ")) {
            if(query.toLowerCase().contains(" and ")) {
                String[] splitByAND = query.toLowerCase().split(" and ");
                LinkedList<Document> andResult = new LinkedList<Document>();
                for (String word : splitByAND) {
                    LinkedList<Document> wordDocs = index.find(word.trim());
                    if (andResult != null && andResult.empty()) {
                        andResult = wordDocs;
                    } else {
                        andResult = processAndQuery(andResult, wordDocs);
                    }
                }
                return andResult;
            } else {
                return index.find(query);
            }
        }

        String[] splitByOR = query.toLowerCase().split(" or ");
        for (String orOperation : splitByOR) { // O(n)
            String[] splitByAND = orOperation.toLowerCase().split(" and ");
            LinkedList<Document> andResult = new LinkedList<Document>();
            for (String word : splitByAND) { // O(n)
                LinkedList<Document> wordDocs = index.find(word.trim()); // Index O(n^2) - InvertedIndex O(n) - BSTIndex O(h) where h is height of longest path - AVLIndex O(log n)
                if (andResult != null && andResult.empty()) {
                    andResult = wordDocs;
                } else {
                    andResult = processAndQuery(andResult, wordDocs);
                }
            }
            if (result.empty()) {
                result = andResult; // Initialize result with the first AND operation result
            } else {
                result = processOrQuery(result, andResult); // Combine the result of ANDs with OR
            }
        }


        // First, process all AND operations
//        for (int i = 1; i < words.length; i+=2) { // O(n)
//            String operator = words[i];
//            if (operator.equalsIgnoreCase("and")) {
//                String word1 = words[i - 1];
//                String word2 = words[i + 1];
//                LinkedList<Document> wordDocs1 = index.find(word1); // Index O(n^2) - InvertedIndex O(n) - BSTIndex O(h) where h is height of longest path - AVLIndex O(log n)
//                LinkedList<Document> wordDocs2 = index.find(word2); // Index O(n^2) - InvertedIndex O(n) - BSTIndex O(h) where h is height of longest path - AVLIndex O(log n)
//                result = processAndQuery(wordDocs1, wordDocs2); // O(n)
//                if (!processedWords.find(word1)) {
//                    processedWords.insert(word1);
//                }
//                if (!processedWords.find(word2)) {
//                    processedWords.insert(word2);
//                }
//            }
//        }

        // Then, process all OR operations
//        for (int i = 0; i < words.length; i += 2) {
//            String word = words[i];
//            if (!processedWords.find(word)) {
//                LinkedList<Document> wordDocs = index.find(word);
//                result = processOrQuery(result, wordDocs);
//            }
//        }

        return result;
    }

    // This method is used to process AND queries - O(n)
    private static LinkedList<Document> processAndQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
        LinkedList<Document> result = new LinkedList<Document>();

        if (docs1 == null || docs2 == null || docs1.empty() || docs2.empty()) {
            return result;
        }

        LinkedList<Document> smallerList = (docs1.size() <= docs2.size()) ? docs1 : docs2;
        LinkedList<Document> largerList = (docs1.size() > docs2.size()) ? docs1 : docs2;


        smallerList.findFirst();
        while (!smallerList.last()) {
            Document doc = smallerList.retrieve();
            if (largerList.find(doc)) {
                result.insert(doc);
            }
            smallerList.findNext();
        }
        Document doc = smallerList.retrieve();
        if (largerList.find(doc)) {
            result.insert(doc);
        }

        return result;
    }


    // This method used to process OR queries - O(n)
    private static LinkedList<Document> processOrQuery(LinkedList<Document> docs1, LinkedList<Document> docs2) {
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
    public static void processRankedQuery(String query, IndexInterface index) {
        LinkedList<Document> result = new LinkedList<Document>();
        String[] words = query.toLowerCase().split(" ");

        System.out.println("Ranked retrieval for: " + query);
        System.out.println("Document ID - Rank");

        for (String word : words) { // O(n)
            LinkedList<Document> docs = index.find(word);
            if (docs == null) {
                continue;

            }
            docs.findFirst();
            while (!docs.last()) { // O(n)
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
