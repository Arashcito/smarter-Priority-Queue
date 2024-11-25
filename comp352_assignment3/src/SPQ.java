// COMP352 ASSIGNMENT 3
// AUTHOR : ARASH SHAFIEE
// CLASS SPQ

import java.util.NoSuchElementException;


public class SPQ<K extends Comparable<K>, V> {
    private Entry<K,V>[] heap;
    private int size;
    private HeapState state;
    private static final int DEFAULT_CAPACITY = 10;

    public SPQ(HeapState initialState) {
        heap = (Entry<K,V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
        this.state = initialState;

    }


    public void toggle() {
        state = (state == HeapState.MAX) ? HeapState.MAX : HeapState.MIN;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            downheap(i);
        }
    }



    public Entry<K,V> remove(Entry<K,V> entry) {
        if (entry == null || entry.getIndex() >= size) {
            throw new IllegalArgumentException("Invalid entry");
        }

        int index = entry.getIndex();

        Entry<K,V> result = heap[index];
        heap[index] = heap[size - 1];
        heap[index].setIndex(index);
        heap[size - 1] = null;
        size--;

        if (index < size) {

            upheap(index);
            downheap(index);
        }

        return result;
    }
    public Entry<K,V> top() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return heap[0];
    }
    public Entry<K,V> insert(K key, V value) {
        if (size == heap.length) {
            resize();
        }

        Entry<K,V> newEntry = new Entry<>(key, value, size);
        heap[size] = newEntry;
        upheap(size);
        size++;
        return newEntry;
    }

    public Entry<K,V> removeTop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Entry<K,V> result = heap[0];
        heap[0] = heap[size - 1];
        heap[0].setIndex(0);
        heap[size - 1] = null;
        size--;

        if (!isEmpty()) {
            downheap(0);
        }
        return result;
    }

    public K replaceKey(Entry<K,V> entry, K key) {
        if (entry == null || entry.getIndex() >= size) {
            throw new IllegalArgumentException("Invalid entry");
        }

        K oldKey = entry.getKey();
        entry.setKey(key);

        int index = entry.getIndex();
        if (index > 0 && compare(key, heap[parent(index)].getKey())) {
            upheap(index);
        } else {
            downheap(index);
        }
        return oldKey;
    }


    public V replaceValue(Entry<K,V> entry, V value) {
        if (entry == null || entry.getIndex() >= size) {
            throw new IllegalArgumentException("Invalid entry");
        }

        V oldValue = entry.getValue();
        entry.setValue(value);
        return oldValue;
    }

    public HeapState getState() {
        return state;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize(){
        return size;
    }



    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        Entry<K,V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        heap[i].setIndex(i);
        heap[j].setIndex(j);
    }

    private boolean compare(K key1, K key2) {
        int comparison = key1.compareTo(key2);
        return state == HeapState.MIN ? comparison < 0 : comparison > 0;
    }

    private void downheap(int index) {
        while (true) {
            int left = leftChild(index);
            int right = rightChild(index);
            int smallest = index;

            if (left < size && compare(heap[left].getKey(), heap[smallest].getKey())){
                smallest = left;
            }
            if (right < size && compare(heap[right].getKey(), heap[smallest].getKey())){
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void upheap(int index) {
        while (index > 0) {
            int parent = parent(index);
            if (compare(heap[parent].getKey(), heap[index].getKey())) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void resize() {
        Entry<K, V>[] newHeap = (Entry<K, V>[]) new Entry[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

}
