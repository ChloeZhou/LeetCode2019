import java.util.Iterator;

/**
 * Created by keyingzhou on 1/10/18.
 */
    class PeekingIterator implements Iterator<Integer> {
        Iterator<Integer> iterator;
        Integer next;
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            next = null;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (next == null) {
                next = iterator.next();
            }
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (next == null) {
                next = iterator.next();
            }
            Integer res = next;
            next = null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || next != null;
        }
    }
