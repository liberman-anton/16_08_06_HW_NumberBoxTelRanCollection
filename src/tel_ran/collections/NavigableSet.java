package tel_ran.collections;

public interface NavigableSet<E> extends Set<E> {

	NavigableSet <E> subSet(E from, boolean inclusiveFrom, E to, boolean inclusiveTo);
}
