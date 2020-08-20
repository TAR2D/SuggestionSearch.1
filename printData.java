import java.util.Arrays;

public class printData {
    public static void message(String type , char removedChar,char[] word){
        if(type.equals("deletion"))
            System.out.println((main.debugMode?removedChar + " Removed from word " + Arrays.toString(word):""));
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
    }

    public static void message(String type, String warningType, char[] word){
        if(type.equals("warning") && warningType.equals("notExist"))
            System.out.println("Cant add weight: Word " + Arrays.toString(word) + " does not exist in data.");
    }
}
