/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }
  
  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key){
	  if(root == null){
		  root = new Tree234Node(null,key);
		  size++;
	  }
	  else if(!find(key)){   //avoid the same number to insert
		  Tree234Node cur = findinsPos(key);      //find the position to insert, fun return the Node
		  if(cur.keys<=2){
			  cur.insertKey(key);
			  //
			  split3Node(cur);   //test for 66
		  }
		  else{
			  //for the 3key current node 1.create two new nodes first(cur.parent) 2. insert the key to one of the two new nodes 
			  Tree234Node newNode1 = new Tree234Node(cur.parent,cur.key1);
			  Tree234Node newNode2 = new Tree234Node(cur.parent,cur.key3);  
			  if(key<cur.key1){
				  newNode1.key2 = newNode1.key1;
				  newNode1.key1 = key;
				  newNode1.keys++;
			  }
			  else if(key>cur.key1&&key<cur.key2){
				  newNode1.key2 = key;
				  newNode1.keys++;
			  }
			  else if(key>cur.key2&&key<cur.key3){
				  newNode2.key2 = newNode2.key1;
				  newNode2.key1 = key;
				  newNode2.keys++;
			  }
			  else if(key>cur.key3){
				  newNode2.key2 = key;
				  newNode2.keys++;
			  }
			  Tree234Node node = splitNode(cur,newNode1,newNode2);
			  //
			  split3Node(node);  //test for 80
		  }
	  }
  }
  
  private void split3Node(Tree234Node cur) {
	    cur = cur.parent;
	    while (cur != null) {
	      if (cur.keys == 3) {
	        break;
	      }
	      cur = cur.parent;
	    }
	    if (cur!= null) {
	      Tree234Node newNode1 = new Tree234Node(cur.parent, cur.key1);
	      Tree234Node newNode2 = new Tree234Node(cur.parent, cur.key3);
	      newNode1.child1 = cur.child1;
	      newNode1.child2 = cur.child2;
	      newNode2.child1 = cur.child3;
	      newNode2.child2 = cur.child4;
	      cur.child1.parent = newNode1;
	      cur.child2.parent = newNode1;
	      cur.child3.parent = newNode2;
	      cur.child4.parent = newNode2;
	      splitNode(cur, newNode1, newNode2);
	    }
	  }

  
  Tree234Node splitNode(Tree234Node cur,Tree234Node newNode1,Tree234Node newNode2){
	  while(true){
		  if(cur.parent == null){
			  root = new Tree234Node(null, cur.key2);
			  root.child1 = newNode1;
			  root.child2 = newNode2;
			  newNode1.parent = root;
			  newNode2.parent = root;
			  return root; //?????
		  }
		  else{
			  Tree234Node parent = cur.parent;
			  if(parent.keys == 1){
				  parent.keys++;
				  if(cur.key2 < parent.key1){
					  parent.key2 = parent.key1;
					  parent.key1 = cur.key2;
					  parent.child3 = parent.child2;
					  parent.child2 = newNode2;
					  parent.child1 = newNode1;
				  }
				  else{
					  parent.key2 = cur.key2;
			          parent.child2 = newNode1;
			          parent.child3 = newNode2;
				  }
				  return parent;
			  }
			  else if(parent.keys == 2){
				  parent.keys++;
				  //System.out.println("test for parent = 2");
				  if (cur.key2 < parent.key1) {
			          parent.key3 = parent.key2;
			          parent.key2 = parent.key1;
			          parent.key1 = cur.key2;
			          parent.child4 = parent.child3;
			          parent.child3 = parent.child2;
			          parent.child2 = newNode2;
			          parent.child1 = newNode1;
			        } 
				  else if (cur.key2 > parent.key1 && cur.key2 < parent.key2) {
			          parent.key3 = parent.key2;
			          parent.key2 = cur.key2;
			          parent.child4 = parent.child3;
			          parent.child3 = newNode2;
			          parent.child2 = newNode1;
			        } 
				  else {
					  System.out.println("test for parent = 2");
			          parent.key3 = cur.key2;
			          parent.child3 = newNode1;
			          parent.child4 = newNode2;
			        }
				  return parent;
			  }
			  else if(parent.keys == 3){    //for the 3key current.parent node
				  Tree234Node node1 = new Tree234Node(parent.parent, parent.key1);
				  Tree234Node node2 = new Tree234Node(parent.parent, parent.key3);
				  if(cur.key2<parent.key1){
					  node1.key2 = node1.key1;
					  node1.key1 = cur.key2;
					  node1.keys++;
					  
					  node2.child2 = parent.child4;  //decide the node.child
					  node2.child1 = parent.child3;
					  node1.child3 = parent.child2;
					  node1.child2 = newNode2;
					  node1.child1 = newNode1;
					  
					  newNode1.parent = node1;       //decide the node.parent
					  newNode2.parent = node1;	
					  parent.child2.parent = node1;
					  parent.child3.parent = node2;
					  parent.child4.parent = node2;
				  }
				  else if (cur.key2 > parent.key1 && cur.key2 < parent.key2) {
			          node1.key2 = cur.key2;
			          node1.keys++;
			          node1.child1 = parent.child1;
			          node1.child2 = newNode1;
			          node1.child3 = newNode2;
			          node2.child1 = parent.child3;
			          node2.child2 = parent.child4;
			          parent.child1.parent = node1;
			          newNode1.parent = node1;
			          newNode2.parent = node1;
			          parent.child3.parent = node2;
			          parent.child4.parent = node2;
			        } 
				  else if (cur.key2 > parent.key2 && cur.key2 < parent.key3) {
			          node2.key2 = node2.key1;
			          node2.key1 = cur.key2;
			          node2.keys++;
			          node1.child1 = parent.child1;
			          node1.child2 = parent.child2;
			          node2.child3 = parent.child4;
			          node2.child2 = newNode2;
			          node2.child1 = newNode1;
			          parent.child1.parent = node1;
			          parent.child2.parent = node1;
			          parent.child4.parent = node2;
			          newNode2.parent = node2;
			          newNode1.parent = node2;
			        } 
				  else {
			          node2.key2 = cur.key2;
			          node2.keys++;
			          node1.child1 = parent.child1;
			          node1.child2 = parent.child2;
			          node2.child1 = parent.child3;
			          node2.child2 = newNode1;
			          node2.child3 = newNode2;
			          parent.child1.parent = node1;
			          parent.child2.parent = node1;
			          parent.child3.parent = node2;
			          newNode1.parent = node2;
			          newNode2.parent = node2;
			        }
				  cur = cur.parent;
				  newNode1 = node1;
				  newNode2 = node2;
			  }
		  }
	  }
  }
  
  //find the position to insert, fun return the Node
  Tree234Node findinsPos(int key){
	  Tree234Node cur = root;
	  Tree234Node prev = null;
	  while(cur != null){
		  prev = cur; //tag for the previous (current node)
		  if(key<cur.key1){
			  cur = cur.child1;
		  }
		  else if(cur.keys == 1 || key<cur.key2){
			  cur = cur.child2;
		  }
		  else if(cur.keys == 2 || key<cur.key3){ 
			  cur = cur.child3;
		  }
		  else if(key>cur.key3){
			  cur = cur.child4;
		  }
	  }
	  return prev;
  }
  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert1(int key) {
    // Fill in your solution here.
	  Tree234Node node = root; //??
	  Tree234Node insertNode = new Tree234Node(root,key); //null???
	  if(root==null){
		  root = insertNode;
		  size++;
	  }
	  else{
		while(node!=null){
		  if(node.keys == 1){
			  //System.out.println("test3");
			  if(key<node.key1){
				  if(node.child1 != null) node = node.child1;
				  else if(node.child1 == null){
					  node.key2=node.key1;node.key1=key;
					  node.keys++;
					  node = node.child1;
				  }
			  }
			  else if(key>node.key1){
				  if(node.child2 != null) node = node.child2;
				  else if(node.child2 == null){
					  node.key2=key;
					  node.keys++;
					  node = node.child2;
				  }
			  }
		  }
		  else if(node.keys == 2){
			  if(key<node.key1){
				  if(node.child1 != null) node = node.child1;
				  else if(node.child1 == null){
					  node.key3=node.key2;node.key2=node.key1;node.key1=key;
					  node.keys++;
					  node = node.child1;
				  }
			  }
			  else if(key>node.key1&&key<node.key2){
				  if(node.child2 != null) node = node.child2;
				  else if(node.child2 == null){
					  node.key3=node.key2;node.key2=key;
					  node.keys++;
					  node = node.child2;
				  }
			  }
			  else if(key>node.key2){
				  if(node.child3 != null) node = node.child3;
				  else if(node.child3 == null){
					  node.key3=key;
					  node.keys++;
					  node = node.child3;
				  }
			  }
		  }
		  else if(node.keys == 3){
			 if(node == root&&node.child1==null){
			  Tree234Node insertNode2 = new Tree234Node(node,node.key3);
			  insertNode2.parent = node;
			  Tree234Node insertNode1 = new Tree234Node(node,node.key1);
			  insertNode2.parent = node;
			  
			  node.keys = 1;
			  node.key1 = node.key2;
			  node.child1 = insertNode1;
			  node.child2 = insertNode2;
			  
			  //compare all the keys to decide the position
			  if(key<insertNode1.key1){
				  insertNode1.key2 = insertNode1.key1;
				  insertNode1.key1 = key;
				  insertNode1.keys++;
			  }
			  else if(key>insertNode1.key1&&key<node.key1){
				  insertNode1.key2 = key;
				  insertNode1.keys++;
			  }
			  else if(key<insertNode2.key1&&key>node.key1){
				  insertNode2.key2 = insertNode2.key1;
				  insertNode2.key1 = key;
				  insertNode2.keys++;
			  }
			  else if(key>insertNode2.key1){
				  insertNode2.key2 = key;
				  insertNode2.keys++;
			  }
			 node = null; 
			 } //if(root == node)
			 else if(node == root){
				 if(key<node.key1){
					 node = node.child1;
				 }
				 else if(key>node.key1&&key<node.key2){
					 node = node.child2;
				 }
				 else if(key>node.key2&&key<node.key3){
					 node = node.child3;
				 }
				 else if(key>node.key3){
					 node = node.child4;
				 }
				 
			 }  
			 else{
				 if(node.parent.keys == 1){
					 System.out.println("not root with 1 key");
					//if(node.child1 == null){
					 if(node == node.parent.child1){
						 node.parent.key2 = node.parent.key1;
						 node.parent.key1 = node.key2;
						 node.parent.keys++;
						 
						 node.parent.child3 = node.parent.child2;
						 node.parent.child2 = node.parent.child1;
						 Tree234Node insertNode2 = new Tree234Node(node.parent,node.key1);
						 node.parent.child1 = insertNode2;
						 node.parent.child2.key1 = node.parent.child2.key3;
						 node.parent.child2.keys = 1;
						 
						 System.out.println("key:" + key);
						 if(key < node.parent.child1.key1){
							 node.parent.child1.key2 = node.parent.child1.key1;
							 node.parent.child1.key1 = key;
							 node.parent.child1.keys++;
						 }
						 else if(key > node.parent.child1.key1 && key < node.parent.key1){
							 node.parent.child1.key2 = key;
							 node.parent.child1.keys++;
						 }
						 else if(key < node.parent.child2.key1 && key > node.parent.key1){
							 node.parent.child2.key2 = node.parent.child2.key1;
							 node.parent.child2.key1 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key > node.parent.child2.key1){
							 System.out.println("test5");
							 node.parent.child2.key2 = key;
							 node.parent.child2.keys++;
						 }
					 }
					 else if(node == node.parent.child2){
						 node.parent.key2 = node.key2;
						 node.parent.keys++;
						 
						 Tree234Node insertNode2 = new Tree234Node(node.parent,node.parent.child2.key3);
						 node.parent.child3 = insertNode2;
						 node.parent.child2.keys = 1;
						 
						 if(key < node.parent.child2.key1){
							 node.parent.child2.key2 = node.parent.child2.key1;
							 node.parent.child2.key1 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key > node.parent.child2.key1 && key < node.parent.key1){
							 node.parent.child2.key2 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key < node.parent.child3.key1 && key > node.parent.key1){
							 node.parent.child3.key2 = node.parent.child3.key1;
							 node.parent.child3.key1 = key;
							 node.parent.child3.keys++;
						 }
						 else if(key > node.parent.child3.key1){
							 node.parent.child3.key2 = key;
							 node.parent.child3.keys++;
						 }
					 }
					 node =null;
					//}
					/* else if(key<node.key1) node = node.child1;
					 else if(key<node.key2&&key>node.key1) node = node.child2;
					 else if(key<node.key3&&key>node.key2) node = node.child3;
					 else if(key>node.key3) node = node.child4;*/
				 }//if(keys == 1)
				 else if(node.parent.keys == 2){
					 if(node == node.parent.child1){
						 node.parent.key3 = node.parent.key2;
						 node.parent.key2 = node.parent.key1;
						 node.parent.key1 = node.parent.child1.key2;
						 node.parent.keys++;
						 
						 node.parent.child4 = node.parent.child3;
						 node.parent.child3 = node.parent.child2;
						 node.parent.child2 = node.parent.child1;
						 Tree234Node insertNode2 = new Tree234Node(node.parent,node.key1);
						 node.parent.child1 = insertNode2;
						 node.parent.child2.key1 = node.parent.child2.key3;
						 node.parent.child2.keys = 1;
						 
						 
						 if(key < node.parent.child1.key1){
							 node.parent.child1.key2 = node.parent.child1.key1;
							 node.parent.child1.key1 = key;
							 node.parent.child1.keys++;
						 }
						 else if(key > node.parent.child1.key1 && key < node.parent.key1){
							 node.parent.child1.key2 = key;
							 node.parent.child1.keys++;
						 }
						 else if(key < node.parent.child2.key1 && key > node.parent.key1){
							 node.parent.child2.key2 = node.parent.child2.key1;
							 node.parent.child2.key1 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key > node.parent.child2.key1){
							 node.parent.child2.key2 = key;
							 node.parent.child2.keys++;
						 }
					 }
					 else if(node == node.parent.child2){
						 node.parent.key3 = node.parent.key2;
						 node.parent.key2 = node.parent.child2.key2;
						 node.parent.keys++;
						 
						 node.parent.child4 = node.parent.child3;
						 node.parent.child3 = node.parent.child2;
						 Tree234Node insertNode2 = new Tree234Node(node.parent,node.key1);
						 node.parent.child2 = insertNode2;
						 node.parent.child3.key1 = node.parent.child3.key3;
						 node.parent.child3.keys = 1;
						 
						 
						 if(key < node.parent.child2.key1){
							 node.parent.child2.key2 = node.parent.child2.key1;
							 node.parent.child2.key1 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key > node.parent.child2.key1 && key < node.parent.key2){
							 node.parent.child2.key2 = key;
							 node.parent.child2.keys++;
						 }
						 else if(key < node.parent.child3.key1 && key > node.parent.key2){
							 node.parent.child3.key2 = node.parent.child3.key1;
							 node.parent.child3.key1 = key;
							 node.parent.child3.keys++;
						 }
						 else if(key > node.parent.child3.key1){
							 node.parent.child3.key2 = key;
							 node.parent.child3.keys++;
						 }
					 }
					 else if(node == node.parent.child3){
						 node.parent.key3 = node.parent.child3.key2;
						 node.parent.keys++;

						 
						 node.parent.child4 = node.parent.child3;
						 Tree234Node insertNode2 = new Tree234Node(node.parent,node.key1); //
						 node.parent.child3 = insertNode2;
						 node.parent.child4.key1 = node.parent.child4.key3;
						 node.parent.child4.keys = 1;
						 
						 if(key < node.parent.child3.key1){
							 node.parent.child3.key2 = node.parent.child3.key1;
							 node.parent.child3.key1 = key;
							 node.parent.child3.keys++;
						 }
						 else if(key > node.parent.child3.key1 && key < node.parent.key3){
							 node.parent.child3.key2 = key;
							 node.parent.child3.keys++;
						 }
						 else if(key < node.parent.child4.key1 && key > node.parent.key3){
							 node.parent.child4.key2 = node.parent.child4.key1;
							 node.parent.child4.key1 = key;
							 node.parent.child4.keys++;
						 }
						 else if(key > node.parent.child4.key1){
							 node.parent.child4.key2 = key;
							 node.parent.child4.keys++;
						 }
					 }
					 node = null;
				 }
				 else if(node.parent.keys == 3){
					 System.out.println("test");
					 Tree234Node insertNode1 = new Tree234Node(null, node.parent.key1);
					 				 
					 
					 Tree234Node insertNode2 = new Tree234Node(null, node.parent.key3);
					 
					 
					 
					 if(node == node.parent.child1){
						 System.out.println("test1");
						 insertNode2.child1 = node.parent.child3;
						 insertNode2.child2 = node.parent.child4;
						 insertNode1.child2 = node.parent.child2;
						 
						 insertNode1.child2.parent = insertNode1;
						 insertNode2.child1.parent = insertNode2;
						 insertNode2.child2.parent = insertNode2;
						 
						 insertNode2.parent = node.parent;
						 node.parent.child2 = insertNode2;
						 
						 insertNode1.child1 = node;
						 node.parent = insertNode1;
						 
						 insertNode1.parent = insertNode2.parent;
						 insertNode1.parent.child1 = insertNode1;
						 
						 
						 insertNode1.parent.key1 = insertNode1.parent.key2;
						 insertNode1.parent.keys = 1;
						 System.out.println(node.parent.child1 == node);
					 }
					 else if(node == node.parent.child2){
						 //System.out.println("test2");
					 }
					 else if(node == node.parent.child3){
						 //System.out.println("test3");
					 }
					 else if(node == node.parent.child4){
						 //System.out.println("test4");
					 }
				 }
				 System.out.println("test2");
				 //node = null;
			 }
		  }//else if
		} //while
	//	  while(node!=null){
			  /*if(key<node.key1){
				  if(node.child1!=null) node = node.child1;
				  else if(node.child1==null){
					  if(node.keys<3){
						  if(node.keys==1){node.key2=node.key1;node.key1=key;}
						  if(node.keys==2){node.key3=node.key2;node.key2=node.key1;node.key1=key;}
						  node.keys++;
					  }
					  else if(node.keys==3){
						  Tree234Node insertNode2 = new Tree234Node(node,node.key3);
						  insertNode2.parent = node;
						  Tree234Node insertNode1 = new Tree234Node(node,key);
						  insertNode1.key2 = node.key1;
						  insertNode2.parent = node;
						  node.keys = 1;
						  node.key1 = node.key2;
						  node.child1 = insertNode1;
						  node.child2 = insertNode2;
					  }
				  }
			  }
			  else if(key>node.key1){
				  if(node.keys==1){
					  
				  }
				  else if(node.keys ==2)
			  }*/
			  
		 // }
	  }
  }


  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}