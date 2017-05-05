package com.sda.collection;

import java.io.Serializable;

public class ArrayList implements IList, Serializable, Cloneable {
    private int size = 0;
    public static final int DEFAULT_SIZE = 10;
    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int defaultSize) {
        data = new Object[defaultSize];
    }

    public void add(Object value) {
        if (size >= data.length) {
            resize();
        }
        data[size] = value;
        size++;
    }

    public void add(Object value, int index) {
        if (index > size) {
            throw new IllegalArgumentException();
        }

        Object[] temp = new Object[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            if (i < index) {
                temp[i] = data[i];
            }
            else if (i == index) {
                temp[i] = value;
            }
            else {
                temp[i + 1] = data[i];
            }
        }
        data = temp;
        size++;
    }

    private void resize() {
        Object[] temp = new Object[data.length + DEFAULT_SIZE];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        return data[index];
    }

    @Override
    public int getIndex(Object value) {
        return 0;
    }

    public boolean remove(int index) {
        size--;
        boolean result = false;
        Object[] tmp = new Object[size];
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (i == index) {
                result = true;
                j++;
            }
            tmp[i] = data[j];
        }
        data = tmp;
        return result;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            stringBuilder
                    .append(data[i])
                    .append((i == size - 1) ? "]" : ",");
        }

        return stringBuilder.toString();
    }

    public boolean remove(Object value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return remove(i);
            }
        }
        return false;
    }

}
