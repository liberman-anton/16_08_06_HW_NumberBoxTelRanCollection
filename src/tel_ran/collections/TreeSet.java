package tel_ran.collections;

import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("hiding")
public class TreeSet<E extends Comparable<E>> implements NavigableSet<E> {

	class NodeTree<E extends Comparable<E>>{
		E data;
		NodeTree<E> parent;
		NodeTree<E> left;
		NodeTree<E> right;
	}

	private NodeTree<E> root;
	private NodeTree<E> current;
	private NodeTree<E> parent;
	
	public NodeTree<E> getRoot() {
		return root;
	}

	public void setRoot(NodeTree<E> root) {
		this.root = root;
	}

	@Override
	public void add(E data) {
		NodeTree<E> newNode = new NodeTree<>();
		newNode.data = data;
		
		NodeTree<E> current = root;
		NodeTree<E> parent = null;
		if(root == null){
			root = newNode;
			return;
		}
		int resCompare = 0;
		while(current != null){
			parent = current;
			resCompare = compareTreeNodes(current, newNode);
			if(resCompare == 0)
				return;
			current=resCompare>0?current.left:current.right;
		}
		if(resCompare > 0)
			parent.left = newNode;
		else
			parent.right =  newNode;
		newNode.parent = parent;
	}

	public int compareTreeNodes(NodeTree<E> current, NodeTree<E> newNode) {
		
		return current.data.compareTo(newNode.data);
	}

	@Override
	public void remove(E number) {
		current = root;
		NodeTree<E> pattern = new NodeTree<E>();
		pattern.data = number;
		while(current != null){
			int resCompare = compareTreeNodes(current,pattern);
			if(resCompare == 0){
				removeNode();
				return;
			}
			current = resCompare > 0 ? current.left : current.right;
		}
	}

	private void removeNode() {
		parent = current.parent;
		NodeTree<E> currentRemoved = removeRootCurrent();
		if(parent!= null){
			if(compareTreeNodes(parent, currentRemoved) > 0)
				parent.left = current;
			else 
				parent.right = current;
		}
	}

	private NodeTree<E> removeRootCurrent(/*NodeTree<E> root*/) {
		NodeTree<E> res = current;
		if(current.left == null && current.right == null){
			current = null;
			return res;
		}
		if(current.left != null){
			NodeTree<E> rightLastNotNull = goRightUntilNull(current.left);
			rightLastNotNull.right = current.right;
			current = current.left;
			current.parent = parent; 
			if(parent == null) root = current;
		} else{
				NodeTree<E> leftLastNotNull = goLeftUntilNull(current.right);
				leftLastNotNull.left = current.left;
				current = current.right;
				current.parent = parent;
				if(parent == null) root = current;
		
		}
		return res;
	}
	
	public NodeTree<E> goLeftUntilNull(NodeTree<E> current){
		NodeTree<E> res = current;
		while(res.left != null){
			res = res.left; 
		}
		return res;
	}
	
	public NodeTree<E> goRightUntilNull(NodeTree<E> current){
		NodeTree<E> res = current;
		while(res.right != null){
			res = res.right; 
		}
		return res;
	}
	
	@Override
	public void retainAll(Collection<Integer> collection) {
		Iterator<Integer> it = (Iterator<Integer>) this.iterator();
		while(it.hasNext()){
			if(!collection.contains(it.next()))
				it.remove();
		}
	}

	@Override
	public void removeAll(Collection<Integer> collection) {
		Iterator<Integer> it = collection.iterator();
		while(it.hasNext()){
			this.remove((E) it.next());
		}
	}

	@Override
	public boolean contains(E data) {
		TreeSet<E>.NodeTree<E> current = root;
		NodeTree<E> pattern = new NodeTree<E>();
		pattern.data = data;
		while(current != null){
			int resCompare = compareTreeNodes(current,pattern);
			if(resCompare == 0)
				return true;
			current = resCompare > 0 ? current.left : current.right;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new TreeSetIterator<E>(this);
		// начинать с наименьшего элемента
		//1.First element mostleft element in tree --- left while != null
		//2.next наименьший елимент среди больших
		//  next - least element among the graters ones
		// если есть право, то шаг в право а потом всегда влево
		// если нет, то ищем первый парент который больше нашего
		// if right === null then looking for first parent greater then current
		// if right != null see 1
		// ставим карент нал 
		// TODO Auto-generated method stub
		return it;
	}

	@Override
	public NavigableSet<E> subSet(E from, boolean inclusiveFrom, E to, boolean inclusiveTo) {
		NavigableSet<E> resNaviSet = new TreeSet<E>();
		Integer f = (Integer)from;
		Integer too = (Integer)to;
		Predicate<Integer> predicate = (t -> t>=f && t<=too);
		for(E number : this){
			if(predicate.test((Integer) number))
				resNaviSet.add(number);				
		}
		return resNaviSet;
	}

}
