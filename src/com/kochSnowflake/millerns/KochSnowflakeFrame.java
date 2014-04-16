package com.kochSnowflake.millerns;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * This frame displays the Koch Snowflake program. It contains a control panel
 * and a display panel. The control panel allows the user to specify the number
 * of iterations, whether to render a curve or a snowflake, whether to make
 * random turns, and if so the odds of making a left turn. The display panel
 * renders the specified system.
 * 
 * @author millerns
 * 
 */
@SuppressWarnings("serial")
public class KochSnowflakeFrame extends JFrame {

	private static int DEFAULT_HEIGHT = 1080;
	private static int DEFAULT_WIDTH = 1080;
	private static int DEFAULT_CONTROL_HEIGHT = 100;
	private static int DEFAULT_DISPLAY_SIZE = DEFAULT_HEIGHT
			- DEFAULT_CONTROL_HEIGHT;
	private static int DEFAULT_CONTROL_WIDTH = DEFAULT_WIDTH;

	private KochSnowflakeControlPanel controlPanel;
	private KochSnowflakeDisplayPanel displayPanel;

	public KochSnowflakeFrame() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("Koch Snowflake");
		this.displayPanel = new KochSnowflakeDisplayPanel(DEFAULT_DISPLAY_SIZE);
		this.controlPanel = new KochSnowflakeControlPanel(
				DEFAULT_CONTROL_HEIGHT, DEFAULT_CONTROL_WIDTH,
				this.displayPanel);

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.PAGE_START, this.controlPanel);
		this.add(BorderLayout.CENTER, this.displayPanel);
	}
}
