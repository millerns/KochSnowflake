package com.kochSnowflake.millerns;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represents an L-System. It stores an axiom, production function,
 * current iteration, and size, and scale factor. It will iterate a specified
 * number of times and return the iteration obtained.
 * 
 * @author millerns
 * @date 4/4/14
 * 
 */
public class LSystem {

	private String axiom;
	protected HashMap<String, String> production;
	protected String iteration;
	protected int iterationNumber;
	protected double angleSize;
	protected double scaleFactor;
	public static final double DEGREE_TO_RADIANS = 0.0174532925;

	public LSystem(String axiom, HashMap<String, String> production,
			double angleSize, double scaleFactor) {
		this.axiom = axiom;
		this.production = production;
		this.iteration = axiom;
		this.angleSize = angleSize;
		this.scaleFactor = scaleFactor;
		this.iterationNumber = 0;
	}

	/**
	 * Iterates once and returns the new iteration.
	 * 
	 * @return the next iteration
	 */
	public String iterate() {
		int length = this.iteration.length();
		String nextIteration = "";
		for (int x = 0; x < length; x++) {
			nextIteration = nextIteration
					+ this.production.get(this.iteration.substring(x, x + 1));
		}
		this.iteration = nextIteration;
		this.iterationNumber++;
		return this.iteration;
	}

	/**
	 * Iterates n times and returns the nth iteration
	 * 
	 * @param n
	 *            number of times to iterate
	 * @return nth iteration
	 */
	public String iterateN(int n) {
		for (int x = 0; x < n; x++) {
			this.iteration = this.iterate();
		}
		return this.iteration;
	}

	/**
	 * Converts the symbol representation of an L-System to a list of plottable
	 * lines. Takes a total size parameter to determine the size of the lines.
	 * 
	 * @param totalSize
	 *            Size of the space available for plotting the system
	 * @return a list of plottable lines
	 */
	public ArrayList<Line2D.Double> toLines(double totalSize) {
		double x = 0;
		double y = 0;
		double currentAngle = 0;
		int systemLength = this.iteration.length();
		double lineLength = totalSize
				* Math.pow(this.scaleFactor, this.iterationNumber);
		ArrayList<Line2D.Double> lineList = new ArrayList<Line2D.Double>();
		for (int i = 0; i < systemLength; i++) {
			String currentSymbol = this.iteration.substring(i, i + 1);
			if (currentSymbol.equals("F")) {
				Point2D.Double nextPoint = moveForward(x, y, lineLength,
						currentAngle);
				lineList.add(new Line2D.Double(new Point2D.Double(x, y),
						nextPoint));
				x = nextPoint.x;
				y = nextPoint.y;
			} else if (currentSymbol.equals("+")) {
				currentAngle -= this.angleSize;
			} else if (currentSymbol.equals("-")) {
				currentAngle += this.angleSize;
			}
		}
		return lineList;
	}

	/**
	 * Does the same as toLines, except introduces randomness whenever turns are
	 * encountered. Generates winding, snaky systems. THIS IS NOT THE RANDOMNESS
	 * REQUESTED IN THE ASSIGNMENT. THIS IS NOT THE RANDOMNESS USED IN THE FINAL
	 * ALGORITHM.
	 * 
	 * @param totalSize
	 *            size available for displaying the system
	 * @param percentLeft
	 *            percent the turn will be left
	 * @return list of plottable lines of the system
	 */
	public ArrayList<Line2D.Double> toLinesRandom(double totalSize,
			int percentLeft) {
		double x = 0;
		double y = 0;
		double currentAngle = 0;
		int systemLength = this.iteration.length();
		double lineLength = totalSize
				* Math.pow(this.scaleFactor, this.iterationNumber);
		ArrayList<Line2D.Double> lineList = new ArrayList<Line2D.Double>();
		Random r = new Random();
		for (int i = 0; i < systemLength; i++) {
			String currentSymbol = this.iteration.substring(i, i + 1);
			if (currentSymbol.equals("F")) {
				Point2D.Double nextPoint = moveForward(x, y, lineLength,
						currentAngle);
				lineList.add(new Line2D.Double(new Point2D.Double(x, y),
						nextPoint));
				x = nextPoint.x;
				y = nextPoint.y;
			} else if (currentSymbol.equals("+")) {
				currentAngle = this.applyRandomTurn(currentAngle, percentLeft,
						r);
			} else if (currentSymbol.equals("-")) {
				currentAngle = this.applyRandomTurn(currentAngle, percentLeft,
						r);
			}
		}

		return lineList;
	}

	/**
	 * Applies a random turn given a starting angle, odds of turning left, and
	 * Random object. Returns the new angle
	 * 
	 * @param initialAngle
	 *            angle to start with
	 * @param percentLeft
	 *            odds of turning left
	 * @param r
	 *            Random object to use
	 * @return new angle
	 */
	private double applyRandomTurn(double initialAngle, int percentLeft,
			Random r) {
		if (r.nextInt(100) < percentLeft) {
			return initialAngle - this.angleSize;
		} else
			return initialAngle + this.angleSize;

	}

	/**
	 * Determines the coordinates that would result from starting at point (x,y)
	 * and moving distance length with the given angle. Returns the new point
	 * reached.
	 * 
	 * @param x
	 *            Initial x coordinate
	 * @param y
	 *            Initial y coordinate
	 * @param length
	 *            Distance to travel
	 * @param currentAngle
	 *            Angle to travel
	 * @return Destination coordinate
	 */
	private Point2D.Double moveForward(double x, double y, double length,
			double currentAngle) {
		return new Point2D.Double(x + (length * Math.cos(currentAngle)), y
				+ (length * Math.sin(currentAngle)));
	}

	public String getIteration() {
		return this.iteration;
	}

	public String getAxiom() {
		return this.axiom;
	}

	public HashMap<String, String> getProduction() {
		return this.production;
	}

	public int getIterationNumber() {
		return this.iterationNumber;
	}
}
