package tel_ran.numbers.implementation;

import tel_ran.collections.LinkedList;

import tel_ran.numbers.interfaces.INumbersBox;

public class NumbersBoxLinkedList extends NumbersBoxList {
public NumbersBoxLinkedList(){
	numbers=new LinkedList<Integer>();
}

@Override
protected INumbersBox getInstanceBox() {
	// TODO Auto-generated method stub
	return new NumbersBoxLinkedList();
}

}
