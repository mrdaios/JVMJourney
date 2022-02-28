package org.mrdaios.ijvm.runtime;

import java.util.NoSuchElementException;

public class SlotsStack<T> {
    private final T[] buffer;
    private int end = 0;

    public SlotsStack(int size) {
        buffer = (T[]) new Object[size];
    }

    public void push(T entity) throws IllegalArgumentException {
        this.push(entity, 1);
    }

    public void push(T entity, int size) throws IllegalArgumentException {
        if (size <= 0 || end + size > buffer.length) {
            throw new IllegalArgumentException("invalid entity size " + size);
        }
        buffer[end] = entity;
        for (int i = 0; i < size; i++) {
            buffer[end + i] = null;
        }
        end += size;
    }

    public T pop() throws NoSuchElementException {
        while (end > 0) {
            end--;
            T entity = buffer[end];
            if (entity != null) {
                buffer[end] = null;
                return entity;
            }
        }
        throw new NoSuchElementException();
    }

    public T pick() {
        int end = this.end;
        while (end > 0) {
            end--;
            T entity = buffer[end];
            if (null != entity) {
                return entity;
            }
        }
        return null;
    }
}
