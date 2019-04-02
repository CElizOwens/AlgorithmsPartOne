import edu.princeton.cs.algs4.StdOut;

public class Pointtests {

	public static void main(String[] args) {

	    Point first = new Point(5, 3);
	    Point second = new Point(5, 3);
	    Point third = new Point(5, 4);
	    Point fourth = new Point(5, 2);
	    Point fifth = new Point(4, 3);
	    Point sixth = new Point(6, 3);
	    Point seventh = new Point(4, 4);
	    Point eighth = new Point(4, 2);

		// Test 'compareTo()'
	 //    StdOut.println(first.toString() + second.toString());
	 //    StdOut.println(first.compareTo(second) + ": answer is 0");
	 //    StdOut.println(first.toString() + third.toString());
	 //    StdOut.println(first.compareTo(third) + ": answer is -1");
	 //    StdOut.println(first.toString() + fourth.toString());
	 //    StdOut.println(first.compareTo(fourth) + ": answer is 1");
	 //    StdOut.println(first.toString() + fifth.toString());
	 //    StdOut.println(first.compareTo(fifth) + ": answer is 1");
	 //    StdOut.println(first.toString() + sixth.toString());
	 //    StdOut.println(first.compareTo(sixth) + ": answer is -1");

		// Test 'slopeTo()'
	    StdOut.println(first.slopeTo(second) + ": answer is negative infinity");
	    StdOut.println(first.slopeTo(third) + ": answer is positive infinity");
	    StdOut.println(first.slopeTo(fifth) + ": answer is +0.0");
	    StdOut.println(first.slopeTo(seventh) + ": answer is a valid slope value");
	    StdOut.println(first.slopeTo(eighth) + ": answer is a valid slope value");

	}
}