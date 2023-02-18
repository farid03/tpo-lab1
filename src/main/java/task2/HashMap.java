package task2;

import org.apache.commons.math3.util.Pair;

import java.util.*;

public class HashMap<K extends Comparable<K>, V> {
    private ArrayList<LinkedList<Pair<K, V>>> map;
    private int size;

    public HashMap(int maxSize) {
        this.map = new ArrayList<>();
        for (int i = 0; i < maxSize; ++i) {
            this.map.add(new LinkedList<>());
        }
        this.size = 0;
    }

    public int hash(K key) {
        return key.hashCode() % map.size();
    }

    public boolean insert(K key, V value) {
        int idx = hash(key);
        if (map.get(idx).stream()
                .anyMatch(el -> el.getKey().compareTo(key) == 0)) {
            return false;
        }

        map.get(idx).add(new Pair<>(key, value));
        size++;
        resize();
        return true;
    }

    public boolean erase(K key) {
        int idx = hash(key);

        if (map.get(idx).removeIf(el -> el.getKey().compareTo(key) == 0)) {
            size--;
            return true;
        }

        return false;
    }

    public Optional<V> find(K key) {
        int idx = hash(key);
        return map.get(idx).stream()
                .filter(el -> el.getKey().compareTo(key) == 0)
                .map(Pair::getValue)
                .findAny();
    }

    public int size() {
        return size;
    }

    private void resize() {

        if (size < 4 * map.size() / 3) {
            return;
        }

        int newSize = map.size() * 2;
        var newMap = new ArrayList<LinkedList<Pair<K, V>>>();
        for (int i = 0; i < newSize; ++i) {
            newMap.add(new LinkedList<>());
        }

        for (var list : map) {
            for (var kv : list) {
                newMap.get(kv.getKey().hashCode() % newSize)
                        .add(kv);
            }
        }

        map = newMap;
    }
}
