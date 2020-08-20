public class TrieAC {
    TrieNode root;

    public TrieAC() {
        root = new TrieNode();
    }

    public void insert(char[] word) {
        TrieNode current = root;

        for (int i = 0; i < word.length; i++) {
            if (current.hasChildren(word[i]))
                current = current.getChildren(word[i]);
            else {
                TrieNode newNode = new TrieNode(word[i], 0);
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
        for (int i = 0; i < word.length; i++) {
            if (current.hasChildren(word[i])) {
                current = current.getChildren(word[i]);
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
            System.out.println(word);
        }
        char[] values = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'
                , 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < values.length; i++) {
            char c = values[i];
            if (current.hasChildren(c)) {
                word.insert(level, c);
                printDataRec(current.getChildren(c), word, level + 1);
            }
        }
    }

    public void printAllData() {
        StringBuilder build = new StringBuilder();
        printDataRec(root, build, 0);
    }

}
