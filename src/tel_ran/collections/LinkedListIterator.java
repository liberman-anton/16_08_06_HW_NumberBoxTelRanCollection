package tel_ran.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class LinkedListIterator<E> implements Iterator<E> {

	LinkedList list;
	LinkedList.NodeList current; 
	LinkedList.NodeList head;
	boolean headNotReturn = true;
	
	public LinkedListIterator(LinkedList list) {
		super();
		this.list = list;
		this.current = this.head = list.getHead();
	}
	
	@Override
	public boolean hasNext() {
		if(current != null){
			if(current.equals(head) && headNotReturn && current.next == null)return true;
			return current.next != null;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		E res;
		if(current.equals(head)&& current.next == null && headNotReturn){
			res = (E)current.data;
			headNotReturn = false;
			return res;
		}
		if(!hasNext())
			throw new NoSuchElementException();
		if(current.prev != null || !headNotReturn){
				current = current.next;
				res = (E)current.data;
		}
		else{			
			res = (E) current.data;
			headNotReturn = false;
		}
		return res;
	}
	
	public void remove(){
		if(current.prev != null){
			if(current.next != null){
				removeNode();
			}else{
				removeTail();
			}
		}else{
			removeHead();
		}
	}

	private void removeHead() {
		if(current.next != null){
			current.next.prev = null;
			this.head = current.next;
			this.list.setHead(current.next);
		} 
		else {
			current = null;
			this.list.setHead(null);
		}
		
	}

	private void removeTail() {
		current.prev.next = null;
		this.list.setTail(current.prev);
	}

	private void removeNode() {
		current.prev.next = current.next;
		current.next.prev = current.prev;
	}

}
