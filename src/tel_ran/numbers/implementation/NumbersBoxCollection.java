package tel_ran.numbers.implementation;

import tel_ran.collections.*;

import java.util.Iterator;
import java.util.function.Predicate;

import tel_ran.numbers.interfaces.INumbersBox;

public abstract class NumbersBoxCollection implements INumbersBox {
protected Collection<Integer> numbers;
	@Override
	public Iterator<Integer> iterator() {
		return numbers.iterator();
	}

	@Override
	public void addNumber(int number) {
		numbers.add(number);

	}

	@Override
	public void removeNumber(int number) {
		numbers.remove(number);

	}

	@Override
	public INumbersBox findNumbers(Predicate<Integer> predicate) {
		INumbersBox res=getInstanceBox();
		for(Integer number:numbers)
			if(predicate.test(number))
				res.addNumber(number);
		return res;
	}

	

	abstract protected INumbersBox getInstanceBox() ;

	@Override
	public void removeAllNumbers(Predicate<Integer> predicate) {
		Iterator<Integer> it=numbers.iterator();
		while(it.hasNext()){
			if(predicate.test(it.next()))
				it.remove();
		}

	}

	

	@Override
	public void intersection(INumbersBox numbers) {
		Collection<Integer> collection=getCollectionCopy(numbers);
		this.numbers.retainAll(collection);

	}
	private Collection<Integer> getCollectionCopy(INumbersBox numbers) {
		HashSet<Integer> res=new HashSet<Integer>();
		for(Integer number: numbers)
			res.add(number);	
		return res;
	}

	@Override
	public void subtract(INumbersBox numbers) {
		
		Collection<Integer> collection=getCollectionCopy(numbers);
		this.numbers.removeAll(collection);

	}

	

	@Override
	public INumbersBox getNumbersInRange(int min, int max) {
		
		return findNumbers(number->number<=max&&number>=min);
	}

}
