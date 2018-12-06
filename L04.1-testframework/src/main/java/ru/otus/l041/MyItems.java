package ru.otus.l041;

import java.util.ArrayList;

class MyItems<E> {
    private ArrayList<E> arrayList;

    MyItems() {
        arrayList = new ArrayList<E>();
    }

    int size() {
        return arrayList.size();
    }

    MyItems<E> add(E e) {
        arrayList.add(e);

        return this;
    }

    MyItems<E> remove(E e) {
        arrayList.remove(e);

        return this;
    }

    boolean isset(E e) {
        return arrayList.contains(e);
    }
}
