/**
 * Created by keyingzhou on 2/18/20.
 */
import java.util.*;
interface Dict {
    public Integer get (int idx);
}

class DictionaryImpl implements Dict {
    List<Integer> list = new ArrayList<Integer>();
    @Override
    public Integer get (int idx) {
        if (!list.isEmpty()) {
            return list.get(idx);
        }
        return null;
    }

}


public class AbstractTest {

}
