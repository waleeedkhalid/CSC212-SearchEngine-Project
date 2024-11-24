import Process.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DocumentProcessing dp = new DocumentProcessing();
            Word.displayListOfWord(dp.getDocuments());



//            Index index = new Index();
//            InvertedIndex invertedIndex = new InvertedIndex();
//            InvertedIndexBST invertedIndexBST = new InvertedIndexBST();
//            InvertedIndexAVL invertedIndexAVL = new InvertedIndexAVL();
//
//            dp.getDocuments().findFirst();
//            while (!dp.getDocuments().last()) {
//                Document doc = dp.getDocuments().retrieve();
//                index.addDocument(doc);
//                invertedIndex.addNew(doc);
//                invertedIndexBST.addNew(doc);
//                invertedIndexAVL.addNew(doc);
//            }
//            Document doc = dp.getDocuments().retrieve();
//            index.addDocument(doc);




            Menu menu = new Menu();
            menu.displayMenu();
            while(true) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        menu.displayEnterRetrieval();
                        int retrievalChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        break;
                    case 2:
                        menu.displayEnterRetrieval();
                        System.out.println("Enter query for Ranked retrieval:");
                        String query = scanner.nextLine();
                        int retrievalChoice2 = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        break;
                    case 3:
                        menu.displayEnterRetrieval();
                        int retrievalChoice3 = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        break;
                    case 4:
                        menu.displayEnterRetrieval();
                        int retrievalChoice4 = scanner.nextInt();
                        scanner.nextLine(); // consume newline
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