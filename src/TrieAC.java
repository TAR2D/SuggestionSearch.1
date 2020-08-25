import java.util.ArrayList;
import java.util.Arrays;

public class TrieAC {
    TrieNode root;
    private final ArrayList<String> topSuggestion = new ArrayList<>();

    public TrieAC() {
        root = new TrieNode();
    }

    public void insert(char[] word) {
        TrieNode current = root;

        for (char c : word) {
            if (current.hasChildren(c))
                current = current.getChildren(c);
            else {
                TrieNode newNode = new TrieNode(c, 0);
                current.addChildren(newNode);
                current = newNode;
            }
        }
        current.isWord = true;
    }

    public void insertWeight(char[] word) {
        if (find(word) != null)
            find(word).weight++;
        else printData.message("warning", "notExist", word);
    }

    public TrieNode find(char[] word) {
        TrieNode current = root;
        for (char c : word) {
            if (current.hasChildren(c)) {
                current = current.getChildren(c);
            } else {
                return null;
            }
        }
        return current;
    }

    public boolean delete(char[] word) {
        return delete(word, root, 0);
    }

    public boolean delete(char[] word, TrieNode node, int wIndex) {
        //when wIndex reaches last second character of word.
        if (wIndex == word.length - 1) {
            if (!node.hasChildren(word[wIndex])) {
                printData.message("warning", "noChild", word[wIndex], word);
                return false;
            }

            //clear word
            TrieNode current = node.getChildren(word[wIndex]);
            current.isWord = false;
            current.weight = 0;
            printData.message("wordRemoved", word);

            if (current.canDelete()) {
                node.children.remove(word[wIndex]);
                printData.message("deletion", word[wIndex], word);
                return true;
            }

        } else {
            if (!node.hasChildren(word[wIndex])) {
                printData.message("warning", "noChild", word[wIndex], word);
                return false;
            }

            boolean canDelete = delete(word, node.getChildren(word[wIndex]), ++wIndex);

            if (canDelete) {
                TrieNode current = node.getChildren(word[--wIndex]);
                if (current.children.size() > 1 || current.isWord) {
                    printData.message("warning", "multipleChild", current.c, word);
                    return false;
                }
                node.children.remove(word[wIndex]);
                printData.message("deletion", word[wIndex], word);
                return true;
            }
        }
        return false;
    }

    private void printDataRec(TrieNode current, StringBuilder word, int level) {
        if (current.isWord) {
            word.delete(level, word.length());
            topSuggestion.add(word.toString());
        }
        char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'
                , 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char c : values) {
            if (current.hasChildren(c)) {
                word.insert(level, c);
                printDataRec(current.getChildren(c), word, level + 1);
            }
        }
    }

    private void printDataRecLimitedLength(TrieNode current, StringBuilder word, int level, int lengthLimit) {
        if (lengthLimit > 0) {
            if (current.isWord) {
                word.delete(level, word.length());
                topSuggestion.add(word.toString());
            }
            char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'
                    , 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            for (char c : values) {
                if (current.hasChildren(c)) {
                    word.insert(level, c);
                    printDataRecLimitedLength(current.getChildren(c), word, level + 1, lengthLimit - 1);
                }
            }
        }
    }

    public void printAllData() {
        StringBuilder build = new StringBuilder();
        printDataRec(root, build, 0);
        printData.list(topSuggestion,true);
    }

    private void autoComplete(TrieNode current, char[] query, int feature) {
        TrieNode temp = current;
        for (char c : query) {
            if (temp.getChildren(c) == null) {
                printData.message("warning", "queryNotFound", query);
                return;
            } else
                temp = temp.getChildren(c);
        }
        if (temp.isWord)
            printData.message("isWord", query);

        StringBuilder build = new StringBuilder(utilityFunctions.charToString(query));
        if (feature == 1 || feature == 2)
            printDataRec(temp, build, query.length);
        else if (feature == 3)
            printDataRecLimitedLength(temp, build, query.length, 3);
    }

    public void autoCompleteHelper(char[] query, int feature) {
        autoComplete(root, query, feature);
    }

    public ArrayList<String> getTopSuggestion(){
        return topSuggestion;
    }

    public void prioritise(boolean max) {
        int size = 5; //[DEFAULT]
        boolean notEnoughSuggestion = topSuggestion.size() < 5;

        if (max || notEnoughSuggestion)
            size = topSuggestion.size();

        PQLinkedList priorSuggestion = new PQLinkedList();

        for (int i = 0; i < size; i++) {
            String word = topSuggestion.get(i);
            int weight = find(topSuggestion.get(i).toCharArray()).weight;
            priorSuggestion.insert(weight, word);
        }
        priorSuggestion.updateList(topSuggestion);
    }
}
