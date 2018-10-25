
public class LinkStrand implements IDnaStrand {

	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	   private Node myFirst, myLast;
	   private long mySize;
	   private int myAppends;
	   private int myIndex;
	   private int myLocalIndex;
	   private Node myCurrent;
	
	   
	public LinkStrand(String s) {
		initialize(s);
	}
	public LinkStrand() {
		this("");
		
	}
	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
		
	}

	@Override
	public void initialize(String source) {
		// TODO Auto-generated method stub
		myFirst= new Node(source);
		myLast=myFirst;
		myAppends=0;
		mySize=source.length();
		myIndex=0;
		myLocalIndex= 0;
		myCurrent= myFirst;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next= new Node(dna); 
		myLast= myLast.next; 
		myAppends++;
		mySize+= dna.length();
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		LinkStrand copy= new LinkStrand();
		Node current= myFirst;
		Node previous = new Node(null);
		Node next= new Node(null);
		while (current != null) {
		    next = current.next;
		    current.next = previous;
		    previous = current;
		    current = next;
		    StringBuilder reversestr= new StringBuilder(previous.info);
		    reversestr= reversestr.reverse();
		    copy.append(reversestr.toString());
		    
		}
		return copy;
		
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
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
	


