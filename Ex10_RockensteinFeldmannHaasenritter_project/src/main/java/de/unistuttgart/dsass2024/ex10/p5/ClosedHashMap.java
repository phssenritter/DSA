package de.unistuttgart.dsass2024.ex10.p5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realizes a basic hash map with closed hashing and quadratic probing. The hash
 * map is not expanded automatically on overflow. It extends
 * {@link AbstractHashMap} and uses an array of {@link KeyValuePair} as basic
 * data structure.
 *
 * @param <V> the type of the values to be added to the HashMap
 */
public class ClosedHashMap<V> extends AbstractHashMap<V> {

    public static final int DEFAULT_SIZE = 23;

    /**
     * Initializes a ClosedHashMap with <code>DEFAULT_SIZE</code>
     * @throws IllegalArgumentException
     */
    public ClosedHashMap() throws IllegalArgumentException {
        this(DEFAULT_SIZE);
    }

    /**
     * Initializes a ClosedHashMap with the defined size.
     * Size must be a prime congruent 3 mod 4.
     *
     * @param size the size of the map
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public ClosedHashMap(int size) throws IllegalArgumentException {

    }

    public Iterator<KeyValuePair<V>> iterator() {
        return new Iterator<AbstractHashMap.KeyValuePair<V>>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                int i = index;
                while (i < map.length && !result) {
                    result = (map[i] != null);
                    i++;
                }
                return result;
            }

            @Override
            public KeyValuePair<V> next() throws NoSuchElementException {
                int i = index;
                KeyValuePair<V> result = null;
                while (i < map.length && map[i] == null) {
                    index++;
                    i = index;
                }
                if (index >= map.length) {
                    throw new NoSuchElementException("No such element!");
                }
                result = map[i];
                index++;
                return result;
            }
        };
    }

}