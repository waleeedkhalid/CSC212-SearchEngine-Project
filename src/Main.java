import Process.*;
import Index.*;
import List.*;
import Retrieval.QueryProcessor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DocumentProcessing dp = new DocumentProcessing();

            Index index = new Index();
            index.setDocuments(dp.getDocuments());
            System.out.print("Index: ");
            System.out.println(index.getDocuments().size());
            System.out.print("Inverted Index: ");
            InvertedIndex invertedIndex = new InvertedIndex();
            invertedIndex.addDocuments(dp.getDocuments());
            System.out.println(invertedIndex.getWords().size());
            System.out.print("Inverted Index with BST: ");
            InvertedIndexBST invertedIndexBST = new InvertedIndexBST();
            invertedIndexBST.addDocuments(dp.getDocuments());
            System.out.println(invertedIndexBST.getWords().size());
            System.out.print("Inverted Index with AVL: ");
            InvertedIndexAVL invertedIndexAVL = new InvertedIndexAVL();
            invertedIndexAVL.addDocuments(dp.getDocuments());
            System.out.println(invertedIndexAVL.getWords().size());


            Menu menu = new Menu();
            menu.displayMenu();
            while(true) {
                try {
                int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Searching in Index...\n");
                            handleSearchInIndex(menu, scanner, index);
                            break;
                        case 2:
                            System.out.println("Searching in Inverted Index...\n");
                            handleSearchInInvertedIndex(menu, scanner, invertedIndex);
                            break;
                        case 3:
                            System.out.println("Searching in Inverted Index with BST...\n");
                            handleSearchInInvertedIndexBST(menu, scanner, invertedIndexBST);
                            break;
                        case 4:
                            System.out.println("Searching in Inverted Index with AVL...\n");
                            handleSearchInInvertedIndexAVL(menu, scanner, invertedIndexAVL);
                            break;
                        case 5:
                            System.out.println(dp.getDocuments().size() + " Documents");
                            break;
                        case 6:
                            System.out.println("Indexed Tokens: " + WordManager.countWords());
                            break;
                        case 7:
                            System.out.println("Exiting program...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine();
                }
                menu.displayEnterChoice();
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private static void handleSearchInIndex(Menu menu, Scanner scanner, Index index) {
        menu.displayEnterRetrieval();
        int retrievalChoice = scanner.nextInt();
        scanner.nextLine();
        if (retrievalChoice == 1) {
            System.out.println("Enter query for Boolean retrieval:");
            String query = scanner.nextLine();
            QueryProcessor.processBooleanQuery(query, index);
        } else if (retrievalChoice == 2) {
            System.out.println("Enter query for Ranked retrieval:");
            String query = scanner.nextLine();
            QueryProcessor.processRankedQuery(query, index);
        } else if (retrievalChoice == 3) {
            System.out.println("Enter query to search:");
            String query = scanner.nextLine();
            LinkedList<Document> doc = index.find(query);
            if(doc != null) {
                System.out.println("Document found: \n");
                Document.printDocuments(doc);
            } else {
                System.out.println("Document not found.");
            }
        }
    }

    private static void handleSearchInInvertedIndex(Menu menu, Scanner scanner, InvertedIndex index) {
        menu.displayEnterRetrieval();
        int retrievalChoice = scanner.nextInt();
        scanner.nextLine();
        if (retrievalChoice == 1) {
            System.out.println("Enter query for Boolean retrieval:");
            String query = scanner.nextLine();
            LinkedList<Document> doc = index.find(query);
            if(doc != null) {
                System.out.println("Document found: \n");
                Document.printDocuments(doc);
            } else {
                System.out.println("Document not found.");
            }
        } else if (retrievalChoice == 2) {
            System.out.println("Enter query for Ranked retrieval:");
            String query = scanner.nextLine();
        }
    }

    private static void handleSearchInInvertedIndexBST(Menu menu, Scanner scanner, InvertedIndexBST index) {
        menu.displayEnterRetrieval();
        int retrievalChoice = scanner.nextInt();
        scanner.nextLine();
        if (retrievalChoice == 1) {
            System.out.println("Enter query for Boolean retrieval:");
            String query = scanner.nextLine();
            LinkedList<Document> doc = index.find(query);
            if(doc != null) {
                System.out.println("Document found: \n");
                Document.printDocuments(doc);
            } else {
                System.out.println("Document not found.");
            }
        } else if (retrievalChoice == 2) {
            System.out.println("Enter query for Ranked retrieval:");
            String query = scanner.nextLine();
        }
    }

    private static void handleSearchInInvertedIndexAVL(Menu menu, Scanner scanner, InvertedIndexAVL index) {
        menu.displayEnterRetrieval();
        int retrievalChoice = scanner.nextInt();
        scanner.nextLine();
        if (retrievalChoice == 1) {
            System.out.println("Enter query for Boolean retrieval:");
            String query = scanner.nextLine();
            LinkedList<Document> doc = index.find(query);
            if(doc != null) {
                System.out.println("Document found: \n");
                Document.printDocuments(doc);
            } else {
                System.out.println("Document not found.");
            }
        } else if (retrievalChoice == 2) {
            System.out.println("Enter query for Ranked retrieval:");
            String query = scanner.nextLine();
        }
    }
}