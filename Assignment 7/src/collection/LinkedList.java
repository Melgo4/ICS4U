package collection;

import java.util.AbstractList;

public class LinkedList<E extends Comparable<E>> extends AbstractList<E> {

	private Node<E> head;

	protected int size;

	public Node<E> getNode(int index) {
		Node<E> n = this.head;

		for(int i = 0; i < index; i++) {
			n = n.next;
		}

		return n;
	}

	@Override
	public E get(int index) {
		return this.getNode(index).value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean add(E e) {
		Node<E> node = new Node<>(e, null, null);

		for(Node<E> n = this.head; n != null; n = n.next) {
			int s = e.compareTo(n.value);
			if(s > 0)continue;
			else if(s == 0)return false;

			if(n.previous != null) {
				n.previous.next = node;
			}

			node.next = n;
			node.previous = n.previous;

			n.previous = node;

			if(n == this.head) {
				this.head = node;
			}

			this.size++;
			return true;
		}

		if(this.head == null) {
			this.head = node;
		} else {
			Node<E> tail = this.getNode(this.size() - 1);
			tail.next = node;
			node.previous = tail;
		}

		this.size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for(Node<E> n = this.head; n != null; n = n.next) {
			if(!n.value.equals(o))continue;

			if(n.previous != null) {
				n.previous.next = n.next;
			}

			if(n.next != null) {
				n.next.previous = n.previous;
			}

			this.size--;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");

		int m = Math.min(5, this.size());

		for(int i = 0; i < m; i++) {
			sb.append(this.get(i));
			if(i != m - 1)sb.append(", ");
		}

		return sb.append(']').toString();
	}

	public static class Node<E> {
		public final E value;
		public Node<E> previous;
		public Node<E> next;

		public Node(E value, Node<E> previous, Node<E> next) {
			this.value = value;
			this.previous = previous;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node{" + "value=" + this.value + '}';
		}
	}

}
