package deque;

public class ArrayDeque<Glorp> {
    private Glorp[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Glorp[]) new Object[8];
        nextFirst = 0;
        nextLast = items.length - 1;
        size = 0;
    }

    private int addOne(int i){
        return (i + 1) % items.length;
    }

    private int subOne(int i) {
        return (i - 1 + items.length) % items.length;
    }

    private void resize(int cap) {
        Glorp[] a = (Glorp[]) new Object[cap];
        int first = subOne(nextFirst);
        for (int i = size - 1; i >= 0; i--) {
            a[i] = items[first];
            first = subOne(first);
        }
        items = a;
        nextFirst = size;
        nextLast = items.length - 1;
    }

    public void addFirst(Glorp item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = addOne(nextFirst);
        size += 1;
    }

    public void addLast(Glorp item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = subOne(nextLast);
        size += 1;
    }

    public void printDeque() {
        int first = subOne(nextFirst);
        while (first != nextLast) {
            System.out.print(items[first] + " ");
            first = subOne(first);
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean isRemove(){
        return items.length >= 16 && size < (items.length / 4);
    }

    public Glorp removeFirst() {
        if (isRemove()) {
            resize(items.length / 2);
        }
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
        }

        nextFirst = subOne(nextFirst);
        Glorp item = items[nextFirst];
        items[nextFirst] = null;
        return item;
    }

    public Glorp removeLast(){
        if (isRemove()) {
            resize(items.length / 2);
        }
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
        }
        nextLast = addOne(nextLast);
        Glorp item = items[nextLast];
        items[nextLast] = null;
        return item;
    }

    public Glorp get(int index) {
        int first = subOne(nextFirst);
        while (index < size && index >= 0){
            if (index == 0) {
                return items[first];
            }
            index -= 1;
            first = subOne(first);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();


        for (int i = 0; i < 10; i++) {
            L.addLast(i);
        }

        for (int i = 0; i < 10; i++){
            L.addFirst(i);
        }
        L.printDeque();

        for (int i = 0; i < 15; i++) {
            L.removeFirst();
        }
        L.printDeque();
        System.out.println(L.get(4));

    }
 }





