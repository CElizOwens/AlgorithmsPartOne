import java.util.Arrays;

public class BruteCollinearPoints {
	private int counter = 0;
	private LineSegment[] holder;
	private LineSegment[] results;
	private Point[] sorted;

	public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
		holder = new LineSegment[points.length];

		if (points == null) throw new IllegalArgumentException("Cannot be null");
		// if a point in points is null -- done
		// or a point is repeat twice
		// return IllegalArgumentException

	    for (int i = 0; i < points.length - 3; i++) {
	    	for (int j = i + 1; j < points.length - 2; j++) {
	    		for (int k = j + 1; k < points.length - 1; k++) {
	    			for (int l = k + 1; l < points.length; l++) {
				    	if (points[l] == null) throw new IllegalArgumentException("Cannot be null");
				    	if (points[l].compareTo(points[i]) == 0 || points[l].compareTo(points[j]) == 0 || points[l].compareTo(points[k]) == 0) {
				    		throw new IllegalArgumentException("Repeated point");
				    	}
				    	if (points[i].slopeTo(points[j]) == (points[i].slopeTo(points[k]))) {
				    		if (points[i].slopeTo(points[l]) == (points[i].slopeTo(points[k]))) {
				    			counter++;
				    			// sort these points from min to max
				    			sorted = new Point[] { points[i], points[j], points[k], points[l] };
				    			Arrays.sort(sorted);
				    			// put this line segment in a LineSegment array
				    			holder[counter - 1] = new LineSegment(sorted[0], sorted[3]);
				    		}
				    	}
	    			}
	    		}
	    	}
    	}

	}

	public int numberOfSegments() { // the number of line segments
		return counter;
	}

	public LineSegment[] segments() { // the line segments
		results = new LineSegment[counter];
		int x = 0;
		while (holder[x] != null) {
			results[x] = holder[x];
			x++;
		}
		return results;
	}
}