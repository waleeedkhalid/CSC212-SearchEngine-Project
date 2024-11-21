public class Menu {
    public void displayMenu() {
        System.out.println("Welcome to the Simple Search Engine!");
        System.out.println("This system is designed to retrieve documents based on the user's query.");
        System.out.println("The system supports Boolean and Ranked Retrieval.");
        System.out.println("The system also provides the ability to view indexed documents and tokens.");
        System.out.println("Please choose an option:");
        System.out.println("1. Index");
        System.out.println("2. Inverted Index");
        System.out.println("3. Inverted Index with BST");
        System.out.println("4. Inverted Index with AVL");
        System.out.println("5. Indexed Documents");
        System.out.println("6. Indexed Tokens");
        System.out.println("7. Exit");
        System.out.println("\nEnter your choice: ");
    }

    public void displayIndex() {
        System.out.println("Please choose an option:");
        System.out.println("1. Boolean Retrieval");
        System.out.println("2. Ranked Retrieval");
        System.out.println("3. Exit");
        System.out.println("\nEnter your choice: ");
    }

    public void displayMenuBooleanRetrieval() {
        System.out.println("Please choose an option:");
        System.out.println("1. AND");
        System.out.println("2. OR");
        System.out.println("3. Back to src.Main Menu");
        System.out.println("\nEnter your choice: ");
    }

    public void displayEnterQuery() {
        System.out.println("Enter your query: ");
    }

    public void displayEnterChoice() {
        System.out.println("Enter your choice: ");
    }
}
