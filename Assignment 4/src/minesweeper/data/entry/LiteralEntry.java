package minesweeper.data.entry;

public class LiteralEntry extends Entry<String> {

    public LiteralEntry() {
        this("");
    }

    public LiteralEntry(String initialValue) {
        super(initialValue);
    }

    @Override
    public String serialize() {
        return this.get();
    }

    @Override
    public void deserialize(String raw) {
        this.set(raw);
    }

}
