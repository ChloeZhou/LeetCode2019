/**
 * Created by keyingzhou on 1/9/18.
 */
public class TireTree {
    TireNode root;
    public TireTree (TireNode root) {
        this.root = root;
    }

    public boolean search (String word) {
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            cur = cur.children.get(word.charAt(i));
            if (cur == null) {
                return false;
            }
        }
        return cur.isWord;
    }

    public boolean insert (String word) {
        //corner case:
        if (search(word)) {
            return false;
        }
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TireNode next = cur.children.get(word.charAt(i));
            if (next == null) {
                next = new TireNode();
                cur.children.put(word.charAt(i), next);
            }
            cur = next;
            cur.count++;
        }
        cur.isWord = true;
        return true;
    }

    public boolean delete (String word) {
        //corner case:
        if (!search(word)) {
            return false;
        }
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TireNode next = cur.children.get(word.charAt(i));
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
