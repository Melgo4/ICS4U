package minesweeper.data;

import java.io.Serializable;

public interface ISerializable<T> extends Serializable {

    T serialize();

    void deserialize(T raw);

}
