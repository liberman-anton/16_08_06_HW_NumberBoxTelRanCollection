package tel_ran.collections;

import java.util.Iterator;



public class HashSet<E> implements Set<E> {
	LinkedList<E>[] array;
	int capacity=10;
	int size = 0;

	public HashSet(){
		super();
		array = new LinkedList[capacity];
	}
	
	public HashSet(int capacity){
		super();
		this.capacity = capacity;
		array = new LinkedList[capacity];
	}
	
	@Override
	public Iterator<E> iterator() {
		//TODO hasNext, Next, Remove!!!
		Iterator<E> it = new HashSetIterator<E>(array);
		return it;
	}

	@Override
	public void add(E number) {
		if((double) size / capacity > (double) 3/4){
			increaseCapacity();
		}
		int index = number.hashCode() % capacity;
		if(array[index] != null){
			if(!array[index].contains(number)){
				array[index].add(number);
				size++;
			}
		}
		else {
			array[index] = new LinkedList<E>();
			array[index].add(number);
			size++;
		}
	}

	private void increaseCapacity() {
		capacity *= 2;
		LinkedList<E> []tmp = new LinkedList[capacity];
		for(int i = 0; i < capacity/2; i++){
			if(array[i] != null ){
				for(Object number : array[i]){
					int ind = array[i].hashCode()%capacity;
					if(tmp[ind] != null)
						tmp[ind].add((E)number);
					else {
						tmp[ind] = new LinkedList<E>();
						tmp[ind].add((E)number);
					}
				}
			}
		}
		array = tmp;
	}

	@Override
	public void remove(E number) {
		int i = number.hashCode() % capacity;
		if(array[i] != null){
			//if(array[i].contains(number))
				array[i].remove(number);
			if(array[i].getHead() == null)
				array[i] = null;
		}	
	}

	@Override
	public void retainAll(Collection<Integer> collection) {
		Iterator<E> it = this.iterator();//new HashSetIterator<E>(array);
		while(it.hasNext()){
			if(!collection.contains((Integer)(it.next())))
				it.remove();
		}
	}

	@Override
	public void removeAll(Collection<Integer> collection) {
		Iterator<Integer> it = collection.iterator();
		while(it.hasNext()){
			this.remove((E) it.next());
		}
		
	}

	@Override
	public boolean contains(E number) {
		int i = number.hashCode() % capacity;
			if(array[i] != null){
				if(array[i].contains(number)){
					return true;
				}
			}	
		return false;
	}

}
