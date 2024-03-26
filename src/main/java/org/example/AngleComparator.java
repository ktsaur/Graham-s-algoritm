package org.example;

import java.util.Comparator;

public class AngleComparator implements Comparator<Point> {
    private Point left;
    public AngleComparator(Point left) { this.left = left; }
    @Override
    public int compare(Point o1, Point o2) {
        int result = 0;
        if (o1 == left) {
            result = -1;
        }
        else if (o2 == left) {
            result = 1;
        }
        else {
            result = - Double.compare(Math.atan2(o1.getY() - left.getY(), o1.getX() - left.getX()), Math.atan2(o2.getY() - left.getY(), o2.getX() - left.getX()));
        }
        return result;
    }
}
