package tel_ran.collections;

public interface List<E> extends Collection<E>{
 void add(int index, E element);
 int indexOf(E element);
 int lastIndexOf(E element);
 E remove(int index);
}
