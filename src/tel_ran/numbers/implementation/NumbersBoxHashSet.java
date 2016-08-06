package tel_ran.numbers.implementation;

import tel_ran.collections.HashSet;

import tel_ran.numbers.interfaces.INumbersBox;

public class NumbersBoxHashSet extends NumbersBoxSet {
public  NumbersBoxHashSet() {
	numbers=new HashSet<Integer>();
}
	@Override
	protected INumbersBox getInstanceBox() {
		
		return new NumbersBoxHashSet();
	}

}
