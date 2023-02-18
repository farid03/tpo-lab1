import java.util.*;

public class HashMap<K extends Comparable<K>, V> {

    public HashMap(int maxSize) {
        this.map = new ArrayList<>();
        for (int i = 0; i < maxSize; ++i) {
            this.map.add(new LinkedList<>());
        }
        this.size = 0;
    }

    public boolean insert(K key, V value) {
        int idx = key.hashCode() % map.size();
        if (map.get(idx).stream()
                .anyMatch(el -> el.key.compareTo(key) == 0)) {
            return false;
        }

        map.get(idx).add(new Pair<>(key, value));
        size++;
        resize();
        return true;
    }

    public boolean erase(K key) {
        int idx = key.hashCode() % map.size();

        if (map.get(idx).removeIf(el -> el.key.compareTo(key) == 0)) {
            size--;
            return true;
        }

        return false;
    }

    public Optional<V> find(K key) {
        int idx = key.hashCode() % map.size();
        return map.get(idx).stream()
                .filter(el -> el.key.compareTo(key) == 0)
                .map(el -> el.value)
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
                newMap.get(kv.key.hashCode() % newSize)
                        .add(kv);
            }
        }

        map = newMap;
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

