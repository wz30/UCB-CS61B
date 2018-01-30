/* Set.java */

import list5.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	DList dlist ;
	int size;
	
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    dlist = new DList();
    size = 0;
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return size;
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
 * @throws InvalidNodeException 
   **/
  public void insert(Comparable c) {
	  ListNode n = dlist.front();
	  if(size == 0) {
		  dlist.insertBack(c);
		  //System.out.println(dlist.toString());
		  size++;
	  } else if(size == 1) {  //size==1
		   try {
		  if(c.compareTo(n.item())>0) {
			  n.insertAfter(c);
		  } else if(c.compareTo(n.item())<0) {
			  n.insertBefore(c);
		  }    size++;} catch (InvalidNodeException e) {
				e.printStackTrace();
			}
	  } else {
		  //System.out.println(dlist.toString());
		  try {
			while(n.next().isValidNode()) {
				if(c.compareTo(n.item())<=0) break; //find the n which is bigger or equal to c
				  n = n.next();
			  }
			//find the node >=c
			if(c.compareTo(n.item())!=0) {
				if(n.next().isValidNode()) n.insertBefore(c);
				else n.insertAfter(c);
				size++;
			}
		} catch (InvalidNodeException e) {
			e.printStackTrace();
		}
		  
	  }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
	  //create the newNode for the Set s
	  ListNode  newNode1 = s.dlist.front();
	  ListNode  newNode2 = this.dlist.front();
	  while(newNode1.isValidNode()) {  //walk through the list
		  try {
			this.insert((Comparable)newNode1.item());
			newNode1 = newNode1.next();
		} catch (InvalidNodeException e) {
			e.printStackTrace();
		}
	  }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
	  ListNode  newNode1 = s.dlist.front(); //Set s
	  ListNode  newNode2 = this.dlist.front();
	  
	  while(newNode2.isValidNode()) {  //walk through the this
		  //System.out.println(dlist);
		  try{
		  newNode1 = s.dlist.front();
		  Comparable c = (Comparable)newNode2.item();
		  while(newNode1.isValidNode()) {
			  if(c.compareTo(newNode1.item())==0) break;
			  newNode1 = newNode1.next();
		  }//while
		  ListNode  newNode3 = newNode2;
		  newNode2 = newNode2.next();
		  if(!newNode1.isValidNode())  {newNode3.remove();size--;}
		  } catch (InvalidNodeException e) {
				e.printStackTrace();
			}
	  }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
	  String result = "[  ";
	    ListNode current =  dlist.front();
	    while (current.isValidNode()) {
	      try {
			result = result + current.item() + "  ";
			current = current.next();
		} catch (InvalidNodeException e) {
			e.printStackTrace();
		}
	  
	    } //while
	    return result + "]";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(1));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}