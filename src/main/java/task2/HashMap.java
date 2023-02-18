package task2;

import java.util.*;

public class HashMap<K extends Comparable<K>, V> {

    public HashMap(int maxSize) {
        this.map = new ArrayList<>();
        for (int i = 0; i < maxSize; ++i) {
            this.map.add(new LinkedList<>());
        }
        this.size = 0;
    }

    public boolean Insert(K key, V value) {
        int idx = key.hashCode() % map.size();
        if (map.get(idx).stream()
                .anyMatch(el -> el.key.compareTo(key) == 0)) {
            return false;
        }

        map.get(idx).add(new Pair<>(key, value));
        size++;
        Resize();
        return true;
    }

    public boolean Erase(K key) {
        int idx = key.hashCode() % map.size();

        if (map.get(idx).removeIf(el -> el.key.compareTo(key) == 0)) {
            size--;
            return true;
        }

        return false;
    }

    public Optional<V> Find(K key) {
        int idx = key.hashCode() % map.size();
        return map.get(idx).stream()
                .filter(el -> el.key.compareTo(key) == 0)
                .map(el -> el.value)
                .findAny();
    }

    public int Size() {
        return size;
    }

    private void Resize() {

        if (size < 4 * map.size() / 3) {
            return;
        }

        int new_size = map.size() * 2;
        var new_map = new ArrayList<LinkedList<Pair<K, V>>>();
        for (int i = 0; i < new_size; ++i) {
            new_map.add(new LinkedList<>());
        }

        for (var list : map) {
            for (var kv : list) {
                new_map.get(kv.key.hashCode() % new_size)
                        .add(kv);
            }
        }

        map = new_map;
    }

    private ArrayList<LinkedList<Pair<K, V>>> map;
    private int size;
}

class Pair<K, V> {
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K key;
    public V value;
}

