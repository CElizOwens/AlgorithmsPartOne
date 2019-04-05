import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private Point[] pointholder;
	private int phcounter;
	private Point[] slopeholder;
	private int shcounter;
	private Point[] sorted;
	private LineSegment[] segs;
	private int segscounter;
	private LineSegment[] result;
	private int resultcounter;


    public FastCollinearPoints(Point[] points) {   // finds all line segments containing 4 or more points
    	if (points == null) throw new IllegalArgumentException("Must not be null");

    	slopeholder = new Point[points.length - 1];
    	pointholder = new Point[points.length - 1];
    	shcounter = 0;
    	phcounter = 0;
    	segs = new LineSegment[points.length];
    	// slopecounter = 0;

		for (int n = 0; n < points.length; n++) {
			for (int s = 0; s < points.length; s++) {
				if (points[s] == null) throw new IllegalArgumentException("Can not be null");
				if (s == n) continue;  // same index in points array
				// tests for duplicate points
				if (points[n].compareTo(points[s]) == 0) throw new IllegalArgumentException("Repeated point");
				pointholder[phcounter] = points[s];
				phcounter++;
			}
			phcounter = 0;
			Arrays.sort(pointholder, points[n].slopeOrder());

			for (int x = 1; x < pointholder.length; x++) {
				if (shcounter == 0) {
					slopeholder[shcounter] = pointholder[x - 1];
					shcounter++;
				}


				if (points[n].slopeTo(pointholder[x]) == points[n].slopeTo(pointholder[x - 1])) {
					slopeholder[shcounter] = pointholder[x];
					shcounter++;
				}
				else if (shcounter < 3) { // Above 'if' failed and less thset shcounter back to 0;
					shcounter = 0;
				}

				else if (shcounter >= 3) {
	    			sorted = Arrays.copyOfRange(slopeholder, 0, shcounter + 1);
	    			sorted[shcounter] = points[n];

	    			// The sort below may not be needed
	    			// Isn't 'slopeholder' already ordered by slope?
	    			// And then, couldn't the 'segs[segscounter]' assignment go like this:
	    			// 'new LineSegment(points[n], slopeholder[shcounter - 1])' ??
	    			// Well, 'slopeholder' is not ordered by point.
	    			Arrays.sort(sorted); // sorting with point n
					segs[segscounter] = new LineSegment(sorted[0], sorted[shcounter]); // includes point n
					
					// // test
					// StdOut.println("Line segment " + (segscounter + 1) + ": " + segs[segscounter]);

					segscounter++;
					
					// // test
					// StdOut.println(segscounter + " segments so far");
					
					shcounter = 0;
				}
			}
		}
		// StdOut.println(segscounter + ": number of segments before eliminating duplicates");

		// for (LineSegment seg : segs) {
		// 	StdOut.println(seg);
		// }

		// boolean checksegs = segs[0] == segs[1];
		// StdOut.println("First and second: " + checksegs);


		if (segscounter > 1) {
			// Arrays.sort(segs);
			for (int s = 1; s < segs.length; s++) {
				if (segs[s] != null && segs[s] == segs[s - 1]) {
					segs[s - 1] = null;
					segscounter--;
				}
			}
		}
	}
    
    public int numberOfSegments() {      // the number of line segments
    	return segscounter;
    }

    public LineSegment[] segments() {              // the line segments
		// maybe take care of duplicate recordings of segments here?
		resultcounter = 0;
    	result = new LineSegment[segscounter];
  		for (LineSegment seg : segs) {
  			if (seg != null) {
  				result[resultcounter] = seg;
  				resultcounter++;
  			}
  		}
  		// StdOut.println(result[0] + " " + result[1]);
  		return result;
	}

}
