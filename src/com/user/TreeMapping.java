import java.util.Comparator;
//full implementation of a TreeMap using null references for leaf nodes
public class TreeMapping<K, V> {

    private Node root;
    private Comparator<? super K> comparator;

    public TreeMapping() {
        this.root = null;
        this.comparator = null;
    }

    public TreeMapping(Comparator<? super K> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            root = root.put(key, value);
        }
    }

    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node getNode(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = compare(key, curr.key);
            if (cmp < 0) {
                curr = curr.left;
            } else if (cmp > 0) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        } else {
            Comparable<? super K> key = (Comparable<? super K>) k1;
            return key.compareTo(k2);
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node put(K key, V value) {
            int cmp = compare(key, this.key);
            if (cmp < 0) {
                if (left == null) {
                    left = new Node(key, value);
                } else {
                    left = left.put(key, value);
                }
            } else if (cmp > 0) {
                if (right == null) {
                    right = new Node(key, value);
                } else {
                    right = right.put(key, value);
                }
            } else {
                this.value = value;
            }
            return this;
        }

        private int compare(K k1, K k2) {
            if (comparator != null) {
                return comparator.compare(k1, k2);
            } else {
                Comparable<? super K> key = (Comparable<? super K>) k1;
                return key.compareTo(k2);
            }
        }
    }

    public static void main(String[] args) {
        TreeMapping<Integer, String> treeMapping = new TreeMapping<>();
        treeMapping.put(1, "One");
        treeMapping.put(2, "Two");
        treeMapping.put(3, "Three");

        System.out.println("Value for  2: " + treeMapping.get(2));
        System.out.println("Value for  4: " + treeMapping.get(4));
    }
}
