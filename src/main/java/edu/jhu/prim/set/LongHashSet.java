package edu.jhu.prim.set;

import edu.jhu.prim.iter.LongArrayIter;
import edu.jhu.prim.iter.LongIter;
import edu.jhu.prim.map.LongDoubleHashMap;

/**
 * Hash set for long primitives.
 * @author mgormley
 */
public class LongHashSet implements LongSet {
    
    private static final long serialVersionUID = 1L;
    private LongDoubleHashMap map;
    
    public LongHashSet() {
        this.map = new LongDoubleHashMap();
    }
    
    public LongHashSet(int expectedSize) {
        this.map = new LongDoubleHashMap(expectedSize);
    }
    
    public LongHashSet(LongHashSet other) {
        this.map = new LongDoubleHashMap(other.map);
    }

    public static LongHashSet fromArray(long... keys) {
        LongHashSet set = new LongHashSet();
        set.add(keys);
        return set;
    }

    public void add(long... keys) {
        for (long key : keys) {
            this.add(key);
        }
    }
    
    public void add(long key) {
        map.put(key, 1);
    }

    public boolean contains(long key) {
        return map.contains(key);
    }

    public LongIter iterator() {
        return new LongArrayIter(map.getIndices());
    }
    
}
