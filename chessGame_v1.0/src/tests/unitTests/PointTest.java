package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Point;

/**
 * Unit tests for the Point class
 * 
 * @author Bernard O'Meara
 *
 */
public class PointTest {
	private Point point0, point1, point2, point3, point4, negVals;

	@Before
	public void setUp() throws Exception {
		point0 = new Point(0, 0);
		point1 = new Point(3, 5);
		point2 = new Point(3, 5);
		point3 = new Point(5, 5);
		point4 = new Point(3, 3);
		negVals = new Point(-10, -20);
	}
	
	@Test
	public void testGetters() {
		assertEquals("Point.getRow() failed", 3, point1.getRow());
		assertEquals("Point.getCol() failed", 5, point2.getCol());
	}

	@Test
	public void testAdd() {
		Point sum = point1.add(point2);
		assertEquals("Add points Row value failed", 6, sum.getRow());
		assertEquals("Add points Col value failed", 10, sum.getCol());
	}
	
	@Test
	public void testAddNegVals() {
		Point sum = point1.add(negVals);
		
		assertEquals("Add points Row neg value failed", -7, sum.getRow());
		assertEquals("Add points Col neg value failed", -15, sum.getCol());
	}
	
	@Test
	public void testAddCornerCases() {
		Point point0 = new Point(0, 0);
		
		// adding zero to a point
		assertEquals("Add zero point Row failed", point1.getRow(), point1.add(point0).getRow());
		assertEquals("Add zero point Col failed", point1.getCol(), point1.add(point0).getCol());
		
		// adding a point to itself
		assertEquals("Add double point Row failed", 2 * point1.getRow(), point1.add(point1).getRow());
		assertEquals("Add double point Col failed", 2 * point1.getCol(), point1.add(point1).getCol());
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
		assertTrue("Points with matching attributes", point1.equals(point2));
		assertFalse("Points with not matching attributes", point1.equals(point3));
	}
	
	// testing that the List.contains() method works
	@Test
	public void testListContains() {
		List<Point> list = new ArrayList<>();
		list.add(point1);
		
		assertTrue("List contains the same instance", list.contains(point1));
		assertTrue("List containing equal Point", list.contains(point2));
		assertFalse("List containing != Point", list.contains(point3));
		
	}

}
