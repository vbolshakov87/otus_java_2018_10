package ru.otus.l031;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class MyArrayList<E> implements List<E> {
    private E[] arr;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        setEmptyArray();
    }

    private MyArrayList(E[] input, int size) {
        if (size > input.length) {
            throw new IndexOutOfBoundsException("size provided is bigger then array length");
        }
        this.arr = Arrays.copyOf(input, size);
        this.size = size;
        this.capacity = size;
    }

    private void setEmptyArray() {
        capacity = DEFAULT_CAPACITY;
        this.arr = (E[])new Object[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return index(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(arr, size);
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size+1);
        arr[size++] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = index(o);
        if (index < 0) {
            return false;
        }

        return remove(index) != null;
    }

    private int index(Object o) {
        for (int i =0; i < size; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] all = c.toArray();
        for (Object element : all) {
            if (index(element) < 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] addArr = c.toArray();
        int numNew = addArr.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(addArr, 0, arr, size, numNew);
        size += numNew;

        return numNew != 0;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity >= capacity) {
            capacity = minCapacity < DEFAULT_CAPACITY * 100 ? minCapacity * 2 : (int) ((double)minCapacity * 1.2);
            arr = Arrays.copyOf(arr, capacity);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize == 0)
            return false;

        Object[] addArr = c.toArray();
        int i = 0;
        for (Object element : addArr) {
            add(index + i++, (E) element);
        }

        return true;
    }

    /**
     * A version of rangeCheck used by add and addAll.
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return String.format("Index: %d, Size: %d", index, size) ;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] addArr = c.toArray();
        for (Object element : addArr) {
            remove(element);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAll(c);
    }

    @Override
    public void clear() {
        setEmptyArray();
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        return arr[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheckForAdd(index);
        arr[index] = element;

        return get(index);
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size+1);
        if (size - index >= 0) System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheckForAdd(index);
        size--;
        E out = arr[index];
        if (index == size) {
            arr[size] = null;

            return out;
        }

        IntStream.range(index, size).forEach(i -> arr[i] = arr[i + 1]);
        arr[size] = null;

        return out;
    }

    @Override
    public int indexOf(Object o) {
        ListIterator<E> it = listIterator();
        while (it.hasNext()) {
            if (o.equals(it.next())) {
                return it.previousIndex();
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size-1; i >= 0; i--) {
            if (o.equals(arr[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        E[] subarr = Arrays.copyOfRange(arr, fromIndex, toIndex);
        return new MyArrayList<E>(subarr, subarr.length);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(" -> ");
            }
            builder.append(arr[i]);
        }

        return builder.toString();
    }

    /**
     * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
     * elements themselves are not copied.)
     *
     * @return a clone of this <tt>ArrayList</tt> instance
     */
    public Object clone() {
        return new MyArrayList<E>(arr, size);
    }

    @SuppressWarnings("unchecked")
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int size = this.size;
        for (int i=0; i < size; i++) {
            arr[i] = operator.apply((E) arr[i]);
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) arr, 0, size, c);
    }

    private class Itr implements Iterator<E> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public E next() {
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }

            try {
                MyArrayList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public E previous() {
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            try {
                int i = cursor;
                MyArrayList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
