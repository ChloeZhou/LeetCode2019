/**
 * Created by keyingzhou on 8/21/18.
 */
public class Commit {
    int id;
    Commit parent;
    public Commit(int id, Commit parent) {
        this.id = id;
        this.parent = parent;
    }
}
