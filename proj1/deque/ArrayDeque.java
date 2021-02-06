package deque;

import java.util.Iterator;

public class ArrayDeque<Glorp> implements Iterable<Glorp>, Deque<Glorp> {
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

/**public boolean isEmpty() {
 *      return size == 0;
 *  }
 */
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

    public Iterator<Glorp> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<Glorp> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;

        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public Glorp next() {
            Glorp returnItem = get(wizPos);
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
        ArrayDeque<Glorp> other = (ArrayDeque<Glorp>) o;
        if (this.size() != other.size()) { return false; }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
       for (int i = 0; i < 10; i++) {
           L.addFirst(1);
       }

        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            L1.addFirst(1);
        }

        System.out.println(L.equals(L1));


        }

}






