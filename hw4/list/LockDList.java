package list;


public class LockDList extends DList{

	protected LockDListNode head;
	
	public void lockNode(DListNode node) { 
		LockDListNode lnode = (LockDListNode)node;
		lnode.lock = true;
	}
	
	//override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		  //DListNode node = super.newNode(item, prev, next);
		  LockDListNode lnode = new LockDListNode(item, prev, next);
		  return (DListNode)lnode;
	  }
	
	/**
	   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
	   *  Performance:  runs in O(1) time.
	   */
	  public void remove(DListNode node) {
		  LockDListNode lnode = (LockDListNode)node ;
		  if(lnode.lock==false) {
			  super.remove(node);
		  }
	  }


}
