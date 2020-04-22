package minesweeper.data.entry;

import minesweeper.data.ISerializable;

import java.util.Objects;

public abstract class Entry<T> implements ISerializable<String> {

    private T value;

    public Entry(T initialValue) {
        this.value = initialValue;
    }

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)return true;
        if(!(o instanceof Entry))return false;
        Entry<?> entry = (Entry<?>)o;
        return Objects.equals(this.value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

}
