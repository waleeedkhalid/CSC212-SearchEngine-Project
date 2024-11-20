package Process;

import List.LinkedList;

public class Document {
    private int docId;
    private String content;
    private LinkedList<String> words;

    // This is the constructor of the Document class - O(1)
    public Document(int docId, String content) {
        this.docId = docId;
        this.content = content;
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
    public LinkedList<String> getWords() {
        return words;
    }

    // This method is used to set the words of the document - O(1)
    public void setWords(LinkedList<String> words) {
        this.words = words;
    }

    // This method is used to print the content of each document in the list - O(n)
    static public void printDocumentsContentList(LinkedList<Document> list) {
        list.findFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().getContent());
            list.findNext();
        }
        System.out.println(list.retrieve().getContent());
    }

    // This method is used to print the words of each document in the list - O(n^2)
    static public void printDocumentsContentWordsList(LinkedList<Document> list) {
        try {
            list.findFirst();
            while (!list.last()) {
                LinkedList<String> words = list.retrieve().getWords();
                words.findFirst();
                while (!words.last()) {
                    System.out.print(words.retrieve() + " ");
                    words.findNext();
                }
                System.out.print(words.retrieve() + " ");
                list.findNext();
                System.out.println();
            }
            LinkedList<String> words = list.retrieve().getWords();
            words.findFirst();
            while (!words.last()) {
                System.out.print(words.retrieve() + " ");
                words.findNext();
            }
            System.out.print(words.retrieve() + " ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
