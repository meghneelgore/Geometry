package com.meghneelgore.geometry.sorting;

import com.meghneelgore.geometry.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestKNearest {

    @Test
    public void testFindKNearest() {
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(3.0, 3.0);
        Point p4 = new Point(-1.5, -1.5);

        ArrayList<Point> input = new ArrayList<>();
        input.add(p1);
        input.add(p2);
        input.add(p4);
        input.add(p3);

        KNearest kNearest = new KNearest();
        List<Point> list = kNearest.findKNearest(input, 2);

        Assert.assertEquals("Wrong number of points in list", 2, list.size());
        Assert.assertEquals("Wrong point in list", new Point(-1.5, -1.5), list.get(0));
        Assert.assertEquals("Wrong point in list", new Point(1.0, 1.0), list.get(1));
    }
}
