public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(char[] word, int id) {
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
        current.id = id;
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
            if(!node.hasChildren(word[wIndex]))
                return false;

            TrieNode w = node.getChildren(word[wIndex]);
            w.isWord = false;
            w.id = 0;

            if (w.canDelete()) {
                node.children.remove(word[wIndex]);
                System.out.println(word[wIndex] + " Removed with id: " + w.id);
                return true;
            }

        } else {
            if (!node.hasChildren(word[wIndex]))
                return false;

            boolean canDelete = delete(word, node.getChildren(word[wIndex]), ++wIndex);

            if (canDelete) {
                TrieNode c = node.getChildren(word[--wIndex]);
                if (c.children.size() > 1 || c.isWord)
                    return false;
                node.children.remove(word[wIndex]);
                System.out.println(word[wIndex] + " Removed with id: " + node.id);
                return true;
            }
        }
        return false;
    }
}
