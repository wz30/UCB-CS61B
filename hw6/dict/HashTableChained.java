/* HashTableChained.java */

package dict;
import  list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	//int N = 50;//??? why   tableSize number of bucklist
	SList[] hashTable;       //1.use SList array as bucklist 
	int size ; 				//entry number
	int bucklist =0;  		//bucklist number N
	int[] numCollision;  	//Collision
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {  //constructor 1 with a parameter initialize all the fields
    // Your solution here.
	hashTable = new SList[sizeEstimate];
	numCollision = new int[sizeEstimate];
	bucklist = sizeEstimate;
	size = 0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {    //default constructor
    // Your solution here.
	  hashTable = new SList[101];
	  numCollision = new int[101];
	  bucklist = 101;
	  size = 0;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {      //hashcode-->compFunction
    // Replace the following line with your solution.
	int returnCode = code%bucklist;
	if(returnCode>=0)
    return returnCode;
	else return (bucklist+returnCode);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	if(size()==0)
    return true;
	else
	return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {   //use the LinkList to insert
    // Replace the following line with your solution.
	Entry entry = new Entry();
	entry.key = key;
	entry.value = value;
	
	if(hashTable[compFunction(key.hashCode())]==null)  ///****about how to use the Slist: judge if null
		hashTable[compFunction(key.hashCode())] = new SList(); //create an object
	else{
		System.out.println(numCollision[compFunction(key.hashCode())]);
		numCollision[compFunction(key.hashCode())]++;
	}
	hashTable[compFunction(key.hashCode())].insertFront(entry);

	System.out.println(hashTable[compFunction(key.hashCode())].toString());
	size++; //one case: if there is a collision, it doesnot work
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	Entry entry = new Entry();
	entry.key = key;
	
	if(hashTable[compFunction(key.hashCode())]==null) return null;
	else{
		if(!hashTable[compFunction(key.hashCode())].isEmpty()){ //wanna get the private : to creat a list before taht
			entry =  (Entry) hashTable[compFunction(key.hashCode())].nth(1);
			return entry;
		}
		else
			return null;
	}
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	if(find(key)!=null){
		hashTable[compFunction(key.hashCode())].removeHash();
		size--;
		return find(key);
	}
	else
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	hashTable = new SList[hashTable.length];
	size = 0;
  }
  
  /**
   *  print each bucklist--Slist, if not null
   */
  public String[] HashString(){
	System.out.println("bucklist:"+bucklist);
	String[] result = new String[bucklist];
	for(int i=0;i<bucklist;i++){
		if(hashTable[i]!=null){
			result[i]=hashTable[i].toString();
			//System.out.println("result: "+hashTable[i].toString());
		}
	}
	return result;
  }
  
  /**
   * add a method to your HashTableChained class
   * that counts the number of collisions--or better yet, also prints
   * a histograph of the number of entries in each bucket.  
   */
  public int numCollision(){
	  int resultColl=0;
	  //System.out.println("size:"+size+"bucklist:"+(1.0-(float)(1.0/bucklist)));  //focus on float
	  //n - N + N (1 - 1/N)^n.
	  System.out.println("expect Collision:"+(size-bucklist+bucklist*Math.pow((1.0-(float)(1.0/bucklist)), size)));
	  for(int i=0;i<numCollision.length;i++){
		  //System.out.println("collision:" + numCollision[i]);
		  resultColl+=numCollision[i];
	  }
	  return resultColl;
  }
  
}