package Process;

import List.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DocumentProcessing {
    String dataPath = "data/dataset.csv";
    String stopWordsPath = "data/stop.txt";
    LinkedList<Document> documents;
    LinkedList<String> stopWords;
    LinkedList<String> testList;

    // This is the constructor of the DocumentProcessing class - O(1)
    public DocumentProcessing() {
        this.documents = new LinkedList<Document>();
        this.stopWords = new LinkedList<String>();
        this.testList = new LinkedList<String>(); // save useful information
        readCsvFile(dataPath);
        readStopwordsFile(stopWordsPath);
        processDocuments();
    }

    // This method is used to read the csv file - O(n)
    public void readCsvFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line=reader.readLine()) != null) {
//                System.out.println(line);
                String[] parts = line.split(",", 2);

                String docIdStr = parts[0].trim();
                String content = parts[1].trim();

                try {
                    int docId = Integer.parseInt(docIdStr);
                    documents.insert(new Document(docId, content));
//                    System.out.println(line);
                } catch (NumberFormatException e) {
                    // here we are insert the non-document lines into the testList so no information gone.
                    testList.insert(line);
//                    System.out.println(line);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method is used to read the stopwords file - O(n)
    public void readStopwordsFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.insert(line);
//                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method is used to process the documents - O(n^2)
    public void processDocuments() {
        documents.findFirst();
        while (!documents.last()) {
            Document doc = documents.retrieve();
            String content = doc.getContent();
            String[] words = content.split("\\s+");
            LinkedList<String> wordsList = new LinkedList<String>();

            for (String word : words) {
                String wordLower = word.toLowerCase();
                if (!stopWords.find(wordLower)) {
                    wordsList.insert(wordLower);
                }
            }

            doc.setWords(wordsList);
            documents.findNext();
        }

        Document doc = documents.retrieve();
        String content = doc.getContent();
        String[] words = content.split("\\s+");
        LinkedList<String> wordsList = new LinkedList<String>();

        for (String word : words) {
            String wordLower = word.toLowerCase();
            if (!stopWords.find(wordLower)) {
                wordsList.insert(wordLower);
            }
        }

        doc.setWords(wordsList);
    }

    // This method is used to get the documents - O(1)
    public LinkedList<Document> getDocuments() {
        return documents;
    }

    // This method is used to get the stopwords - O(1)
    public LinkedList<String> getStopWords() {
        return stopWords;
    }


    // This method is used to search the information from the dataset.csv - O(n)
    public LinkedList<String> searchInformation(String word) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(word.toLowerCase())) {
                    testList.insert(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testList;
    }

    // This method is used to get the testList - O(1)
    public LinkedList<String> getTestList() {
        return testList;
    }
}