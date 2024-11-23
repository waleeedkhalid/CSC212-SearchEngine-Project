package Index;

import Process.*;

public class Index {
    Document[] indexes;

    public Index() {
        indexes = new Document [100];
    }

    public void addDocument(Document document) {
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] == null) {
                indexes[i] = document;
                break;
            }
        }
    }

    public Document[] getDocuments() {
        return indexes;
    }

    public Document searchInformation(String word) {
        for (Document document : indexes) {
            if (document != null) {
                if (document.getWords().equals(word)) {
                    return document;
                }
            }
        }
        return null;
    }
}