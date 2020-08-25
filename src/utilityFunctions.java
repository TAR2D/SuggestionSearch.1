public class utilityFunctions {
    public static String charToString(char[] word) {
        StringBuilder temp = new StringBuilder();
        for (char k : word) {
            temp.append(k);
        }
        return temp.toString();
    }
}
