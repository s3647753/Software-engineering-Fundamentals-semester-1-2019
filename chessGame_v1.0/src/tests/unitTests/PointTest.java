package tests.unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Point;

/**
 * Unit tests for the Point class
 * 
 * @author CS Bernard O'Meara
 *
 */
public class PointTest {
	private Point point0, point1, point2, point3, negVals;

	@Before
	public void setUp() throws Exception {
		point0 = new Point(0, 0);
		point1 = new Point(3, 5);
		point2 = new Point(3, 5);
		point3 = new Point(5, 3);
		negVals = new Point(-10, -20);
	}
	
	@Test
	public void testGetX() {
		assertEquals("Point.getX() failed", 3, point1.getX());
	}
	
	@Test
	public void testGetY() {
		assertEquals("Point.getY() failed", 5, point2.getY());
	}

	@Test
	public void testAdd() {
		Point sum = point1.add(point2);
		assertEquals("Add points X value failed", 6, sum.getX());
		assertEquals("Add points Y value failed", 10, sum.getY());
	}
	
	@Test
	public void testAddNegVals() {
		Point sum = point1.add(negVals);
		
		assertEquals("Add points X neg value failed", -7, sum.getX());
		assertEquals("Add points Y neg value failed", -15, sum.getY());
	}
	
	@Test
	public void testAddCornerCases() {
		Point point0 = new Point(0, 0);
		
		// adding zero to a point
		assertEquals("Add zero point X failed", point1.getX(), point1.add(point0).getX());
		assertEquals("Add zero point Y failed", point1.getY(), point1.add(point0).getY());
		
		// adding a point to itself
		assertEquals("Add double point X failed", 2 * point1.getX(), point1.add(point1).getX());
		assertEquals("Add double point Y failed", 2 * point1.getY(), point1.add(point1).getY());
	}
	

	@Test
	public void testToString() {
		assertEquals("Point.toString() failed", "Point(3, 5)", point1.toString());
	}
	
	@Test
	public void testHashCode() {	
		assertEquals("Hashcodes do not match", point1.hashCode(), point2.hashCode());
	}
	
	@Test
	public void testEquals() {
		assertTrue("Like Points not indicating equal", point1.equals(point2));
		assertFalse("Dis-Like Points indicating equal", point1.equals(point3));
	}

}
