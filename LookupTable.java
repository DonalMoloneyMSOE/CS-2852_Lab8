/*
 *CS 2852 - 011
 *Fall 2017
 *Lab * - Morse Code Encoder
 *Name: Donal Moloney
 *Created: 10/25/2017
 */
package Moloneyda;

import java.util.*;

/**
 * This is class creates a simple table and implements a few of the core methods
 */
public class LookupTable<K extends Comparable<? super K>, V> implements Map<K, V> {
    private List<Entry<K, V>> collection;

    /**
     * This is the main method its purpose is to test all the methods of lookup table to ensure
     * that they are working correctly
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 - clear():");
        LookupTable<String, String> test = new LookupTable<String, String>();
        test.put("H", "....");
        test.put("I", "..");
        System.out.println("Size before clearing list = " + test.size());
        test.clear();
        System.out.println("After applying clear() table size is = " + test.size());
        test.put("D", "-..");
        test.put("O", "---");
        test.put("N", "-.");
        test.put("A", ".-");
        test.put("L", ".-..");
        System.out.println("\n");
        System.out.println("Test 2 - put():");
        System.out.println(
                "predicted size after adding 5 elements = 5: Actual size is" + " = " + test.size());
        test.put("D", "-..");
        System.out.println(
                "predicted size after trying to add duplicate elements = 5: Actual size is" + " =" +
                        " " + test.size());
        System.out.println("\n");
        System.out.println("Test 3 - remove():");
        test.remove("L");

        System.out.println(
                "predicted size after removing one element" + " = 4: " + "Actual size = " +
                        test.size());
        System.out.println(
                "prediction collection contains L after calling remove(L) = false, " + "Answer = " +
                        test.containsKey("L"));
        test.remove("L");
        System.out.println(
                "predicted size after trying to remove element thats already been removed" + " = " +
                        "4: Actual size = " + test.size());
        System.out.println("\n");
        System.out.println("Test 4 - empty():");
        System.out.println("Prediction isEmpty is false: actual = " + test.isEmpty());
        test.clear();
        System.out.println(
                "Prediction isEmpty after table.clear() true: actual = " + test.isEmpty());
        System.out.println("\n");
        System.out.println("Test 5 - containKey():");
        test.put("C", "-.-.");
        test.put("A", ".-");
        System.out.println("Test containsKey(C) after put(C, -.-.), predicted = true, actual = " +
                                   test.containsKey("C"));
        System.out.println("Test containsKey(A) after not calling test.put(A, .-), predicted = " +
                                   "false, actual = " + test.containsKey("A"));
        System.out.println("\n");
        System.out.println("Test 6 - get():");
        test.clear();
        test.put("D", "-..");
        test.put("O", "---");
        test.put("N", "-.");
        test.put("A", ".-");
        test.put("L", ".-..");
        System.out.println("Prediction that table.get(D) = -.. , actual = " + test.get("D"));
        System.out.println("Prediction that table.get(O) = --- , actual = " + test.get("O"));
        System.out.println("Prediction that table.get(N) = -. , actual = " + test.get("N"));
        System.out.println("Prediction that table.get(A) = .- , actual = " + test.get("A"));
        System.out.println("Prediction that table.get(L) = .-.. , actual = " + test.get("L"));
        System.out.println("\n");

        System.out.println("Test 7 - size():");
        test.clear();
        test.put("D", "-..");
        test.put("O", "---");
        test.put("N", "-.");
        test.put("A", ".-");
        test.put("L", ".-..");
        System.out.println(
                "Prediction on size after adding 5 elements = 5, actual = " + test.size());
        test.remove("D");
        test.remove("O");
        System.out.println(
                "Prediction on size after removing 2 elements = 3, actual = " + "" + test.size());

    }

    /**
     * This method instantiates the collection by saying ArrayList implements List<Entry<K, V>> collection
     */
    public LookupTable() {
        collection = new ArrayList<Entry<K, V>>();
    }

    /**
     * This method returns the size of the collection
     *
     * @return size of the collection
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * Checks to see if the collection is empty()
     *
     * @return boolean returns true if collection is empty and false if its not
     */
    @Override
    public boolean isEmpty() {
        if (collection.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method sees if the passed in key is located in the collection
     *
     * @param key -  the key you are searching for
     * @return boolean - whether collection contains the key or not
     */
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < collection.size(); i++) {
            Entry<K, V> e = collection.get(i);
            if (e != null) {
                if (e.key.equals(key)) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * This method gets the value of a key
     *
     * @param object the key to get the value of
     * @return the value of the object
     */
    @Override
    public V get(Object object) {
        int compare;
        if (containsKey(object) == true) {
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i) != null) {
                    Entry<K, V> possibleMatch = collection.get(i);
                    compare = possibleMatch.key.compareTo((K) object);
                    if (compare == 0) {
                        return collection.get(i).value;
                    }
                }
            }
        }

        return null;

    }

    /**
     * This method adds entries to the collection
     *
     * @param key   the key to add
     * @param value the corresponding value of a key
     * @return the key you are over writing or null if the key is not overwriting anything
     */
    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new InputMismatchException("Key can not be null");
        }
        int index = Collections.binarySearch(collection, new Entry<K, V>(key, value));
        Entry<K, V> newEntry;
        if (index > 0) {
            V temp = (V) collection.get(index);
            collection.get(index).value = value;
            return temp;
        } else {
            index = (index + 1) * (-1);
            newEntry = new Entry<K, V>(key, value);
            collection.add(index, newEntry);
            return null;
        }
    }

    /**
     * Private inter class that allows for an entries keys to be compared with one another
     */
    private static class Entry<K extends Comparable<? super K>, V>
            implements Comparable<Entry<K, V>> {
        K key;
        V value;

        /**
         * This is the Entry's constructor method
         *
         * @param key   -  the new entry's key
         * @param value - the new entry's value
         */
        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Custom implementation of Entry's compare to method
         *
         * @param o the object that you are comparing to
         * @return numerical value describing what key is larger than the other
         */
        @Override
        public int compareTo(Entry<K, V> o) {
            return this.key.compareTo(o.key);
        }

    }

    /**
     * This method removes an entry from the collection
     *
     * @param key - the object to remove
     * @return the object being removed or null if the object does not exist
     */
    @Override
    public V remove(Object key) {
        boolean contains = containsKey(key);
        if (contains == true) {
            V temp = (V) get(key);
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).key.equals(key)) {
                    collection.remove(i);
                }

            }
            return temp;
        } else {
            return null;
        }

    }

    /**
     * This method clears the contents of the collection
     */
    @Override
    public void clear() {
        collection = new ArrayList<Entry<K, V>>();
    }

    /**
     * This method is an unimplemented table method
     * throws UnsupportedOperationException
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * This method is an unimplemented table method
     * throws UnsupportedOperationException
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * This method is an unimplemented table method
     * throws UnsupportedOperationException
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * This method is an unimplemened table method
     *
     * @param value
     */
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * This method is an unimplemened table method
     *
     * @param m
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
