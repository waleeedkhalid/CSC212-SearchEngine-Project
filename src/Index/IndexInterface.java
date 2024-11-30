package Index;

import Process.*;
import List.*;

public interface IndexInterface {
    public void addDocument(Document doc);
    public void addDocuments(LinkedList<Document> documents);
    public LinkedList<Document> find(String word);
}
