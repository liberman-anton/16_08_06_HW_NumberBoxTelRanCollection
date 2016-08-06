package tel_ran.numbers.implementation;

import tel_ran.collections.*;

import tel_ran.numbers.interfaces.INumbersBox;

public class NumbersBoxTreeSet extends NumbersBoxSet {
public NumbersBoxTreeSet(){
	numbers=new TreeSet<Integer>();
}
	@Override
	protected INumbersBox getInstanceBox() {
		return new NumbersBoxTreeSet();
	}
	@Override
	public INumbersBox getNumbersInRange(int min, int max){
		NumbersBoxTreeSet result=new NumbersBoxTreeSet();
		NavigableSet<Integer> navigable=((TreeSet<Integer>)numbers).subSet(min, true, max, true);
		result.numbers=navigable;
		return result;
	}

}
