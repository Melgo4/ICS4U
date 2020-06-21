package stack;

import java.util.*;

public class BasicStack<E> extends AbstractList<E> {

	protected Object[] stack;
	protected int size;

	public BasicStack() {
		this(8);
	}

	public BasicStack(int initialCapacity) {
		if (initialCapacity > 0) {
			this.stack = new Object[initialCapacity];
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}

	@Override
	public E get(int index) {
		return (E)this.stack[index];
	}

	@Override
	public int size() {
		return this.size;
	}

	public E peek() {
		return this.get(this.size - 1);
	}

	public E pop() {
		E e = this.peek();
		this.stack[--this.size] = null; //Let the GC take care of it.
		return e;
	}

	public E push(E e) {
		this.ensureCapacity();
		this.stack[this.size++] = e;
		return e;
	}

	public int search(E e) {
		if(e == null) {
			for(int i = this.size - 1; i >= 0; i--) {
				if(this.stack[i] == null)return this.size - i - 1;
			}
		} else {
			for(int i = this.size - 1; i >= 0; i--) {
				if(e.equals(this.stack[i]))return this.size - i - 1;
			}
		}

		return -1;
	}

	@Override
	public E remove(int index) {
		E e = this.get(index);

		for(int i = index + 1; i < this.size; i++) {
			this.stack[i - 1] = this.stack[i];
		}

		this.stack[--this.size] = null;
		return e;
	}

	private void ensureCapacity() {
		if(this.size == this.stack.length) {
			int newLength = this.stack.length << 1;

			if(newLength - (Integer.MAX_VALUE - 8) > 0) {
				throw new OutOfMemoryError(); //Size overflow
			}

			this.stack = Arrays.copyOf(this.stack, newLength);
		}
	}

}
