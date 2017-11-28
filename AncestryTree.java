//*******************************************************************
//  AncestryTree.java       @author Takis Metaxas
//  Defines an interface appropriate for a biological ancestry tree.
//  NOTE that Ancestral tree has terms that do not match CS tree terms
//  so we use distinctive terms for Ancestry (e.g., Offspring).
//  Paternal ancestors are on the left, Maternal ancestors on the right
//  This tree should NOT contain duplicate elements
//*******************************************************************
import java.util.Iterator;

public interface AncestryTree<T> extends Iterable<T> {
  // Create a 1-argument constructor that sets the root of a new tree
  
  //  Returns the element stored in the root (aka CoTU) of the tree.
  public T getCoTU(); //Center of The Universe
  
  //  Returns the member that is the offspring of target.
  //  Returns null as the offspring of the root.
  public T getOffspring(T target); // This is "parent" in tree terminology
  
  //  Returns the member that is the left child of target.
  //  Returns null if the left child does not exist.
  public T getPater(T target); // Paternal ancestors are on the left
  
  //  Sets the left child of the tree target to lchild.
  //  It throws an exception if target is not already in the tree
  public void setPater(T target, T lchild) throws Exception;
  
  //  Returns the element that is the right child of target.
  //  Returns null if the right child does not exist
  public T getMater(T target); // Maternal ancestors are on the right
  
  //  Sets the right child of target to rchild.
  //  It throws an exception if target is not already in the tree
  public void setMater(T target, T rchild) throws Exception;
  
  //  Returns true if the tree contains an element that
  //  matches the specified target element and false otherwise.
  public boolean appears (T target);
    
  // Returns true if the two members share a grandchild,
  // and false if they are not or if a shared grandchild does not exist
  // Two grandparents that share a grandchild are "inLaws"
  public boolean inLaws(T gp1, T gp2);
    
  //  Returns the number of members in this ancestral tree.
  public int size();
  
  //  Returns the string representation of the binary tree,
  // one line per level.
  public String toString();
    
  //  Returns an iterator that contains a level-order traversal
  // on the ancestral tree.
  public Iterator<T> iterator();
}
