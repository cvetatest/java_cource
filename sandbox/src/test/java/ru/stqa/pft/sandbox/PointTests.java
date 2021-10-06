package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class PointTests {
  public void testDistance1(){
    Point t1=new Point(1,2);
    Point t2=new Point(4,3);
    double d;
    d=Point.distance(t1,t2);
    Assert.assertEquals(d,3.1622776601683795);

  }

  public void testDistance2(){
    Point t1=new Point(1,2);
    Point t2=new Point(1,3);
    double d;
    d=Point.distance(t1,t2);
    Assert.assertEquals(d,1);

  }
}
