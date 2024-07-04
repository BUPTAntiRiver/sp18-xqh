public class LinkedListDeque<T> implements Deque<T>{
	private Node<T> sentinel;
	private int size;

	public static class Node<T>{
		public T item;
		public Node<T> next;
		public Node<T> prev;
		public Node(){
			next = null;
			prev = null;
		}
	}

	public LinkedListDeque(){
		sentinel = new Node<>();
		sentinel.next = null;
		sentinel.prev = sentinel;
		size = 0;
	}

	@Override
	public void addFirst(T item){
		size += 1;

		Node<T> p = new Node<>();
		p.item = item;
		p.next = sentinel.next;
		sentinel.next = p;
		p.prev = sentinel;
		if(p.next == null){
			sentinel.prev = p;
		}
	}

	@Override
	public void addLast(T item){
		size += 1;

		Node<T> p = new Node<>();
		p.item = item;
		p.prev = sentinel.prev;
		sentinel.prev.next = p;
		sentinel.prev = p;
		if(sentinel.next == null){
			sentinel.next = p;
		}
	}

	@Override
	public boolean isEmpty(){
		return size == 0;
	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void printDeque(){
		int i;
		Node<T> p = sentinel.next;
		for(i = 0; i < size; i += 1){
			System.out.print(p.item);
			System.out.print(' ');
			p = p.next;
		}
	}

	@Override
	public T removeFirst(){
		Node<T> p = sentinel.next;
		T res = p.item;
		sentinel.next = p.next;
		if(size == 1){
			sentinel.prev = sentinel;
		}
		size -= 1;
		p = null;
		return res;
	}

	@Override
	public T removeLast(){
		Node<T> p = sentinel.prev;
		T res = p.item;
		p.prev.next = null;
		sentinel.prev = p.prev;
		p = null;
		return res;
	}

	@Override
	public T get(int index){
		Node<T> p = sentinel.next;
		for(int i = 0; i < index; i += 1){
			p = p.next;
		}
		return p.item;
	}

	public T getRecursive(int index){
		if(index == 0){
			return sentinel.next.item;
		}
		return getRecursive(index - 1);
	}
}