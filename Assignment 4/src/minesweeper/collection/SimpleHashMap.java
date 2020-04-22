package minesweeper.collection;

import java.util.HashMap;

public class SimpleHashMap<H, V> extends HashMap<H, V> {

	public void putEntries(Entry<H, V>... entries) {
		for(Entry<H, V> entry: entries) {
			this.put(entry.getKey(), entry.getValue());
		}
	}

}
