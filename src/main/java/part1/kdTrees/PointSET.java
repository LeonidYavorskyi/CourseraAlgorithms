package part1.kdTrees;

import edu.princeton.cs.algs4.*;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> jSet;

    // construct an empty set of points
    public PointSET() {
        jSet = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return jSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return jSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        jSet.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Null input =(");
        }
        return jSet.contains(p);
    }

    // draw all points to standard draw
    // TODO: Changed Type of TreeSet to jNodes, so remake draw too ^^
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D point2d : jSet) {
            point2d.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> jStack = new Stack<>();
        for (Point2D point2d : jSet) {
            // should i check on the rect border? if so - done =)
            if (point2d.x() >= rect.xmin() && point2d.x() <= rect.xmax() && point2d.y() >= rect.ymin()
                    && point2d.y() <= rect.ymax()) {
                jStack.push(point2d);
                // System.err.println(point2d + " Pushed");
            }
        }
        return jStack;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (jSet.isEmpty())
            return null;

        MinPQ<Map.Entry<Double, Point2D>> jQueue = new MinPQ<>(new Comparator<Map.Entry<Double, Point2D>>() {
            @Override
            public int compare(Map.Entry<Double, Point2D> arg0, Map.Entry<Double, Point2D> arg1) {
                // TODO Auto-generated method stub
                return arg0.getKey().compareTo(arg1.getKey());
            }
        });

        for (Point2D that : jSet) {
            if (that == p)
                continue;
            jQueue.insert(new AbstractMap.SimpleEntry<Double, Point2D>(p.distanceTo(that), that));
        }
        return jQueue.delMin().getValue();
    }

}
