import java.util.*;

public class StragglingMergeSort {

    public static <K extends Comparable<? super K>, V> void merge(Queue<Map.Entry<K, V>> S1, Queue<Map.Entry<K, V>> S2, Queue<Map.Entry<K, V>> S) {
        while (!S1.isEmpty() && !S2.isEmpty()) {
            Map.Entry<K, V> entry1 = S1.peek();
            Map.Entry<K, V> entry2 = S2.peek();
            int cmp = entry1.getKey().compareTo(entry2.getKey());
            if (cmp <= 0) {
                S.add(S1.poll());
            } else {
                S.add(S2.poll());
            }
        }
        while (!S1.isEmpty()) {
            S.add(S1.poll());
        }
        while (!S2.isEmpty()) {
            S.add(S2.poll());
        }
    }

    public static <K extends Comparable<? super K>, V> void mergeSort(Queue<Map.Entry<K, V>> S) {
        int n = S.size();
        if (n < 2) return; // queue is trivially sorted
        Queue<Map.Entry<K, V>> S1 = new LinkedList<>();
        Queue<Map.Entry<K, V>> S2 = new LinkedList<>();
        while (S1.size() < n/2) {
            S1.add(S.poll());
        }
        while (!S.isEmpty()) {
            S2.add(S.poll());
        }
        mergeSort(S1);
        mergeSort(S2);
        merge(S1, S2, S);
    }

    public static void main(String[] args) {
        // Creating a list of key-value entries (e.g., integers as keys and strings as values)
        List<Map.Entry<Integer, String>> entries = new ArrayList<>();
        entries.add(new AbstractMap.SimpleEntry<>(3, "Three"));
        entries.add(new AbstractMap.SimpleEntry<>(1, "One"));
        entries.add(new AbstractMap.SimpleEntry<>(2, "Two"));
        entries.add(new AbstractMap.SimpleEntry<>(2, "Another Two"));
        entries.add(new AbstractMap.SimpleEntry<>(1, "Another One"));

        // Creating a queue from the list of entries
        Queue<Map.Entry<Integer, String>> queue = new LinkedList<>(entries);

        // Sorting the queue using straggling merge-sort
        mergeSort(queue);

        // Printing the sorted entries
        for (Map.Entry<Integer, String> entry : queue) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
