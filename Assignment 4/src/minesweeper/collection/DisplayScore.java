package minesweeper.collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class DisplayScore<E extends Comparable<E>> extends AbstractList<E> implements List<E> {

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	protected Object[] elements;
	protected int size;

	public DisplayScore() {
		this(16);
	}

	public DisplayScore(int initialSize) {
		if(initialSize < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + initialSize);
		}

		this.elements = new Object[initialSize];
	}

	/**
	 * Applies an insertion sort based on the Comparable each time an element is added.
	 * */
	@Override
	public boolean add(E t) {
		int insertionIndex = 0;

		for(int i = 0; i < this.size(); i++, insertionIndex++) {
			E e = this.get(i);
			int signum = e.compareTo(t);

			if(signum >= 0) {
				break;
			}
		}

		this.add(insertionIndex, t);
		return true;
	}

	public void add(int index, E element) {
		this.rangeCheck(index);
		this.ensureExplicitCapacity(size + 1);
		System.arraycopy(this.elements, index, this.elements, index + 1, size - index);
		this.elements[index] = element;
		this.size++;
	}

	@Override
	public E get(int index) {
		this.rangeCheck(index);
		return (E)this.elements[index];
	}

	@Override
	public E remove(int index) {
		E oldValue = this.get(index);

		int steps = size - index - 1;

		if(steps > 0) {
			System.arraycopy(this.elements, index + 1, this.elements, index, steps);
		}

		this.elements[--this.size] = null;
		return oldValue;
	}

	@Override
	public boolean remove(Object o) {
		if(o == null) {
			for(int i = this.size() - 1; i >= 0; i--) {
				if(this.get(i) == null) {
					this.remove(i);
					return true;
				}
			}
		} else {
			for(int i = this.size() - 1; i >= 0; i--) {
				if(this.get(i).equals(o)) {
					this.remove(i);
					return true;
				}
			}
		}

		return false;
	}

	private void rangeCheck(int index) {
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	private void ensureExplicitCapacity(int capacity) {
		if(capacity - this.elements.length > 0) {
			this.grow(capacity);
		}
	}

	private void grow(int capacity) {
		int oldCapacity = this.elements.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);

		if(newCapacity - capacity < 0) {
			newCapacity = capacity;
		}

		if(newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = this.rebound(capacity);
		}

		this.elements = Arrays.copyOf(this.elements, newCapacity);
	}

	private int rebound(int capacity) {
		if(capacity < 0)throw new OutOfMemoryError();
		return capacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

}
