package tel_ran.numbers.implementation;

import tel_ran.numbers.interfaces.INumbersBox;

public abstract class NumbersBoxSet extends NumbersBoxCollection {

	@Override
	public void removeAllNumbers(int number) {
		numbers.remove(number);

	}

	@Override
	public void union(INumbersBox numbers) {
		for(Integer number:numbers){
				this.numbers.add(number);
		}

	}

	@Override
	public void removeRepeated() {
		

	}

	

}
