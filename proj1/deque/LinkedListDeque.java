package deque;

public class LinkedListDeque<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node first = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;

    }

    public void addLast(T item) {
        Node last = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node n = sentinel.next;
        while (n.next != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println(n.item);
    }
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first;
        }
        return null;
    }

    public T removeLast() {
        if (sentinel.next != sentinel) {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last;
        }
        return null;
    }

    public T get(int index) {
        int i = 0;
        Node n = sentinel.next;
        while (i <= index && n != sentinel) {
            if (i == index) {
                return n.item;
            }
            i += 1;
            n = n.next;
        }
        return null;
    }

    public T getRecursive(int index) {
        Node n = sentinel.next;
        if (index == 0) {
            return n.item;
        }
        return get_helper(n, index).item;
    }

    private Node get_helper(Node n, int index) {
        if (index == 0) {
            return n;
        }
        return this.get_helper(n.next, index - 1);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(3);
        L.addFirst(4);
        System.out.println(L.isEmpty());
        L.printDeque();
        System.out.println(L.get(2));
        System.out.println(L.getRecursive(3));
    }
}
