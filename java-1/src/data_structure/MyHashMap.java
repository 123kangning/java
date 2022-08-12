package data_structure;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    // Hash table is an array with each cell that is a linked list
    LinkedList<Entry<K, V>>[] table;
    // Current hash table capacity. Capacity is a power of 2
    private int capacity;
    // Specify a load factor used in the hash table
    private float loadFactorThreshold;
    // The number of entries in the map
    private int size = 0;

    /**
     * Construct a map with the default capacity and load factor
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity and
     * default load factor
     */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity
     * and load factor
     */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    /**
     * Ensure the hashing is evenly distributed
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    /** Remove all of the entries from this map */
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override
    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        else
            return false;
    }

    @Override
    /** Return true if this map contains the value */
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    if (entry.getValue().equals(value))
                        return true;
            }
        }
        return false;
    }

    @Override
    /** Return a set of entries in the map */
    public java.util.Set<Entry<K, V>> entrySet() {
        java.util.Set<Entry<K, V>> set =
                new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    set.add(entry);
            }
        }

        return set;
    }

    @Override
    /** Return the value that matches the specified key */
    public V get(K key) {
        int index = hash(key.hashCode());
        if (table[index] != null) {
            LinkedList<Entry<K, V>> bucket = table[index];
            for (Entry<K, V> e : bucket) {
                if (e.getKey().equals(key)) {
                    return e.getValue();
                }
            }
        }
        return null;
    }

    @Override
    /** Return true if this map contains no entries */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<Entry<K, V>> list : table) {
            if (list != null) {
                for (Entry<K, V> e : list) {
                    set.add(e.getKey());
                }
            }
        }
        return set;
    }

    @Override
    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        if (get(key) != null) {
            for (LinkedList<Entry<K, V>> entry : table) {
                if (entry != null) {
                    for (Entry<K, V> e : entry) {
                        if (e.getKey().equals(key)) {
                            V oldValue = e.getValue();
                            e.value = value;
                            return oldValue;
                        }
                    }
                }
            }
        }
        if (size >= capacity * loadFactorThreshold) {
            if (capacity >= MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding max capacity");
            }
            rehash();
        }
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }
        table[index].add(new Entry<K, V>(key, value));
        size++;
        return value;
    }

    @Override
    /** Remove the entries for the specified key */
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            for (Entry<K, V> entry : table[bucketIndex]) {
                if (entry.getKey().equals(key)) {
                    table[bucketIndex].remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    /** Return the number of entries in this map */
    public int size() {
        return size;
    }

    @Override
    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    set.add(entry.getValue());
            }
        }

        return set;
    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    /**
     * Return a power of 2 for initialCapacity
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        return capacity;
    }

    /**
     * Remove all entries from each bucket
     */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    /**
     * Rehash the map
     */
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1; // Double capacity
        table = new LinkedList[capacity]; // Create a new hash table
        size = 0; // Reset size to 0

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].size() > 0)
                for (Entry<K, V> entry : table[i])
                    builder.append(entry);
        }

        builder.append("]");
        return builder.toString();
    }
}