public class LinkStrand implements IDnaStrand {

    /**
     * Inner Node class to create nodes
     */
    private class Node {
        String info;
        Node next;

        /**
         * Creates a Node
         * @param s - String with info for Node
         */
        public Node(String s) {
            info = s;
            next = null;
        }
    }
    private Node myFirst,myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;

    /**
     * Default constructor for LinkStrand class
     */
    public LinkStrand(){
        this("");
    }

    /**
     * Create LinkStrand object given info for Node; uses initialize method to initialize variables
     * @param s - info for single Node
     */
    public LinkStrand(String s){
        initialize(s);
    }

    /**
     * Initialize by copying DNA data from the string into this strand,
     * replacing any data that was stored. The parameter should contain only
     * valid DNA characters, no error checking is done by the this method.
     *
     * @param source
     *            is the string used to initialize this strand
     */
    public void initialize(String source){
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myLocalIndex = 0;
        myCurrent = myFirst;
    }

    /**
     * Append dna to the end of this strind.
     * @param dna
     *            is the string appended to this strand
     * @return this strand after the data has been added
     */
    public IDnaStrand append(String dna){
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    /**
     * Creates a String object to contains all of the information in the LinkStrand object
     * Makes use of the StringBuilder class to create a mutable object
     * @return - String object of all strands
     */
    public String toString(){
        StringBuilder list = new StringBuilder();
        Node temp = myFirst;
        while(temp != null){
            list.append(temp.info);
            temp = temp.next;
        }
        return list.toString();
    }

    /**
     * Returns the number of times append has been called.
     *
     * @return myAppends - number of times append has been called
     */
    public int getAppendCount(){
        return myAppends;
    }

    /**
     * Return this object, useful to obtain
     * an object without knowing its type, e.g.,
     * calling dna.getInstance() returns an IDnaStrand
     * that will be the same concrete type as dna
     * @param source is data from which object constructed
     * @return an IDnaStrand whose .toString() method will be source
     */
    public IDnaStrand getInstance(String source){
        return new LinkStrand(source);
    }

    /**
     * Returns the number of elements/base-pairs/nucleotides in this strand.
     * @return the number of base-pairs in this strand
     */
    public long size(){
        return mySize;
    }

    /**
     * Returns an IDnaStrand that is the reverse of this strand, e.g., for
     * "CGAT" returns "TAGC"
     *
     * @return reverse strand
     */
    public IDnaStrand reverse(){
        LinkStrand temp = new LinkStrand();
        StringBuilder reverseString = new StringBuilder(myFirst.info);
        reverseString = reverseString.reverse();
        temp.myLast.info = reverseString.toString();
        temp.myFirst = temp.myLast;
        temp.mySize += reverseString.toString().length();
        temp.myAppends++;
        Node tempNode = myFirst;
        myFirst = myFirst.next;
        while(myFirst!=null) {
            StringBuilder reverse = new StringBuilder(myFirst.info);
            reverse = reverse.reverse();
            Node temp2 = new Node(reverse.toString());
            temp2.next = temp.myFirst;
            temp.myFirst = temp2;
            temp.mySize += reverse.toString().length();
            temp.myAppends++;
            myFirst = myFirst.next;
        }
        myFirst = tempNode;
        return temp;
    }

    /**
     * Returns character at a specified index, where 0 <= index < size()
     * @param index specifies which character will be returned
     * @return the character at index
     * @throws IndexOutOfBoundsException if index < 0 or inde >= size()
     */
    public char charAt (int index){
        if(index>=mySize||index<0)
            throw new IndexOutOfBoundsException();
        Node temp = myCurrent;
        if(index < myIndex)
        {
            myIndex = 0;
            myLocalIndex = 0;
            temp = myFirst;
        }
        while (myIndex != index) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= temp.info.length()) {
                myLocalIndex = 0;
                temp = temp.next;
            }
        }
        myCurrent = temp;
        return temp.info.charAt(myLocalIndex);
    }
}

