package employeet.data.entry;

import employeet.data.ISerializable;

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

}
