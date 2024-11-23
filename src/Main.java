import Process.*;

import java.util.Scanner;
import Process.*;
import Index.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DocumentProcessing dp = new DocumentProcessing();
            Document.printDocumentsContentWordsList(dp.getDocuments());
            Menu menu = new Menu();

            Index index = new Index();
            InvertedIndex invertedIndex = new InvertedIndex();
            InvertedIndexBST invertedIndexBST = new InvertedIndexBST();
            InvertedIndexAVL invertedIndexAVL = new InvertedIndexAVL();

            dp.getDocuments().findFirst();
            while (!dp.getDocuments().last()) {
                Document doc = dp.getDocuments().retrieve();
                index.addDocument(doc);
                invertedIndex.addNew(doc);
                invertedIndexBST.addNew(doc);
                invertedIndexAVL.addNew(doc);
            }
            Document doc = dp.getDocuments().retrieve();
            index.addDocument(doc);




            menu.displayMenu();
            while(true) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        menu.displayEnterRetrieval();
                        System.out.println("Enter 1 for Boolean retrieval or 2 for Ranked retrieval:");
                        int retrievalChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        if (retrievalChoice == 1) {
                            System.out.println("Enter query for Boolean retrieval:");
                            String query = scanner.nextLine();
                            boolean[] results = dp.booleanRetrieval(query);
                            System.out.println("Boolean retrieval results: " + Arrays.toString(results));
                        } else if (retrievalChoice == 2) {
                            System.out.println("Enter query for Ranked retrieval:");
                            String query = scanner.nextLine();
                            LinkedList<Document> results = dp.rankedRetrieval(query);
                            System.out.println("Ranked retrieval results: " + results);
                        } else {
                            System.out.println("Invalid retrieval choice.");
                        }
                        break;
                    case 2:
                        menu.displayEnterRetrieval();
                        System.out.println("Enter query for Ranked retrieval:");
                        String query = scanner.nextLine();

                        break;
                    case 3:
                        menu.displayEnterRetrieval();

                        break;
                    case 4:
                        menu.displayEnterRetrieval();
                        break;
                    case 5:
                        System.out.println(dp.getDocuments().size() + " Documents");
                        break;
                    case 6:
                        System.out.println(dp.searchInformation("tokens").retrieve());
                        break;
                    case 7:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                menu.displayEnterChoice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}