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
    LinkedList<String> infoList;

    // This is the constructor of the DocumentProcessing class - O(1)
    public DocumentProcessing() {
        this.documents = new LinkedList<Document>();
        this.stopWords = new LinkedList<>();
        this.infoList = new LinkedList<>(); // save useful information
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
                    // here we are insert the non-document lines into the infoList so no information gone.
                    infoList.insert(line);
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

    // This method is used to read the stopWords file - O(n)
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
            LinkedList<Word> wordsList = processDocument(doc);

            doc.setWords(wordsList);
            documents.findNext();
        }

        Document doc = documents.retrieve();
        LinkedList<Word> wordsList = processDocument(documents.retrieve());

        doc.setWords(wordsList);
    }

    // This method is used to process one document - O(n)
    public LinkedList<Word> processDocument(Document doc) {
        String[] words = doc.getContent().split("\\s+");
        LinkedList<Word> wordsList = new LinkedList<>();
        for (String word : words) {
            String processedWord = word.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
            if (!stopWords.find(processedWord)) {
//                Word wordObj = new Word(processedWord, doc);
                Word wordObj = Word.getOrCreateWord(processedWord, doc);
                // if the word is not in the list, create a new object and add the document to the list of documents
                if (!wordsList.find(wordObj)) {
                    wordsList.insert(wordObj);
                } else {
                    // if the word is already in the list, add the document to the list of documents
                    wordsList.retrieve().addDoc(doc);
                }
            }
        }

        return wordsList;
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
                    infoList.insert(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return infoList;
    }

    // This method is used to get the infoList - O(1)
    public LinkedList<String> getInfoList() {
        return infoList;
    }
}
