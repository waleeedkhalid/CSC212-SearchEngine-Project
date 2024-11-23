import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static SearchEngine SE = new SearchEngine();

    public static int menu() throws IOException {
        System.out.println("1. Boolean Retrieval. ");
        System.out.println("2. Ranked Retrieval.");
        System.out.println("3. Indexed Documents.");
        System.out.println("4. Indexed Tokens.");
        System.out.println("5. Exit.");

        System.out.println("Enter choice:");
        String choiceStr = input.readLine();
        int choice;

        try {
            choice = Integer.parseInt(choiceStr); // Parse input to integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1; // Return an invalid choice to prompt the menu again
        }

        return choice;
    }

    public static void printBoolean(boolean[] result) {
        Term t = new Term("", result);
        System.out.println(t);
    }

    public static void booleanRetrievalMenu() {
        String[] Questions = {
                "market AND sports",
                "weather AND warming",
                "business AND world",
                "weather OR warming",
                "market OR sports",
                "market OR sports AND warming"
        };

        System.out.println("################### Boolean Retrieval ####################");
        System.out.println("Q#: ");

        for (String str : Questions) {
            System.out.println(str);
            System.out.print("Result doc IDs: ");
            printBoolean(SE.booleanRetrieval(str, 2));
            System.out.println("\n");
        }
    }

    public static void rankedRetrievalMenu() {
        String[] Questions = {
                "market sports",
                "weather warming",
                "business world market"
        };

        System.out.println("######## Ranked Retrieval ######## ");
        System.out.println("Q#: ");

        for (String str : Questions) {
            System.out.println("## Q: " + str);
            System.out.println("DocID\tScore");
            SE.rankedRetrieval(str);
            System.out.println("\n");
        }
    }

    public static void indexedDocumentsMenu() {
        System.out.println("######## Indexed Documents ######## ");
        System.out.println("tokens " + SE.tokens);
    }

    public static void indexedTokensMenu() {
        System.out.println("######## Indexed Tokens ######## ");
        System.out.println("tokens " + SE.vocab);
    }

    public static void main(String[] args) throws IOException {
        SE.LoadData("C:\\Users\\sulaiman\\Desktop\\stop.txt", "C:\\Users\\sulaiman\\Desktop\\dataset.csv");

        int choice;

        do {
            choice = menu();
            switch (choice) {
                // Boolean Retrieval: to enter a Boolean query that will return a set of unranked documents
                case 1:
                    booleanRetrievalMenu();
                    break;

                // Ranked Retrieval: to enter a query that will return a ranked list of documents with their scores
                case 2:
                    rankedRetrievalMenu();
                    break;

                // Indexed Documents: to show number of documents in the index
                case 3:
                    indexedDocumentsMenu();
                    break;

                // Indexed Tokens: to show number of vocabulary and tokens in the index
                case 4:
                    indexedTokensMenu();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Bad choice, try again!");
            }
        } while (choice != 5);
    }
}
