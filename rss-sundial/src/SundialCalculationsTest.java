import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class SundialCalculationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHourLineAngles() {
		double longitude = 150;
		double latitude = 30;
		int date = 20130402;
		//double[] 
		//double[] testAngles = SundialCalculations.getHourLineAngles(latitude, longitude, date);
		fail("Need experimental data to test against.");
	}

	@Test
	public void testGetMeridianDelta() {
		double testDelta = SundialCalculations.getMeridianDelta(50);
		assertEquals(5, testDelta, 0);
	}

	@Test
	public void testEOTCorrection() {
		double testDelta = SundialCalculations.EOTCorrection(20130215);
		assertEquals(-15, testDelta, 0.5);
		testDelta = SundialCalculations.EOTCorrection(20131101);
		assertEquals(16, testDelta, 0.5);
	}

	@Test
	public void testGetDayNumber() {
		int testDay = SundialCalculations.getDayNumber(20130101);
		int year = 0;
		int month = 1;
		int day = 1;
		int[] dayArray = {1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335}; 
		int[] dayArrayLeap = {1, 32, 61, 92, 122, 153, 183, 214, 245, 275, 306, 336};
		
		assertEquals(1, testDay);
		for(year = 2013, month = 1; month <= 12; month++) {
			int date = year * 10000 + month * 100 + day;
			testDay = SundialCalculations.getDayNumber(date);
			assertEquals(dayArray[month - 1], testDay);
		}
		//test a known leapyear
		for(year = 2012, month = 1; month <= 12; month++) {
			int date = year * 10000 + month * 100 + day;
			testDay = SundialCalculations.getDayNumber(date);
			assertEquals(dayArrayLeap[month - 1], testDay);
		}
	}

	@Test
	public void testCosDegrees() {
		double cos = SundialCalculations.cosDegrees(0);
		assertEquals(1, cos, 0);
		cos = SundialCalculations.cosDegrees(45);
		assertEquals(0.707, cos, 0.001);
		cos = SundialCalculations.cosDegrees(90);
		assertEquals(0, cos, 0.001);
	}

	@Test
	public void testSinDegrees() {
		double sin = SundialCalculations.sinDegrees(0);
		assertEquals(0, sin, 0);
		sin = SundialCalculations.sinDegrees(45);
		assertEquals(0.707, sin, 0.001);
		sin = SundialCalculations.sinDegrees(90);
		assertEquals(1, sin, 0.001);
	}

	@Test
	public void testTanDegrees() {
		double tan = SundialCalculations.tanDegrees(0);
		assertEquals(0, tan, 0);
		tan = SundialCalculations.tanDegrees(45);
		assertEquals(1, tan, 0.001);
		tan = SundialCalculations.tanDegrees(90);
		assert tan > 100000000000000.0;
	}

	@Test
	public void testAtanDegrees() {
		double atan = SundialCalculations.atanDegrees(0);
		assertEquals(0, atan, 0);
		atan = SundialCalculations.atanDegrees(1);
		assertEquals(45, atan, 0.001);
		atan = SundialCalculations.atanDegrees(100000000000000.0);
		assertEquals(90, atan, 0.001);
	}

	@Test
	public void testIsDayLightSavings() {
		assertFalse(SundialCalculations.isDayLightSavings(0, 0, 20130309));
		assertTrue(SundialCalculations.isDayLightSavings(0, 0, 20130310));
		assertTrue(SundialCalculations.isDayLightSavings(0, 0, 20131102));
		assertFalse(SundialCalculations.isDayLightSavings(0, 0, 20131103));
	}

	@Test
	public void testIsUSASummer() {
		try {
			assertTrue(SundialCalculations.isUSASummer(20130413));
			assertFalse(SundialCalculations.isUSASummer(20130309));
			assertTrue(SundialCalculations.isUSASummer(20130310));
			assertTrue(SundialCalculations.isUSASummer(20131102));
			assertFalse(SundialCalculations.isUSASummer(20131103));
			SundialCalculations.isUSASummer(0);
			fail("Code should not execute as previous line should have triggered an exception.");
		}
		catch(Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testHasDSTLocation() {
		assertFalse(SundialCalculations.hasDSTLocation(21.306944400000000000, -157.858333300000030000));
		assertTrue(SundialCalculations.hasDSTLocation(0,0));
		assertTrue(SundialCalculations.hasDSTLocation(180, 180));
		assertTrue(SundialCalculations.hasDSTLocation(-180, -180));
	}

	@Test
	public void testWithinGlobeQuandrant() {
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(30, 0, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(31, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(-30, 0, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(-31, 0, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, 15, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(0, 16, 30, -30, 15, -15));
		assertTrue(SundialCalculations.withinGlobeQuandrant(0, -15, 30, -30, 15, -15));
		assertFalse(SundialCalculations.withinGlobeQuandrant(0, -16, 30, -30, 15, -15));
	}

}