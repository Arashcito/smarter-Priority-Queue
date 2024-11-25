// COMP352 ASSIGNMENT 3
// AUTHOR : ARASH SHAFIEE
// CLASS Entry

public class Entry<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private int index;

    public Entry(K key, V value, int index) {
        this.key = key;
        this.value = value;
        this.index = index;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
