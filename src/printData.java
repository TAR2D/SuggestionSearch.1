import java.util.ArrayList;
import java.util.Arrays;

public class printData {
    public static void message(String type , char removedChar,char[] word){
        if(type.equals("deletion"))
            System.out.println(removedChar + " Removed from word " + Arrays.toString(word));
    }

    public static void message(String type, String warningType, char c, char[] word){
        if(type.equals("warning") && warningType.equals("noChild"))
            System.out.println("Character '" + c + "' in word " + Arrays.toString(word) + " have no children.");
        if(type.equals("warning") && warningType.equals("multipleChild"))
            System.out.println("Multiple Children exist at Character '"+ c +"' in word "+ Arrays.toString(word));
    }
    public static void message(String type, char[] word){
        if(type.equals("wordRemoved"))
            System.out.println(Arrays.toString(word) + " is removed from the list.");
        if(type.equals("isWord"))
            System.out.println(Arrays.toString(word) + " is a query.");
    }

    public static void message(String type, String warningType, char[] word){
        if(type.equals("warning") && warningType.equals("notExist"))
            System.out.println("Cant add weight: Word " + Arrays.toString(word) + " does not exist in data.");
        if(type.equals("warning") && warningType.equals("queryNotFound"))
            System.out.println(Arrays.toString(word) +": query not found in data.");
    }

    public static void list(ArrayList<String> topSuggestion , boolean sizeControl) {
        int size = 5; //[DEFAULT]
        boolean notEnoughSuggestion = topSuggestion.size() < 5;

        if (sizeControl || notEnoughSuggestion)
            size = topSuggestion.size();

        for(int i =0; i<size; i++)
            System.out.println("["+ (i + 1) +"] ->" + topSuggestion.get(i));
    }

    static void printError(int errorValue) {
        System.out.println("[Alert] Wrong Selection : '" + errorValue + "'");
        System.out.println("[Update] Please Try Again :");
    }

    static void printError(String errorValue) {
        System.out.println("[Alert] Wrong Selection : '" + errorValue + "'");
        System.out.println("[Update] Type your selection again : ");
    }

    static void printFeatures() {
        System.out.println("\n\n[Message]Feature List: \n[1] All Suggestion\n[2] LimitedSuggestion\n[3] LimitedSuggestion with Limited Length\n[4] Logout\n[5] Exit");
    }

    static void printQuestion(int value) {
        if (value == 1) {
            System.out.println("\n[Message] Type the word you are looking for: ");
        }
        if (value == 2)
            System.out.println("\n[Message] Chosen the suggestion you looking for: ");
        if (value == 3)
            System.out.print("\n[Message] Do want to stay with your feature preference?\n" +
                    "[Message] Press (1) to stay and (2) to get back to main features\n" +
                    "Type Here: ");
        if (value == 4)
            System.out.print("\nEnter your userName or create One typing(0 + newUserName).\n" +
                    "Type Here: ");
    }

    static void printInf(String whatFor) {
        if (whatFor.equals("features"))
            System.out.println("\nFeature Discription:\n" +
                    "[1] All suggestions : Gives all suggestion for a specific query.\n" +
                    "[2] LimitedSuggestion : Gives Limited no. of suggestion. Best part" +
                    "is you can prioritize your selection for future.\nOrder of suggestion is Alphabetical" +
                    "can be updated according to priorities\n" +
                    "[3] LimitedSuggestion with LL : Gives limited no. of suggestions with controlled length. Ability" +
                    " of prioritization.\nOrder of suggestion will be alphabetical and according to your query length.");
        else
            System.out.print("Wrong value 'whatFor'");
    }

    static void printActiveProcess(int value, String id) {
        if (value == 1)
            System.out.print("[Update] All Suggestion Activated");
        if (value == 2)
            System.out.print("[Update] Limited Suggestion Activated");
        if (value == 3)
            System.out.print("[Update] Limited Suggestion with LL Activated");
        if (value == 4)
            System.out.print("[Update] Logging out id:" + id);
        if (value == 5)
            System.out.print("[Update] Exiting...");
    }
}
