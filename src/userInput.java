import java.util.Scanner;

public class userInput {
    User DefaultUser;

    public boolean userValidate() {
        boolean isWord;
        Scanner userInputWord = new Scanner(System.in);//Take user response
        printData.printQuestion(4);
        String key = "";
        do {
            if (userInputWord.hasNext() && !userInputWord.hasNextInt()) {
                key = userInputWord.next(); //Store user input as String
                key = key.replaceAll("[*1-9]", "");//Remove integers from string
                isWord = true;
            } else {
                System.out.println("[Alert] Invalid Character! ->" + userInputWord.next() + "<-");
                isWord = false;
            }
        } while (!isWord);
        DefaultUser = userSecurity.validUser(key);
        return (DefaultUser != null);
    }

    public int featureChoice() {
        boolean isInteger;//validating user input if its a integer
        int selection = 0;
        printData.printFeatures();
        System.out.println("\n[Message] Type Here : ");
        Scanner read = new Scanner(System.in);
        do {

            if (read.hasNextInt()) {
                selection = read.nextInt();

                if (!(selection < 0 || selection > 5)) {
                    isInteger = true;
                } else {
                    printData.printError(selection);
                    isInteger = false;
                }
            } else {
                printData.printError(read.next());
                isInteger = false;
            }
        } while (!isInteger);
        printData.printActiveProcess(selection, DefaultUser.getName());//printing info based on selection
        return selection;
    }

    //L E V E L : 2 (USER INPUT)

    /**
     * Search Input: Receive input from user and provide suggestion according to their
     * selected feature.
     *
     * @param chosenFeature : selected feature by user.
     */
    public void searchInput(int chosenFeature) {
        boolean isWord;

        printData.printQuestion(1);
        Scanner userInputWord = new Scanner(System.in);//Take user response
        String key = "";
        do {
            if (userInputWord.hasNext() && !userInputWord.hasNextInt()) {
                key = userInputWord.next(); //Store user input as String
                key = key.replaceAll("[*0-9]", "");//Remove integers from string
                isWord = true;
            } else {
                System.out.println("[Alert] Invalid Character! ->" + userInputWord.next() + "<-");
                isWord = false;
            }
            main.autoComplete(key, chosenFeature, true);
        } while (!isWord);
    }

    //L E V E L : 3 (USER INPUT)
    public void userSelectionAndPrioritization() {
        int options = main.getSuggestionSize();
        boolean isInteger;
        if (!(options == 0)) {
            printData.printQuestion(2);

            int chosenSuggestion = 0;
            System.out.print("[Message] Selection : ");
            Scanner userInputChoice = new Scanner(System.in);//Take user response
            do {
                if (userInputChoice.hasNextInt()) {
                    chosenSuggestion = userInputChoice.nextInt();//store user input as integer
                    if ((chosenSuggestion > 0 && chosenSuggestion <= options + 1)) { //+1 is for not found
                        isInteger = true;
                    } else {
                        printData.printError(chosenSuggestion);
                        isInteger = false;
                    }
                } else {
                    printData.printError(userInputChoice.next());
                    isInteger = false;
                    //userInputChoice.next();
                }
            } while (!isInteger);
            //------------------------------------------------------------------------------
            DefaultUser.insertHist(main.getChooseSuggestion(chosenSuggestion));
            main.update(chosenSuggestion);//update search information with priorities
            //------------------------------------------------------------------------------
            //dataStorage.fileContentUpdater(dictionary.top5Suggestions(chosenSuggestion));//Update data to cache.txt TODO
            //update(dictionary, 1);//update first suggestion
        }
    }
}
