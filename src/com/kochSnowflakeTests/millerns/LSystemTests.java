package com.kochSnowflakeTests.millerns;

import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kochSnowflake.millerns.LSystem;

public class LSystemTests {

	private static final double SIXTY_DEGREES = 60.0 * LSystem.DEGREE_TO_RADIANS;
	private static final double ONE_THIRD = 1.0 / 3.0;

	private static final String TEST_AXIOM_KOCH = "F";
	private static final String TEST_AXIOM_CANTOR = "F";

	private static final HashMap<String, String> TEST_PRODUCTION_KOCH = new HashMap<String, String>();
	private static final HashMap<String, String> TEST_PRODUCTION_CANTOR = new HashMap<String, String>();

	private static final String KOCH_ITERATION_ZERO = "F";
	private static final String KOCH_ITERATION_ONE = "F-F++F-F";
	private static final String KOCH_ITERATION_TWO = "F-F++F-F-F-F++F-F++F-F++F-F-F-F++F-F";

	private static final String CANTOR_ITERATION_ZERO = "F";
	private static final String CANTOR_ITERATION_ONE = "FfF";
	private static final String CANTOR_ITERATION_TWO = "FfFfffFfF";

	private LSystem kochTestSystem;
	private LSystem cantorTestSystem;

	@BeforeClass
	public static void setUpClass() {
		TEST_PRODUCTION_KOCH.put("F", "F-F++F-F");
		TEST_PRODUCTION_KOCH.put("+", "+");
		TEST_PRODUCTION_KOCH.put("-", "-");

		TEST_PRODUCTION_CANTOR.put("F", "FfF");
		TEST_PRODUCTION_CANTOR.put("f", "fff");
	}

	@Before
	public void setUp() {
		this.kochTestSystem = new LSystem(TEST_AXIOM_KOCH,
				TEST_PRODUCTION_KOCH, SIXTY_DEGREES, ONE_THIRD);
		this.cantorTestSystem = new LSystem(TEST_AXIOM_CANTOR,
				TEST_PRODUCTION_CANTOR, 0.0, ONE_THIRD);
	}

	@Test
	public void testLSystem() {
		assertEquals(TEST_AXIOM_KOCH, kochTestSystem.getAxiom());
		assertEquals(TEST_PRODUCTION_KOCH, kochTestSystem.getProduction());
		assertEquals(TEST_AXIOM_KOCH, kochTestSystem.getIteration());

		assertEquals(TEST_AXIOM_CANTOR, cantorTestSystem.getAxiom());
		assertEquals(TEST_PRODUCTION_CANTOR, cantorTestSystem.getProduction());
		assertEquals(TEST_AXIOM_CANTOR, cantorTestSystem.getIteration());
	}

	@Test
	public void testIterate() {
		assertEquals(KOCH_ITERATION_ZERO, kochTestSystem.getIteration());
		assertEquals(KOCH_ITERATION_ONE, kochTestSystem.iterate());
		assertEquals(KOCH_ITERATION_TWO, kochTestSystem.iterate());

		assertEquals(CANTOR_ITERATION_ZERO, cantorTestSystem.getIteration());
		assertEquals(CANTOR_ITERATION_ONE, cantorTestSystem.iterate());
		assertEquals(CANTOR_ITERATION_TWO, cantorTestSystem.iterate());
	}

	@Test
	public void testIterateN() {
		assertEquals(KOCH_ITERATION_TWO, kochTestSystem.iterateN(2));
		assertEquals(CANTOR_ITERATION_TWO, cantorTestSystem.iterateN(2));
	}

	/*
	 * @Test public void testToLines(){ ArrayList<Line2D.Double>
	 * iterationZeroLines = generateIterationZeroLines();
	 * ArrayList<Line2D.Double> iterationOneLines = generateIterationOneLines();
	 * ArrayList<Line2D.Double> iterationTwoLines = generateIterationTwoLines();
	 * 
	 * ArrayList<Line2D.Double> iterationZeroTest = kochTestSystem.toLines(27);
	 * kochTestSystem.iterate(); ArrayList<Line2D.Double> iterationOneTest =
	 * kochTestSystem.toLines(27); kochTestSystem.iterate();
	 * ArrayList<Line2D.Double> iterationTwoTest = kochTestSystem.toLines(27);
	 * 
	 * assertEquals(generateIterationZeroLines(), kochTestSystem.toLines(27));
	 * kochTestSystem.iterate(); assertEquals(generateIterationOneLines(),
	 * kochTestSystem.toLines(27)); kochTestSystem.iterate();
	 * assertEquals(generateIterationTwoLines(), kochTestSystem.toLines(27));
	 * 
	 * }
	 */

	@SuppressWarnings("unused")
	private ArrayList<Line2D.Double> generateIterationZeroLines() {
		ArrayList<Line2D.Double> lineList = new ArrayList<Line2D.Double>();
		lineList.add(new Line2D.Double(0.0, 0.0, 27.0, 0.0));
		return lineList;
	}

	@SuppressWarnings("unused")
	private ArrayList<Line2D.Double> generateIterationOneLines() {
		ArrayList<Line2D.Double> lineList = new ArrayList<Line2D.Double>();
		lineList.add(new Line2D.Double(0.0, 0.0, 9.0, 0.0));
		lineList.add(new Line2D.Double(9.0, 0.0, 13.5, 7.794228634));
		lineList.add(new Line2D.Double(13.5, 7.794228634, 18.0, 0.0));
		lineList.add(new Line2D.Double(18.0, 0.0, 27.0, 0.0));
		return lineList;
	}

	@SuppressWarnings("unused")
	private ArrayList<Line2D.Double> generateIterationTwoLines() {
		return null;
	}

}
