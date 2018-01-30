/* ListSorts.java */

import list1.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    return null;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
 * @throws QueueEmptyException 
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException {
    // Replace the following line with your solution.
	LinkedQueue q = new LinkedQueue();
	while(!q1.isEmpty()&&!q2.isEmpty()){
		Comparable item1 = (Comparable) q1.front();
		Comparable item2 = (Comparable) q2.front();
		if(item1.compareTo(item2)<=0)
			q.enqueue(q1.dequeue());
		else 
			q.enqueue(q2.dequeue());
	}
	while(!q1.isEmpty()) q.enqueue(q1.dequeue());
	while(!q2.isEmpty()) q.enqueue(q2.dequeue());
    return q;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
 * @throws QueueEmptyException 
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) throws QueueEmptyException {
	  while(!qIn.isEmpty()){
		  Comparable element = (Comparable) qIn.front();
		  if(pivot.compareTo(element)>0) qSmall.enqueue(qIn.dequeue());
		  else if(pivot.compareTo(element)==0) qEquals.enqueue(qIn.dequeue());
		  else qLarge.enqueue(qIn.dequeue());
	  }
	  
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
 * @throws QueueEmptyException 
   **/
  public static void mergeSort(LinkedQueue q) throws QueueEmptyException {
    // Your solution here.
	int n = q.size();//num of item
	if(q.size()<2){ //already sort with one item
		return;
	}
	
	Queue S1 = new LinkedQueue();
	Queue S2 = new LinkedQueue();
	//take the item from q to S1 and S2 --divide
	while(q.size()>(n/2)){
		S1.enqueue(q.dequeue());
	}
	while(!q.isEmpty()){
		S2.enqueue(q.dequeue());
	}
	//conquer with recursion
	mergeSort((LinkedQueue)S1);
	mergeSort((LinkedQueue)S2);
	LinkedQueue temp = new LinkedQueue();
	temp=mergeSortedQueues((LinkedQueue)S1,(LinkedQueue)S2);
	//System.out.println("temp: "+ temp.toString());
	q.append(temp); //append the result
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
 * @throws QueueEmptyException 
   **/
  public static void quickSort(LinkedQueue q) throws QueueEmptyException {
	  int n = q.size();//num of item
		if(q.size()<2){ //already sort with one item
			return;
		}
		Queue qSmall = new LinkedQueue();  //def theree linkedQueue
		Queue qEqual = new LinkedQueue();
		Queue qLarge = new LinkedQueue();
		Comparable pivot = (Comparable) q.front();  //pivot
		partition((LinkedQueue)q, pivot, (LinkedQueue)qSmall, (LinkedQueue)qEqual, (LinkedQueue)qLarge);
		quickSort((LinkedQueue)qSmall);
		quickSort((LinkedQueue)qLarge);
		while(!qSmall.isEmpty()) q.enqueue(qSmall.dequeue());
		while(!qEqual.isEmpty()) q.enqueue(qEqual.dequeue());
		while(!qLarge.isEmpty()) q.enqueue(qLarge.dequeue());
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
 * @throws QueueEmptyException 
   **/
  public static void main(String [] args) throws QueueEmptyException {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}