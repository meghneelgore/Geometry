package com.meghneelgore.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestKNearest {

    @Test
    public void testFindKNearest() {
        KNearest.Point p1 = new KNearest.Point(1.0, 1.0);
        KNearest.Point p2 = new KNearest.Point(2.0, 2.0);
        KNearest.Point p3 = new KNearest.Point(3.0, 3.0);
        KNearest.Point p4 = new KNearest.Point(-1.5, -1.5);

        ArrayList<KNearest.Point> input = new ArrayList<>();
        input.add(p1);
        input.add(p2);
        input.add(p4);
        input.add(p3);

        KNearest kNearest = new KNearest();
        List<KNearest.Point> list = kNearest.findKNearest(input, 2);

        Assert.assertEquals("Wrong number of points in list", 2, list.size());
        Assert.assertEquals("Wrong point in list", new KNearest.Point(-1.5, -1.5), list.get(0));
        Assert.assertEquals("Wrong point in list", new KNearest.Point(1.0, 1.0), list.get(1));
    }
}
