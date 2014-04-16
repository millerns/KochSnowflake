package com.kochSnowflake.millerns;

/**
 * Generates a Koch Snowflake with random turns. Uses KochSnowflakeConstants,
 * populates the production function, and takes in the odds of making a left
 * turn.
 * 
 * @author millerns
 * 
 */
public class KochSnowflakeRandomLSystem extends RandomLSystem implements
		KochSnowflakeConstants {

	public KochSnowflakeRandomLSystem(int percentLeft) {
		super(KOCH_AXIOM, KOCH_PRODUCTION, KOCH_ANGLE, KOCH_SCALE_FACTOR,
				percentLeft, LEFT_TURN, RIGHT_TURN);
		KOCH_PRODUCTION.put("F", "F-F++F-F");
		KOCH_PRODUCTION.put("+", "+");
		KOCH_PRODUCTION.put("-", "-");
	}
}
