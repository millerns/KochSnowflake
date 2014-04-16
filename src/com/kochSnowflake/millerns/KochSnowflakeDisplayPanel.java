package com.kochSnowflake.millerns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This panel displays L-Systems when requested. It creates L-System objects,
 * instructs them to iterate a specified number of times, retrieves from them a
 * list of lines to plot, then plots the lines for the user to see. It contains
 * a list of points that are plotted whenever the panel is painted. This list is
 * updated whenever a new system is generated.
 * 
 * @author millerns
 * 
 */
@SuppressWarnings("serial")
public class KochSnowflakeDisplayPanel extends JPanel {

	private int panelSize;
	private int renderSize;
	private ArrayList<Line2D.Double> lineList = null;
	private int leftMargin;
	private int topMargin;

	private Color backgroundColor = Color.WHITE;
	private Color lineColor = Color.BLACK;

	public KochSnowflakeDisplayPanel(int panelSize) {
		this.panelSize = panelSize;
		this.renderSize = (int) (panelSize * .85);
		this.leftMargin = renderSize / 10;
		this.topMargin = (int) (this.panelSize / 3.8);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(this.backgroundColor);
		this.renderDefaultImage();
	}

	// renders an image on start-up
	private void renderDefaultImage() {
		this.renderRandomKochSnowflake(4, 50);
	}

	/**
	 * Plots the current list of lines whenever the component is painted.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(lineColor);
		if (this.lineList != null) {
			for (Line2D.Double l : this.lineList) {
				this.drawLine(l, g2d);
			}
		}
	}

	/**
	 * Draws a line.
	 * 
	 * @param line
	 *            The line to draw
	 * @param g
	 *            The graphics object to draw with
	 */
	private void drawLine(Line2D.Double line, Graphics g) {
		g.drawLine(xAdjust(line.x1), yAdjust(line.y1), xAdjust(line.x2),
				yAdjust(line.y2));
	}

	// Adjusts x-coordinates to their proper place on the panel
	private int xAdjust(double x) {
		return (int) x + this.leftMargin;
	}

	// Adjusts y-coordinates to their proper place on the panel
	private int yAdjust(double y) {
		return (-1 * (int) y) + this.topMargin;
	}

	/**
	 * Renders a given L-System at a given number of iterations.
	 * 
	 * @param system
	 *            L-System to render
	 * @param iterations
	 *            Number of iterations to render
	 */
	public void renderSystem(LSystem system, int iterations) {
		system.iterateN(iterations);
		this.lineList = system.toLines(this.renderSize);
	}

	/**
	 * Renders a standard Koch Curve
	 * 
	 * @param iterations
	 *            Number of iterations to render
	 */
	public void renderKochCurve(int iterations) {
		this.renderSystem(new KochCurveLSystem(), iterations);
	}

	/**
	 * Renders a Koch Curve with random turns
	 * 
	 * @param iterations
	 *            Number of iterations to render
	 * @param percentLeft
	 *            Odds of making a left turn
	 */
	public void renderRandomKochCurve(int iterations, int percentLeft) {
		this.renderSystem(new KochCurveRandomLSystem(percentLeft), iterations);
	}

	/**
	 * Renders a standard Koch Snowflake
	 * 
	 * @param iterations
	 *            Number of iterations to render
	 */
	public void renderKochSnowflake(int iterations) {
		this.renderSystem(new KochSnowflakeLSystem(), iterations);
	}

	/**
	 * Renders a Koch Snowflake with random turns
	 * 
	 * @param iterations
	 *            Number of iterations to render
	 * @param percentLeft
	 *            Odds of making a left turn
	 */
	public void renderRandomKochSnowflake(int iterations, int percentLeft) {
		this.renderSystem(new KochSnowflakeRandomLSystem(percentLeft),
				iterations);
	}
}
