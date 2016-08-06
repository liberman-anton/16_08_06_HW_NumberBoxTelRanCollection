package tel_ran.numbers.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import tel_ran.numbers.implementation.*;
import tel_ran.numbers.interfaces.INumbersBox;

public class NumbersBoxTest {
Integer [] arrayBox={2,10,5,40,2};
Integer [] arrayOther={5,40,70,80};
Integer [] expectedOne={10,5,40};
Integer [] expectedAll={10,5,40};
Integer [] expectedOdd={5};
Integer [] expectedUnion={2,10,5,40,70,80};
Integer [] expectedIntersection={5,40};
Integer [] expectedNoRepeated={2,10,5,40};
Integer [] expectedSubtract={2,10};
Integer [] expectedInRange={10,5};
INumbersBox other;
INumbersBox box;
	@Before
	public void setUp() throws Exception {
		//box=new NumbersBoxArrayList();
		//box=new NumbersBoxLinkedList();
		box=new NumbersBoxTreeSet();
		//box=new NumbersBoxHashSet();
		
		other=new NumbersBoxLinkedList();
		fillBox(box,arrayBox);
		fillBox(other,arrayOther);
	}

	private void fillBox(INumbersBox numbersBox, Integer[] array) {
		for(int number:array)
			numbersBox.addNumber(number);
		
	}

	@Test
	public void testRemoveOne() {
		
		box.removeNumber(2);
		box.removeNumber(2);
		
		testArray(expectedOne);
	}
	private void testArray(Integer[] expected) {
		LinkedList<Integer> list=new LinkedList<>();
		for(int number:box)
			list.add(number);
		Arrays.sort(expected);
		Integer [] actual=list.toArray(new Integer[list.size()]);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		
	}

	@Test
	public void testRemoveAll() {
		box.removeAllNumbers(2);
		testArray(expectedAll);
	}
	@Test
	public void testFind(){
		
		box=box.findNumbers(t->t%2==1);
		testArray(expectedOdd);
	}
	@Test
	 public void testRemovePredicate(){
		
		box.removeAllNumbers(t->t%2==0);
		
		
		testArray(expectedOdd);
	}
	@Test
	public void testUnion(){
		box.union(other);
		box.removeRepeated();
		testArray(expectedUnion);
	}
	@Test
	public void testSubtract() {
		box.subtract(other);
		testArray(expectedSubtract);
	}
	@Test
	public void testRemoveRepeated() {
		box.removeRepeated();
		testArray(expectedNoRepeated);
	}
	@Test
	public void testIntersection() {
		box.intersection(other);
		testArray(expectedIntersection);
	}
	@Test
	public void testNumbersInRange(){
		box=box.getNumbersInRange(5, 10);
		testArray(expectedInRange);
	}

}
