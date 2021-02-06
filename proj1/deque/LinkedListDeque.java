package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
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


/** public boolean isEmpty() {
 *      return size == 0;
 * }
*/
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

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) { return false; }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(3);
        L.addFirst(4);
        L.printDeque();

        for (int i: L){
            System.out.println(i);
        }

        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        L1.addFirst(2);
        L1.addLast(1);
        L1.addFirst(4);
        L1.addLast(3);
        System.out.println(L.equals(L1));

    }
}
