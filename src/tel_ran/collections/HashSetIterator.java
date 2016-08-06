package tel_ran.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSetIterator<E> implements Iterator<E> {
	
	LinkedList<E>[] array;
	int index = 0;
	Iterator<E> itCurrentList;
	
	public HashSetIterator(LinkedList<E>[] array) {
		this.array = array;
		for(int i = 0; i < array.length; i++){
			if(array[i] != null){
				index = i;
				//current = array[i].getHead();
				itCurrentList = new LinkedListIterator<E>(array[i]);
				if(itCurrentList.hasNext()) break;
			}	
		}
	}

	@Override
	public boolean hasNext() {
		if(itCurrentList.hasNext())
			return true;
		int ind = index + 1;
		while(ind < array.length){
			if(array[ind] != null)
				return true;
			ind++;
		}
		return false;

	}

	@Override
	public E next() {
		if(!hasNext())
			throw new NoSuchElementException();
		E res = null;
		if(itCurrentList.hasNext()){
			res = itCurrentList.next();
			return res;
		}
		index++;
		while(index < array.length){
			if(array[index] != null){
				itCurrentList = array[index].iterator();
				if(itCurrentList.hasNext()){
					res = itCurrentList.next();
					return res;
				}
			}
			index++;	
		}

		return res;
	}
	
	public void remove(){
		itCurrentList.remove();
		if(array[index].getHead() == null)
			array[index] = null;
	}
}
