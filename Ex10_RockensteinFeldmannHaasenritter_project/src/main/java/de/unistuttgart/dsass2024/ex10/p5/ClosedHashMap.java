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
        if (!isPrime(size) || size % 4 != 3) {
            throw new IllegalArgumentException("Size must be a prime number congruent to 3 mod 4.");
        }
        map = (KeyValuePair<V>[]) new KeyValuePair[size];
    }

    @Override
    public V put(int key, V value) throws IllegalStateException {
        int i = 0;
        int h = key % map.length;

        while (i < map.length) {
            int j = (h + (int)Math.pow(-1, i-1) * i * i) % map.length;
            if (j < 0) {
                j += map.length;
            }
            if (map[j] == null) {
                map[j] = new KeyValuePair<>(key, value);
                return null;
            }
            if (map[j].getKey() == key) {
                V oldValue = map[j].getValue();
                map[j] = new KeyValuePair<>(key, value);
                return oldValue;
            }
            i++;
        }
        throw new IllegalStateException("HashMap overflow");
    }

    @Override
    public boolean containsKey(int key) {
        int i = 0;
        int h = key % map.length;

        while (i < map.length) {
            int j = (h + (int)Math.pow(-1, i-1) * i * i) % map.length;
            if (j < 0) {
                j += map.length;
            }
            if (map[j] == null) {
                return false;
            }
            if (map[j].getKey() == key) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public V get(int key) {
        int i = 0;
        int h = key % map.length;

        while (i < map.length) {
            int j = (h + (int)Math.pow(-1, i-1) * i * i) % map.length;
            if (j < 0) {
                j += map.length;
            }
            if (map[j] == null) {
                return null;
            }
            if (map[j].getKey() == key) {
                return map[j].getValue();
            }
            i++;
        }
        return null;
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

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

}