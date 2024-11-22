import Process.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DocumentProcessing dp = new DocumentProcessing();
            Document.printDocumentsContentWordsList(dp.getDocuments());
            Menu menu = new Menu();
//            menu.displayMenu();
//            while(true) {
//                int choice = scanner.nextInt();
//                switch (choice) {
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        break;
//                    case 4:
//                        break;
//                    case 5:
//                        System.out.println(dp.getDocuments().size() + " Documents");
//                        break;
//                    case 6:
//                        System.out.println(dp.searchInformation("tokens").retrieve());
//                        break;
//                    case 7:
//                        System.out.println("Exiting program...");
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                        break;
//                }
//                menu.displayEnterChoice();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}