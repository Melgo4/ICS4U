package employeet.data.entry;

public class FloatEntry extends Entry<Float> {

    public FloatEntry() {
        this(0.0F);
    }

    public FloatEntry(Float initialValue) {
        super(initialValue);
    }

    @Override
    public String serialize() {
        return String.valueOf(this.get());
    }

    @Override
    public void deserialize(String raw) {
        this.set(Float.parseFloat(raw.trim()));
    }

}
