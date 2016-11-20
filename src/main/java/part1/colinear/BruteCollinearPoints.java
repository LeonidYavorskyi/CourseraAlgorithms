package part1.colinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> segments = new ArrayList<>(4);

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Empty list of points");
        }

        Arrays.sort(points);

        if (containDuplicates(points)) {
            throw new IllegalArgumentException("There are duplicate points");
        }

        for (int first = 0; first < points.length - 3; first++) {
            Point p = points[first];

            for (int second = first + 1; second < points.length - 2; second++) {
                Point q = points[second];
                double pq = p.slopeTo(q);

                for (int third = second + 1; third < points.length - 1; third++) {
                    Point a = points[third];
                    double pa = p.slopeTo(a);
                    if (pq == pa) {
                        for (int forth = third + 1; forth < points.length; forth++) {
                            Point b = points[forth];
                            double pb = p.slopeTo(b);
                            if (pa == pb) {
                                segments.add(new LineSegment(p, b));
                            }
                        }
                    }
                }
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
