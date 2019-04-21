import java.util.HashMap;
import java.util.Map;

/**
 * Created by keyingzhou on 1/9/18.
 */
public class TireNode {
    Map<Character, TireNode> children;
    boolean isWord;
    int count;

    public TireNode () {
        children = new HashMap<>();
        isWord = false;
        count = 0;
    }

    public boolean search(String word, TireNode root) {
        //sanity check
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TireNode next = children.get(word.charAt(i));
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.isWord;
    }

    public void insert(String word, TireNode root){
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TireNode next = children.get(word.charAt(i));
            if (next == null) {
                next = new TireNode();
                children.put(word.charAt(i), next);
            }
            //jump to the new node
            cur = next;
            cur.count++;
        }
        cur.isWord = true;
    }

    public boolean delete (String word, TireNode root) {
        TireNode cur = root;
        if (!search(word, root)) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            TireNode next = children.get(word.charAt(i));
            if (next.count == 1) {
                cur.children.remove(word.charAt(i));
                return true;
            }
            next.count--;
            cur = next;
        }
        cur.isWord = false;
        return true;
    }
}
