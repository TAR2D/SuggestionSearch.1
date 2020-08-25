
public class userSecurity {

    private static final hashTableLL dataS = new hashTableLL(10000);

    public static User validUser(String userName) {
        if ((userName.charAt(0) != '0')) {
            if (dataS.search(userName)) {
                dataS.printAllData();
                return dataS.getUser(userName);
            } else {
                System.out.println("User Does not  exist!");
            }
        } else {
            userName = userName.replace("0","");
            User newUser = new User(userName);
            dataS.insert(newUser);
            return dataS.getUser(userName);
        }
        return null;
    }

    public static boolean updateUser(String userName,String choosenSuggestion) {
        if (validUser(userName) != null) {
            dataS.getUser(userName).insertHist(choosenSuggestion);
            return true;
        }
        return false;
    }
}
