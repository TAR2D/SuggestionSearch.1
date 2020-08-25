import java.util.Arrays;

public class User {
    private String name;
    private String country;
    private int age;
    int values;
    private String[] cache = new String[10];

    public User(String newName) {
        name = newName;
        //country = newCountry;
        //age = newAge;
        System.out.println("New user created :" + newName);
    }

    public String toString() {
        return "Name:" + name + "\nCountry: " + country +
                "\nAge: " + age + "\nHistory: " + Arrays.toString(cache);
    }

    public String getName() {
        return name;
    }

    public void insertHist(String data){
        cache[values] = data;
        values++;
    }

    public boolean cacheFull(){
        return values == cache.length;
    }
}
