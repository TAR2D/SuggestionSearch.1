import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    private static final TrieAC storage = new TrieAC();

    /**
     * [INITIALIZE TRIE + ADDING DATA TO IT]
     * Create a Data structure using Trie Algorithm.
     * Default Method: Use provided dictionary to create structure.
     */
    //static int countWords = 0;
    static void create() {
        File file = new File("dictionary.txt");
        try {
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String word = read.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                storage.insert(word.toCharArray());
            }
            read.close();
        } catch (Exception e) {
            System.out.print("error: " + e);
        }
    }

    /**
     * [INITIALIZE TRIE + ADDING DATA TO IT]
     * Create a Data structure using Trie Algorithm.
     * Read given file and add data into Trie.
     *
     * @param inputFile : file used to create data to Trie
     */
    static void create(String inputFile) {
        File file = new File(inputFile);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String word = read.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                storage.insert(word.toCharArray());
            }
            read.close();
        } catch (Exception e) {
            System.out.print("error: " + e);
        }
    }

    /**
     * Method used to call auto completion method and prioritize method to print suggestions
     *
     * @param query : user input word. Suggestions will be based on this key and priorities.
     */
    static void autoComplete(String query, int feature, boolean prioritise) {
        boolean sizeControl = false;
        storage.autoCompleteHelper(query.toCharArray(), feature);
        if (feature == 1)
            sizeControl = true;
        if (prioritise) //TODO Private Account
            storage.prioritise(sizeControl);
        printData.list(storage.getTopSuggestion(), sizeControl); //printing Suggestions
        if (sizeControl) {
            storage.getTopSuggestion().clear(); //clearing Previous Suggestion
            System.out.println("Yes");
        }
    }

    /**
     * Method used to update Trie. Take user response
     * and use that selected suggestion to give prioritization.
     *
     * @param chosenKey : user input selected suggestion index.
     */
    static void update(int chosenKey) {
        if (chosenKey == 6) {
            System.out.print("[Alert] Suggestion skipped \n");
        } else {
            storage.insertWeight(storage.getTopSuggestion().get(chosenKey - 1).toCharArray());
            System.out.println("Weight added to suggestion " + storage.getTopSuggestion().get(chosenKey - 1));
            storage.getTopSuggestion().clear(); //clearing Previous Suggestion
        }
    }

    static int getSuggestionSize() {
        return storage.getTopSuggestion().size();
    }

    static String getChooseSuggestion(int index) {
        return storage.getTopSuggestion().get(index);
    }

}
