package com.sda.collection;

public interface IList {
    void add(Object value);
    void add(Object value, int index);
    boolean remove (int index);
    boolean remove(Object value);
    int size();
    String toString();
    Object get(int index);
    int getIndex(Object value);
}
