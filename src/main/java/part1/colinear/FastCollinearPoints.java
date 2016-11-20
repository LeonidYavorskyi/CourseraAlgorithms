package part1.colinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segments = new ArrayList<>(4);

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Empty list of points");
        }

        Point[] copy = points.clone();
        Arrays.sort(copy);

        if (containDuplicates(copy)) {
            throw new IllegalArgumentException("There are duplicate points");
        }

        for (int i = 0; i < copy.length - 3; i++) {
            Arrays.sort(copy);
            Arrays.sort(copy, copy[i].slopeOrder());

            for (int j = 0, first = 1, last = 2; last < copy.length; last++) {
                while (last < copy.length
                        && Double.compare(copy[j].slopeTo(copy[first]), copy[j].slopeTo(copy[last])) == 0) {
                    last++;
                }

                if (last - first >= 3 && copy[j].compareTo(copy[first]) < 0) {
                    segments.add(new LineSegment(copy[j], copy[last - 1]));
                }
                first = last;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private boolean containDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; ) {
            if (points[i].compareTo(points[++i]) == 0) {
                return true;
            }
        }
        return false;
    }

}
