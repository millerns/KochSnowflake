package com.kochSnowflake.millerns;

import java.util.HashMap;

/**
 * This interface contains the constants used when generating a Koch Curve
 * L-System.
 * 
 * @author millerns
 * 
 */
public interface KochCurveConstants {
	/**
	 * The axiom (starting state) for a Koch Curve.
	 */
	public static final String KOCH_AXIOM = "F";

	/**
	 * The production function for a Koch Curve. Defined elsewhere, as I do not
	 * know how to populate a HashMap in an interface.
	 */
	public static final HashMap<String, String> KOCH_PRODUCTION = new HashMap<>();

	/**
	 * The angle used in a Koch Curve L-System: 60 degrees in radians.
	 */
	public static final double KOCH_ANGLE = 60.0 * LSystem.DEGREE_TO_RADIANS;

	/**
	 * The scale factor used in a Koch Curve. Each new line is one-third the
	 * length of the lines in the previous iteration.
	 */
	public static final double KOCH_SCALE_FACTOR = 1.0 / 3.0;

	/**
	 * The sequence of symbols used when a left turn is made.
	 */
	public static final String LEFT_TURN = "F-F++F-F";

	/**
	 * The sequence of symbols used when a right turn is made.
	 */
	public static final String RIGHT_TURN = "F+F--F+F";
}
