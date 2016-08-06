package tel_ran.numbers.interfaces;

import java.util.function.Predicate;

public interface INumbersBox extends Iterable<Integer> {
void addNumber(int number);
void removeNumber(int number);
INumbersBox findNumbers(Predicate<Integer> predicate);
void removeAllNumbers(int number);
void removeAllNumbers(Predicate<Integer> predicate);
/**
 * this numbers box will add all numbers from the given numbers box except those 
 * already existed in this
 * @param numbers
 */
void union(INumbersBox numbers);
/**
 * this numbers box will contain only those numbers that exist in the given one
 * example: this- {2,5,7} ; the given - {5, 3, 10,20}, after the method call 
 * this will contain just 5
 * {2,2,2,2,5} - {2,10} = {2,2,2,2}
 * {2,2,2,2,5} - {2,5} = {2,2,2,2,5} 
 * @param numbers
 */
void intersection(INumbersBox numbers);

/**
 * 
 * this numbers box will contain all unrepeated numbers except those existing in the given 
 * example: this - {2,2,5,5,7,7} , the given {5,7} - result {2}
 * @param numbers
 */
void subtract (INumbersBox numbers);
void removeRepeated();
/**
 * 
 * @param min
 * @param max
 * @return number box with numbers >=min and <=max
 */
INumbersBox getNumbersInRange(int min, int max);









}
