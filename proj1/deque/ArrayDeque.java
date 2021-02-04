package deque;
/**
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

    private void resize(int cap) {
        Glorp[] a = (Glorp []) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        nextFirst = size;
        nextLast = items.length - 1;
    }

    public void addFirst(Glorp item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + 1) % items.length;
        size += 1;
    }

    public void addLast(Glorp item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast - 1 + items.length) % items.length;
        size += 1;
    }

    public void printDeque() {

    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(1);
        L.addLast(2);
        L.addFirst(3);
        L.addLast(4);
        L.addFirst(5);
        L.addLast(6);
        L.addLast(7);
        L.addFirst(8);
        L.addLast(9);
        L.addFirst(10);
        L.addFirst(11);

    }
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0 ? true : false);
    }

    public int minusOne(int index) {
        return Math.floorMod(index-1, items.length);
    }


    public int plusOne(int index) {
        return Math.floorMod(index+1, items.length);
    }

    public int plusOne(int index, int length) {
        return Math.floorMod(index+1, length);
    }

    /**
     *  invariants:
     *      设计resize()方法，将在增加ArrayDeaue实例内存的方法中调用
     *      内部判断内存满则调用expand()增加内存
     *        如果内存过小则调用reduce()减小内存
     **/

    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < items.length / 4 && items.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity) {
        T[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i=begin; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    /**
     *  invariants:
     *      通过minusOne()方法确定nextFirst，(nextFirst-1)%items.length
     *      即nextFirst的下一个位置
     *      eg. (0 - 1) % 8 = 7
     *
     * */
    public void addFirst(T item) {
        // resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public T getFirst() {
        return items[plusOne(nextFirst)];
    }

    public T removeFirst() {
        resize();
        T res = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        return res;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public T getLast() {
        return items[minusOne(nextLast)];
    }

    public T removeLast() {
        resize();
        T res = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;
        return res;
    }

    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)) {
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst) + index, items.length);
        return items[index];
    }




    public static void main(String[] args) {
        ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            aq.addLast(i);
        }

        aq.printDeque();
        for (int i = 0; i < 10; i++) {
            aq.addFirst(i);
        }
        aq.printDeque();
        System.out.println(aq.get(0));
    }

}


