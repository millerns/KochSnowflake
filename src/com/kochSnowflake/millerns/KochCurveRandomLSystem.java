package com.kochSnowflake.millerns;

/**
 * Generates a Koch Curve L-System with random turns based on a given chance of
 * turning left.
 * 
 * @author millerns
 * 
 */
public class KochCurveRandomLSystem extends RandomLSystem implements
		KochCurveConstants {

	public KochCurveRandomLSystem(int percentLeft) {
		super(KOCH_AXIOM, KOCH_PRODUCTION, KOCH_ANGLE, KOCH_SCALE_FACTOR,
				percentLeft, LEFT_TURN, RIGHT_TURN);
		KOCH_PRODUCTION.put("F", "F-F++F-F");
		KOCH_PRODUCTION.put("+", "+");
		KOCH_PRODUCTION.put("-", "-");
	}
}
