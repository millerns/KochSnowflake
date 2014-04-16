package com.kochSnowflake.millerns;

import java.util.HashMap;
import java.util.Random;

/**
 * Generates an L-System with random turns. Overrides the standard L-System
 * iterate function by randomly turning left or right according to the given
 * odds of turning left. THIS IS THE RANDOMNESS ASKED FOR IN THE ASSIGNMENT AND
 * USED IN THE FINAL ALGORITHM.
 * 
 * @author millerns
 * 
 */
public class RandomLSystem extends LSystem {

	protected int percentLeft;
	protected String leftTurn;
	protected String rightTurn;

	public RandomLSystem(String axiom, HashMap<String, String> production,
			double angleSize, double scaleFactor, int percentLeft,
			String leftTurn, String rightTurn) {
		super(axiom, production, angleSize, scaleFactor);
		this.percentLeft = percentLeft;
		this.leftTurn = leftTurn;
		this.rightTurn = rightTurn;
	}

	@Override
	public String iterate() {
		int length = this.iteration.length();
		Random r = new Random();
		String nextIteration = "";
		for (int x = 0; x < length; x++) {
			String nextSymbol = this.iteration.substring(x, x + 1);
			if (nextSymbol.equals("F")) {
				nextIteration = nextIteration + this.applyRandomTurn(r);
			} else
				nextIteration = nextIteration + this.production.get(nextSymbol);
		}
		this.iteration = nextIteration;
		this.iterationNumber++;
		return this.iteration;
	}

	private String applyRandomTurn(Random r) {
		if (r.nextInt(100) < this.percentLeft) {
			return this.leftTurn;
		} else
			return this.rightTurn;
	}

}
