
/**
 * @author Raksha Doddabele
 * Most efficient implementation of IDNAStrand. 
 * Uses LinkedLists to represent genomic/DNA data.
 */
public class LinkStrand implements IDnaStrand {

	/**
	 * @class Creates a private class Node that allows calls to Node to identify the info within a node
	 * and allows for LinkedLists containing Nodes to be used throughout the program
	 */
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst, myLast;
	//@node myFirst references the first node in a linked list of nodes.
	//@node myLast references the last node in a linked list of nodes.

	private long mySize;
	//@int mySize represents the total number of characters stored in all nodes together.
	private int myAppends;
	//@int myAppends is the number of times that the append method has been called.
	
	private int myIndex;
	//@int the value of the parameter in the last call to charAt. 
	private int myLocalIndex;
	//@int the value of the index within the string stored in the node last-referenced by charAt when the method finishes. 
	private Node myCurrent;
	//@Node the node of the internal list referenced in the last call to charAt.

	/**
	 * calls the other constructor to initialize a LinkedList Node with an empty string
	 */
	public LinkStrand() {
		this("");
	}

	/**
	 * Initialize this strand so that it represents the value of source.
	 * @param s is the source of cgat data for this strand
	 */
	public LinkStrand(String s) {
		initialize(s);
	}

	/**
	 * @return the instance variable mySize which holds the length of the LinkStrand object
	 */
	@Override
	public long size() {
		return mySize;

	}
	
	/**
	 * (non-Javadoc)
	 * @see IDnaStrand#initialize(java.lang.String)
	 * @param source is a String of DNA information to be passed into a new Node
	 * Initializes the LinkStrand object with a String. A single node is created after initialize is called.
	 */
	@Override
	public void initialize(String source) {
		if (source != null) {
			myFirst= new Node(source);
			myLast=myFirst;
			myAppends=0;
			mySize=source.length();
			myIndex=0;
			myLocalIndex= 0;
			myCurrent= myFirst;
		}
	}
	
	/**
	 * @param String Source is a strand of DNA
	 * @return new LinkStrand(source)
	 * Tells the program to call LinkStrand which calls initialize and a new single node is created with 
	 * the @param String Source.
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * @return IDNAStrand object as a LinkStrand
	 * @param String dna is the dna information to be added to the end of the LinkStrand
	 * Adds one new node to the end of the internal linked list and update state to maintain the invariant created
	 */
	@Override
	public IDnaStrand append(String dna) {
		if (dna!= null) {
			myLast.next= new Node(dna); 
			myLast= myLast.next; 
			myAppends++;
			mySize+= dna.length();
		}
		return this;
	}

	/**
	 * @return copy which is a new LinkStrand object that was reversed
	 * Creates a new LinkStrand object that is the reverse of the object on which it's called. Does not modify
	 * The existing strand. Reverse the linked list, and reverse each string in each node of the linked list. 
	 */
	@Override
	public IDnaStrand reverse() {
		LinkStrand copy = new LinkStrand();
		Node current = this.myFirst;
		Node end = null;
		while (current!= null) {
			StringBuilder temporaryactionitem = new StringBuilder(current.info);
			Node frontface = new Node(temporaryactionitem.reverse().toString());
			frontface.next = end;
			end = frontface;
			current = current.next;
		}
		while (end!=null) {
			copy.append(end.info);
			end = end.next;
		}
		return copy;
		}

	/**
	 * @return the value of instance variable myAppends that's 
	maintained by the class invariants and initialized/updated in initialize and append.
	@return myAppends.
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}

	/**
	 * @param int index is the index used to find the character at
	 * @return String character at the specified index
	 * updates instance variables to move through each character in the LinkStrand until the one at the
	 * specified index is found. If param index is before the index the instance variables at, they are reset and 
	 * the method traverses through the LinkStrand from the beginning. If param index is after the index the
	 * instance variables are at, the code runs with O(1) time and traverses to the next index.
	 */
	@Override
	public char charAt(int index) {
		if (index < 0 | index >= mySize) {
			throw new IndexOutOfBoundsException();
		}
		if (index<myIndex) {
			myIndex= 0;
			myLocalIndex=0;
			myCurrent= myFirst;
		}
		while (myIndex !=index) {
			myLocalIndex++;
			myIndex++;
			if (myLocalIndex>= myCurrent.info.length()) {
				myLocalIndex=0;
				myCurrent=myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}

	/**
	 * @return the LinkStrand the method is called as a String representation
	 * Loops over nodes and appends their values to a StringBuilder object. The method should run in O(N) time.
	 */
	public String toString() {
		StringBuilderStrand s= new StringBuilderStrand();
		Node n= myFirst;
		while (n!= null) {
			s.append(n.info);
			n= n.next;
		}
		return s.toString();
	}
}



