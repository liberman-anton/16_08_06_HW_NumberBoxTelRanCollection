package tel_ran.collections;

public interface Collection<T> extends Iterable<T>{

	void add(T number);

	void remove(T number);

	void retainAll(Collection<Integer> collection);

	void removeAll(Collection<Integer> collection);

	boolean contains(T number);

}
