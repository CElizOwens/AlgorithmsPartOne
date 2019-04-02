import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class Pointtests {
	private static Point[] test;

	public static void main(String[] args) {

	    Point first = new Point(5, 3);
	    Point second = new Point(5, 3);
	    Point third = new Point(5, 4);
	    Point fourth = new Point(5, 2);
	    Point fifth = new Point(4, 3);
	    Point sixth = new Point(6, 3);
	    Point seventh = new Point(4, 4);
	    Point eighth = new Point(4, 2);
	    Point ninth = new Point(6, 3);


	    Point tenth = new Point(6, 4);
	    Point eleventh = new Point(7, 5);


	    test = new Point[] { eighth, first, tenth, eleventh };

	    // ++ Not sure about this next line: ++
	    // Object[] night = new Object[]{second, third};

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
	    // StdOut.println(first.slopeTo(second) + ": answer is negative infinity");
	    // StdOut.println(first.slopeTo(third) + ": answer is positive infinity");
	    // StdOut.println(first.slopeTo(fifth) + ": answer is +0.0");
	    // StdOut.println(first.slopeTo(seventh) + ": answer is a valid slope value");
	    // StdOut.println(first.slopeTo(eighth) + ": answer is a valid slope value");

	    // Test 'slopeOrder()'
		// StdOut.println(first.slopeOrder().compare(second, fifth) + ": answer is -1");
		// StdOut.println(first.slopeOrder().compare(third, fifth) + ": answer is 1");
		// StdOut.println(first.slopeOrder().compare(sixth, ninth) + ": answer is 0");

		// Test 'LineSegment.java'
		// Point high = new Point(4096, 20992);
		// Point low = new Point(0, 0);
		// LineSegment seg = new LineSegment(high, low);
		// seg.draw();

	    // Test 'BruteCollinearPoints.java'
	    // BruteCollinearPoints trial = new BruteCollinearPoints(test);

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);

	    for (LineSegment segment : collinear.segments()) {
        	StdOut.println(segment);
	        segment.draw();
		}
		StdDraw.show();

	}
}