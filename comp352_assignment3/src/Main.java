// COMP352 ASSIGNMENT 3
// AUTHOR : ARASH SHAFIEE
// CLASS Main

public class Main {
    public static void main(String[] args) {
        testBasicOperations();
        testBasicOperations();
        testToggleOperations();
        testRemovalOperations();
        testKeyValueModifications();
        testEdgeCases();
    }

    private static void testBasicOperations() {
        System.out.println("Testing Basic Operations");
        SPQ<Integer, String> spq = new SPQ<>(HeapState.MIN);

        spq.insert(10, "ten");
        spq.insert(5, "five");
        spq.insert(15, "fifteen");

        System.out.println("Top elemenet : " + spq.top());
        System.out.println("Heap size : " + spq.getSize());
    }

    private static void testToggleOperations() {
        System.out.println("Testing Toggle Operations : ");
        SPQ<Integer, String> spq = new SPQ<>(HeapState.MIN);
        spq.insert(10, "ten");
        spq.insert(5, "five");
        spq.insert(15, "fifteen");
        System.out.println("before toggle : " + spq.top());
        spq.toggle();
        System.out.println("after toggle : " + spq.top());

    }

    private static void testRemovalOperations() {
        System.out.println("Testing Removal Operations : ");
        SPQ<Integer, String> spq = new SPQ<>(HeapState.MIN);
        Entry<Integer, String> e1 = spq.insert(10, "ten");
        Entry<Integer, String> e2 = spq.insert(5, "five");
        Entry<Integer, String> e3 = spq.insert(15, "fifteen");

        System.out.println("removing top element : " + spq.removeTop());
        System.out.println("New top element : " + spq.top());

        System.out.println("removing specfic entry (15): " + spq.remove(e3));
        System.out.println("Heap Size : " + spq.getSize());

    }

    private static void testKeyValueModifications() {
        System.out.println("Testing KeyValue Modifications : ");
        SPQ<Integer, String> spq = new SPQ<>(HeapState.MIN);

        Entry<Integer, String> e1 = spq.insert(10, "ten");
        Entry<Integer, String> e2 = spq.insert(5, "five");

        System.out.println("Before modification : " + e1);
        spq.replaceKey(e1, 3);
        System.out.println("After modification : " + e1);

        spq.replaceValue(e2, "Updated five");
        System.out.println("After modifying value" + e2);

    }

    private static void testEdgeCases() {
        System.out.println("Testing Edge Cases : ");
        SPQ<Integer, String> spq = new SPQ<>(HeapState.MIN);

        try {
            System.out.println("Attempting to remove from an empty heap");
            spq.removeTop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        spq.insert(10, "ten");
        spq.insert(10, "another ten");

        System.out.println("Heap with duplicate keys: ");
        while (!spq.isEmpty()) {
            System.out.println(spq.removeTop());
        }

        System.out.println("Heap is empty: " + spq.isEmpty());
    }
}