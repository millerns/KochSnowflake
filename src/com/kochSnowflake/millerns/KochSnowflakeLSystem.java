package com.kochSnowflake.millerns;

/**
 * Generates a Koch Snowflake L-System. Uses KochSnowflakeConstants and
 * populates the production function.
 * 
 * @author millerns
 * 
 */
public class KochSnowflakeLSystem extends LSystem implements
		KochSnowflakeConstants {

	public KochSnowflakeLSystem() {
		super(KOCH_AXIOM, KOCH_PRODUCTION, KOCH_ANGLE, KOCH_SCALE_FACTOR);
		KOCH_PRODUCTION.put("F", "F-F++F-F");
		KOCH_PRODUCTION.put("+", "+");
		KOCH_PRODUCTION.put("-", "-");
	}
}
