package tel_ran.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tel_ran.collections.TreeSet.NodeTree;

public class TreeSetIterator<E extends Comparable<E>> implements Iterator<E> {
	TreeSet<E> tree;
	TreeSet<E>.NodeTree<E> current;
	TreeSet<E>.NodeTree<E> first;
	//TreeSet<E>.NodeTree<E> root;
	//TreeSet<E>.NodeTree<E> parent;
	boolean firstNotReturn = true;
	
	public TreeSetIterator(TreeSet<E> tree) {
		super();
		this.tree = tree;
		TreeSet<E>.NodeTree<E> root = tree.getRoot();
		if(root.left != null){
			current = tree.goLeftUntilNull(root);
		} else
			current = root;
		first = current;
	}

	@Override
	public boolean hasNext() {
		if(current != null){
			if(tree.compareTreeNodes(first, current) == 0 && firstNotReturn && current.right == null) return true;
			if(tree.compareTreeNodes(tree.getRoot(), current) > 0) return true;
			if(current.right != null) return true;
			if(hasParentGreater(current)) return true;
		}
		return false;
	}

	private boolean hasParentGreater(TreeSet<E>.NodeTree<E> currentP) {
		TreeSet<E>.NodeTree<E> cur = currentP;
		while(currentP.parent != null){
			currentP = currentP.parent;
			if(tree.compareTreeNodes(currentP, cur) > 0) return true;
		}
		return false;
	}

	@Override
	public E next() {
		E res = null;
		if(tree.compareTreeNodes(first, current) == 0 && firstNotReturn){
			res = current.data;
			firstNotReturn = false;
			return res;
		}
		if(!hasNext())
			throw new NoSuchElementException();
		if(current.right != null){
			current = tree.goLeftUntilNull(current.right);
			res = current.data;
			return res;
		}
		else{
			TreeSet<E>.NodeTree<E> cur = current;
			while(current.parent != null){
				current = current.parent;
				if(tree.compareTreeNodes(current, cur) > 0){
					res = current.data;
					return res;
				}
			}
//			if(current.parent != null){
//				current = current.parent;
//				res = current.data;
//			}
//			else{
//				current = current.right;
//				res = current.data;
//			}
		}
		return res;
	}
	
	public void remove() {

		TreeSet<E>.NodeTree<E> currentRemoved = current;
		if(current.left == null && current.right == null){
			if(current.parent != null){
				while(current.parent != null){
					current = current.parent;
					if(tree.compareTreeNodes(current, currentRemoved) > 0){
						break;
					}
				}
				firstNotReturn = true;
				first = current;	
			}
			else
				current = null;
			
		}
		else{
				if(current.right != null){
					TreeSet<E>.NodeTree<E> leftLastNotNull = tree.goLeftUntilNull(current.right);
					if(current.left != null)current.left.parent = leftLastNotNull;
					leftLastNotNull.left = current.left;
					current = leftLastNotNull;
					
				} else{
					if(current.parent != null){
						if(tree.compareTreeNodes(current.parent, currentRemoved) > 0){
							current.parent.left = current.left;
							//current = null;
						} else{
							current.parent.right = current.left;
						}
					
					while(current.parent != null){
						current = current.parent;
						if(tree.compareTreeNodes(current, currentRemoved) > 0){
							break;
						}
					}
					}else{
						current.left.parent = null;
						tree.setRoot(current.left);
						current = null;
					}
				}
				firstNotReturn = true;
				first = current;
				
		}
		if(currentRemoved.parent!= null){
			if(tree.compareTreeNodes(currentRemoved.parent, currentRemoved) > 0){
				currentRemoved.parent.left = currentRemoved.left;
				currentRemoved.left.parent = currentRemoved.parent;
			}
			else {
				currentRemoved.parent.right = currentRemoved.right;
				currentRemoved.right.parent = currentRemoved.parent;
			}
		} else{
			if(currentRemoved.right != null){
				currentRemoved.right.parent = null;
				tree.setRoot(currentRemoved.right);
				
			} else{
				currentRemoved.left.parent = null;
				tree.setRoot(currentRemoved.left);
				
				TreeSet<E>.NodeTree<E> rightLastNotNull = tree.goRightUntilNull(currentRemoved.left);
				rightLastNotNull = currentRemoved.right;
				if(currentRemoved.right != null) currentRemoved.right.parent = rightLastNotNull;
				
			}
			
		}
	}
}