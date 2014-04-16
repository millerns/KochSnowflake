package com.kochSnowflake.millerns;

/**
 * This class is used to generate a Koch Curve L-System. Uses the values from
 * KochCurveConstants and populates the production function.
 * 
 * @author millerns
 * 
 */
public class KochCurveLSystem extends LSystem implements KochCurveConstants {

	public KochCurveLSystem() {
		super(KOCH_AXIOM, KOCH_PRODUCTION, KOCH_ANGLE, KOCH_SCALE_FACTOR);
		KOCH_PRODUCTION.put("F", "F-F++F-F");
		KOCH_PRODUCTION.put("+", "+");
		KOCH_PRODUCTION.put("-", "-");
	}

}
