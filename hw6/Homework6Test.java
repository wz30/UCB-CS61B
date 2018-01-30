/* Homework6Test.java */

import dict.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
	double fval = Math.random() * 12;
	int value = (int) fval;
	board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), new Integer(i));
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    //System.out.println("test here");
    if (args.length == 0) {
      numBoards = 1000;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    HashTableChained table = new HashTableChained(numBoards);
    
    /** test HashTableChained*/
    /*table.insert("1", "happy");
    table.insert("2", "hapy");
    table.insert("3", "hap");
    table.insert(2,"sad");
    table.insert("2", "trhapy");
    table.insert("2", "thapy");
    
    System.out.println("size:" + table.size());
    System.out.println("isempty:" + table.isEmpty());
    String[] str = table.HashString();
    for(String s:str){
    	if(s!=null) System.out.println(s);
    }*/
    
    //test find
    /* String s = table.find("2").toString();
    System.out.println(s);
    s = table.find(2).toString();
    System.out.println(s);*/
    
    //test remove
    /*table.remove("2");
    table.remove(2);
    System.out.println("size:" + table.size());
    System.out.println("isempty:" + table.isEmpty());
    str = table.HashString();
    for(String ss:str){
    	if(ss!=null) System.out.println(ss);
    }*/
    initTable(table, numBoards);
    System.out.println("total:"+table.numCollision());
    
    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
    
  }

}