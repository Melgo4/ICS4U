package minesweeper.data.entry;

public class IntEntry extends Entry<Integer> {

    public IntEntry() {
        this(0);
    }

    public IntEntry(Integer initialValue) {
        super(initialValue);
    }

    @Override
    public String serialize() {
        return String.valueOf(this.get());
    }

    @Override
    public void deserialize(String raw) {
        this.set(Integer.parseInt(raw));
    }

}
