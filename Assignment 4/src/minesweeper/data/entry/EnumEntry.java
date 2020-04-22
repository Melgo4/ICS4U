package minesweeper.data.entry;

public class EnumEntry<T extends Enum<T>> extends Entry<Enum<T>> {

	private Class<T> classType;

	public EnumEntry(Enum<T> initialValue) {
		super(initialValue);
		this.classType = initialValue.getDeclaringClass();
	}

	@Override
	public String serialize() {
		return String.valueOf(this.get().toString());
	}

	@Override
	public void deserialize(String raw) {
		this.set(Enum.valueOf(classType, raw.trim().toUpperCase()));
	}

}
