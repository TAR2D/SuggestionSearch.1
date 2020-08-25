import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    char c;
    int weight;
    Map<Character, TrieNode> children;
    boolean isWord = false;

    public TrieNode(){
        c = 0;
        weight = 0;
    }

    public TrieNode(char c, int weight){
        this.c = c;
        this.weight = weight;
    }

    public boolean hasChildren(char c){
        return (children != null && children.containsKey(c));
    }

    public TrieNode getChildren(char c){
        if(!hasChildren(c)) return null;
        return children.get(c);
    }

    public void addChildren(TrieNode node){
        if(children == null) children = new HashMap<Character, TrieNode>();
        if(!hasChildren(node.c)) children.put(node.c,node);
    }

    public boolean canDelete(){
        if( children == null || children.size() == 0)
            return true;
        return false;
    }

    @Override
    public String toString()
    {
        if(children != null) return c + (isWord?"." + weight:"") + "->[" + children.values() + "]";
        return c + "." + weight;
    }
}
