public class Activate {
    // private final String Password = "qwerty";

    private boolean Active = true;

    /**
     * [MAIN METHOD]
     * Create a file with Trie ADTs. Take user response and use it
     * to give suggestions. Update file according to selected suggestion
     */
    public Activate() {
        System.out.print("**** Welcome to AutoComplete Feature *****");
    }

    /**
     * RUN METHOD: Use to activate the auto complete feature.
     */
    public void run() {
        userInput getIO = new userInput();
        int attempt = 1;

        main.create();//create a dictionary
        //dataStorage.dictionaryContentUpdater(dictionary);//update dictionary data
        printData.printInf("features");

        while ((attempt <= 5)) {
            if (getIO.userValidate())
                while (Active) {
                    int selectedFeature = getIO.featureChoice();
                    getIO.searchInput(selectedFeature);
                    if(selectedFeature != 1)
                    getIO.userSelectionAndPrioritization();
                }
            attempt++;
            if (attempt == 5)
                System.out.println("[Message]: Last attempt..");
        }
    }
}
