/* DList.java */

package list;


/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
	  
	  return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
	  //empty DList only with sentinel node
	  head = newNode(1,null,null);     //head == null
	  head.prev = head;
	  head.next = head;
	  size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
	  DListNode insertNode = newNode(item, head, head.next);  //<-  ->
	  head.next.prev = insertNode;
	  head.next = insertNode;
	  size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
	  DListNode insertNode = newNode(item, head.prev, head);
	  head.prev.next = insertNode;
	  head.prev = insertNode;
	  size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
	  if(head!=null)
	  return head.next;
	  else return null;
    
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
	  	  if(head!=null)
		  return head.prev;
		  else return null;
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
	  if(node==null) return null;           //node is null
	  else if(node.next == head) return null;
	  else {
		  DListNode current = head;
		  current = current.next;
		  while(current != head) {
			  if(current==node) return current.next;
			  current = current.next;
		  }
		  return null;
	  }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
	  if(node==null) return null;           //node is null
	  else if(node.prev == head) return null;
	  else {
		  DListNode current = head;
		  current = current.next;
		  while(current != head) {
			  if(current==node) return current.prev;
			  current = current.next;
		  }
		  return null;
	  }
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
	  if(node!=null) {   //node != null
		  DListNode insertNode = newNode(item, null, null);
		  DListNode current = head;
		  current = current.next;
		  while(current != head) {
			  if(current==node) {
				  insertNode.prev = current;
				  insertNode.next = current.next;
				  current.next.prev = insertNode;
				  current.next = insertNode;
				  size++;
				  break;
			  }
			  current = current.next;
		  }
	  }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
	  if(node!=null) {   //node != null
		  DListNode insertNode = newNode(item, null, null);
		  DListNode current = head;
		  current = current.next;
		  while(current != head) {
			  if(current==node) {
				  insertNode.prev = current.prev;
				  insertNode.next = current;
				  current.prev.next = insertNode;
				  current.prev = insertNode;
				  size++;
				  break;
			  }
			  current = current.next;
		  }
	  }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
	  if(node != null) {
		  DListNode current = head;   //current
		  DListNode prev = current;   //previous 
		  current = current.next;
		  while(current != head) {     //current ==head
			  if(current==node) {
				  prev = current.prev;
				  current.next.prev = prev;
				  prev.next = current.next;
				  size--;
				  break;
			  }
			  current = current.next;
		  }
	  }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
  
  public static void main(String[] args) {
	  	DList l = new DList();
	    System.out.println("### TESTING insertFront ###\nEmpty list is " + l);
	    
	    l.insertBack(10);
	    System.out.println("\nInserting 10 at back.\nList with 10 is " + l);
	    
	    l.insertFront(9);
	    l.insertBack(11);
	    System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
	
	    l.insertFront(8);
	    System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
	    
	    DListNode back = l.back();
	    DListNode front = l.front();
	    System.out.println("back:" + back.item);
	    System.out.println("front:" + front.item);
	    System.out.println("next front:" + l.next(front).item);
	    System.out.println("before back:" + l.prev(back).item);
	    l.insertAfter(12, back);
	    System.out.println("insert after back:" + l );
	    l.insertBefore(9, front);
	    System.out.println("insert before front:" + l );
	    l.remove(l.next(front));
	    System.out.println("insert before front:" + l );
  }
}