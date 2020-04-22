package minesweeper;

import com.sun.org.apache.xml.internal.utils.XMLString;
import minesweeper.data.ISerializable;
import minesweeper.data.entry.Entry;
import minesweeper.data.entry.EnumEntry;
import minesweeper.data.entry.IntEntry;
import minesweeper.data.entry.LiteralEntry;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Pattern;

public class GameEntry implements ISerializable<String>, Comparable<GameEntry> {

	private List<Entry<?>> entries = new ArrayList<>();

	private LiteralEntry name = new LiteralEntry();
	private IntEntry time = new IntEntry();
	private EnumEntry<GameLevel> level = new EnumEntry<>(GameLevel.BEGINNER);

	private String serializationCache = null;
	private boolean dirty = true;

	public GameEntry() {
		this.entries.add(this.name);
		this.entries.add(this.time);
		this.entries.add(this.level);
	}

	public GameEntry(String name, int time, GameLevel level) {
		this.name.set(name);
		this.time.set(time);
		this.level.set(level);
	}

	public String getName() {
		return this.name.get();
	}

	public GameLevel getLevel() {
		return (GameLevel)this.level.get();
	}

	@Override
	public String serialize() {
		if(!this.dirty && this.serializationCache != null)return this.serializationCache;

		StringBuilder sb = new StringBuilder();
		Iterator<Entry<?>> iterator = this.entries.iterator();

		while(iterator.hasNext()) {
			Entry<?> e = iterator.next();
			sb.append(e.serialize());

			if(iterator.hasNext()) {
				sb.append(",");
			}
		}

		this.dirty = false;
		this.serializationCache = sb.toString();
		return this.serializationCache;
	}

	@Override
	public void deserialize(String raw) throws InvalidParameterException {
		String[] data = Arrays.stream(raw.split(Pattern.quote(","))).map(String::trim).toArray(String[]::new);

		if(data.length != this.entries.size()) {
			System.err.format("Malformed line [%s]-- was expecting %d argument%s, found %d.\n",
					raw, data.length, data.length > 1 ? "s" : "", this.entries.size());

			throw new InvalidParameterException("Malformed entries");
		}

		for(int i = 0; i < data.length; i++) {
			this.entries.get(i).deserialize(data[i]);
		}
	}

	@Override
	public int compareTo(GameEntry o) {
		//Sort by time.
		int signum = this.time.get().compareTo(o.time.get());

		if(signum == 0) {
			//If time is equal, sort by level.
			signum = this.level.get().compareTo((GameLevel)o.level.get());

			//If level is equal, sort by name.
			if(signum == 0) {
				signum = this.name.get().compareTo(o.name.get());
			}
		}

		return signum;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof GameEntry))return false;
		GameEntry gameEntry = (GameEntry)o;
		return Objects.equals(this.name, gameEntry.name) &&
				Objects.equals(this.level, gameEntry.level);
	}

	@Override
	public String toString() {
		return this.serialize();
	}

}
