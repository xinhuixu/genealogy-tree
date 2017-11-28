/* FILE NAME: GenealogyTree.java
 * WHO: Xinhui Xu
 * WHEN: Fall 2017
 * WHAT: Assignment 8 Task 2
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenealogyTree<T> implements AncestryTree<T>{
    private T[] tree; //computed link implementation of tree
    private static final int DEPTH = 4;
    private static final int DEF_CAP = (int)Math.pow(2, DEPTH) - 1; //default tree capacity
    
    // Create a 1-argument constructor that sets the root of a new tree
    public GenealogyTree(T root){
	tree = (T[])new Object[DEF_CAP];
	tree[0] = root;
    }    
    //  Returns the element stored in the root (aka CoTU) of the tree.
    public T getCoTU(){
	return tree[0]; //Center of The Universe
    }

    //returns index of target in tree array, -1 if not found
    public int getIndex(T target){
	for (int i = 0; i<DEF_CAP; i++){
	    if ((tree[i]!=null) && (tree[i].equals(target))){
		return i;
	    }
	}
	return -1;
    }
    //helpers; given an index (returned by getIndex()), identifies its type.
    public boolean notAMember(int targetIndex){
	return targetIndex == -1;
    }
    public boolean isCoTU(int targetIndex){
	return targetIndex == 0;
    }
    public boolean invalidIndex(int targetIndex){
	return targetIndex >= DEF_CAP;
    }

  //  Returns the member that is the offspring of target.
  //  Returns null as the offspring of the root.
    public T getOffspring(T target){
	int i = getIndex(target);
	
	if (notAMember(i) || isCoTU(i)){
	    return null;
	} else {
	    return tree[(i - 1) / 2];
	    //index of parent is always in bound for i>0	    
	}
    } // This is "parent" in tree terminology
  
  //  Returns the member that is the left child of target.
  //  Returns null if the left child does not exist.
    public T getPater(T target){
	int i = getIndex(target);
	int pater_i = (2 * i) + 1;// Paternal ancestors are on the left

	if (notAMember(i) || invalidIndex(pater_i)){
	    return null; //target is not a member,
	    //or child index out of bound
	} else {
	    return tree[pater_i]; //either null or T
	} 	
    }
  //  Sets the left child of the tree target to lchild.
  //  It throws an exception if target is not already in the tree
    public void setPater(T target, T lchild) throws Exception{
	int i = getIndex(target);
	if (notAMember(i)){
	    throw new Exception("target is not in tree.");
	} else {
	    int pater_i = (2 * i) + 1;
	    if (invalidIndex(pater_i)) {
		System.out.println("tree is full.");
	    } else {
		//		System.out.println("p_i: " + pater_i);
		tree[pater_i] = lchild;
		//System.out.println("New LEFT of " + target + ": "+tree[pater_i]);
	    }
	}
    }
  
  //  Returns the element that is the right child of target.
  //  Returns null if the right child does not exist
    public T getMater(T target){ // Maternal ancestors are on the right
	int i = getIndex(target);
	int mater_i = (2 * i) + 2;
				     

	if (notAMember(i) || invalidIndex(mater_i)){
	    return null; //target is not a member,
	    //or child index out of bound
	} else {
	    return tree[mater_i]; //either null or T
	} 	
    }
  //  Sets the right child of target to rchild.
  //  It throws an exception if target is not already in the tree
    public void setMater(T target, T rchild) throws Exception{
	int i = getIndex(target);
	if (notAMember(i)){
	    throw new Exception("target is not in tree.");
	} else {
	    int mater_i = (2 * i) + 2;
	    if (invalidIndex(mater_i)) {
		System.out.println("tree is full.");
	    } else {
		tree[mater_i] = rchild;
		//		System.out.println("New RIGHT of "+target+": "+tree[mater_i]);
	    }
	}	
    }
  
  //  Returns true if the tree contains an element that
  //  matches the specified target element and false otherwise.
    public boolean appears (T target){
	return !(notAMember(getIndex(target)));
    }
    
  // Returns true if the two members share a grandchild,
  // and false if they are not or if a shared grandchild does not exist
  // Two grandparents that share a grandchild are "inLaws"
    public boolean inLaws(T gp1, T gp2){
	T grandchild1 = null; T grandchild2 = null;
	T child1 = getOffspring(gp1);
	if (child1 == null) { return false; }
	grandchild1 = getOffspring(child1);
	
	T child2 = getOffspring(gp2);
	if (child2 == null) { return false; }
	grandchild2 = getOffspring(child2);

	if ( ((grandchild1 == null) && (grandchild2 == null)) ||
	     (!grandchild1.equals(grandchild2)) ){ //both null or unequal
	    return false;
	} else {
	    return true;
	}
    }
    
  //  Returns the number of members in this ancestral tree.
    public int size(){
	int size = 0;
	for (int i = 0; i<DEF_CAP; i++){
	    if (tree[i] != null)
		size++;
	}
	return size;
    }
  
  //  Returns the string representation of the binary tree,
  // one line per level.
    public String toString(){
	String ret = "My genealogy contains " + size() + " members:\n";
	if (size() == 0){
	    return ret;
	}//size is now at least 1

	ret += /*"0:" +*/ getCoTU() + " \n";
	int i = 1;
	int level = 2; //tracks when to break to newline
	while ( i<DEF_CAP ){
	    
	    if (tree[i] != null) {
		ret += /*i + ":" +*/ tree[i] + " ";
	    }//displays _ for null elements
	    else {
		ret += /*i + ":*/"_ ";
	    }
	    
	    if (i == level){
		ret += "\n";
		level = (level * 2) + 2;
	    }
	    i++;	    
	}
	return ret;
    }

    //learned from https://gist.github.com/jnwhiteh/68d095c630dfcaddffd1
    class treeIterator implements Iterator<T> {
	int i = 0;
	
	public boolean hasNext(){
	    return i < (DEF_CAP - 1); //tree[DEF_CAP - 1] has no next
	}

	public T next(){
	    if (!hasNext()){
		throw new NoSuchElementException();
	    }
	    return tree[i++];
	}

	public void remove(){
	    tree[i] = null;
	}
    }
  //  Returns an iterator that contains a level-order traversal
  // on the ancestral tree.
    public Iterator<T> iterator(){
	return new treeIterator();
    }

    public static void main(String[] args){
	System.out.println("----------BEGIN TESTING----------");
	GenealogyTree<String> t1 = new GenealogyTree<String>("A");
	System.out.println("t1: " + t1);
	System.out.println("t1's Center Of Universe: "+ t1.getCoTU());
	System.out.println("----------Adding more elements-----------");
	try{
	    t1.setPater(t1.getCoTU(), "B");
	    t1.setMater(t1.getCoTU(), "C");
	    t1.setPater("B", "D");
	    t1.setMater("C", "E");
	    t1.setPater("C", "F");
	    t1.setMater("B", "G");
	    t1.setPater("D", "H");
	    t1.setMater("F", "I");
	    t1.setPater("E", "J");
	    t1.setMater("E", "K");
	}
	catch (Exception ex){
	    System.out.println(ex);
	}
	System.out.println("t1: " + t1);
	System.out.println("-----------Testing appears(T )----------");
	System.out.println("Is B in t1? " + t1.appears("B"));
	System.out.println("Is Z in t1? " + t1.appears("Z"));
	System.out.println("-----------Testing getters----------");
	System.out.println("t1.getPater(\"B\"): " + t1.getPater("B"));
	System.out.println("t1.getMater(\"E\"): " + t1.getMater("E"));
	System.out.println("t1.getMater(\"K\"): " + t1.getMater("K"));
	System.out.println("t1.getMater(\"Z\"): " + t1.getMater("Z"));
	System.out.println("----------Testing inLaws----------");
	System.out.println("t1.inLaws(\"D\", \"F\"): "+t1.inLaws("D", "F"));
	System.out.println("t1.inLaws(\"H\", \"K\"): "+t1.inLaws("H", "K"));
	System.out.println("t1.inLaws(\"H\", \"C\"): "+t1.inLaws("H", "C"));
	System.out.println("t1.inLaws(\"Y\", \"Z\"): "+t1.inLaws("Y", "Z"));
    }    
}
   
