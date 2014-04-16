package com.kochSnowflake.millerns;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This panel serves as the controller for the program. It takes in input and
 * instructs the DisplayPanel to render a Koch system as indicated.
 * 
 * @author millerns
 * 
 */
@SuppressWarnings("serial")
public class KochSnowflakeControlPanel extends JPanel {

	private KochSnowflakeDisplayPanel displayPanel;
	private int width = 500;
	private int height = 100;
	private Color backgroundColor = Color.LIGHT_GRAY;

	private JTextField iterationField;
	private JTextField percentField;
	private JCheckBox snowflakeBox;
	private JCheckBox randomBox;
	private JButton renderButton;

	public KochSnowflakeControlPanel(int height, int width,
			KochSnowflakeDisplayPanel displayPanel) {
		super();
		this.displayPanel = displayPanel;
		this.height = height;
		this.width = width;
		this.setSize(this.width, this.height);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(this.backgroundColor);
		this.addComponents();
	}

	/**
	 * Instructs the displayPanel to render a Koch system based on given
	 * parameters.
	 * 
	 * @param iterations
	 *            Number of iterations to render
	 * @param snowflake
	 *            Whether to render a curve or a snowflake
	 * @param random
	 *            Whether to render a random or non-random system
	 * @param percentLeft
	 *            If random, then the odds of making a left turn
	 */
	public void renderKoch(int iterations, boolean snowflake, boolean random,
			int percentLeft) {
		if (random) {
			if (snowflake)
				this.displayPanel.renderRandomKochSnowflake(iterations,
						percentLeft);
			else
				this.displayPanel
						.renderRandomKochCurve(iterations, percentLeft);
		} else {
			if (snowflake)
				this.displayPanel.renderKochSnowflake(iterations);
			else
				this.displayPanel.renderKochCurve(iterations);
		}
		this.displayPanel.repaint();
	}

	/**
	 * Handles clicks of the Render button. Reads in the input from the
	 * iterationField, percentField, snowflakeBox and randomBox then instructs
	 * the displayPanel to render a system accordingly.
	 * 
	 * @author millerns
	 * 
	 */
	private class RenderListener implements ActionListener {

		private KochSnowflakeControlPanel controlPanel;
		private JTextField iterationField;
		private JTextField percentField;
		private JCheckBox snowflakeBox;
		private JCheckBox randomBox;

		public RenderListener(KochSnowflakeControlPanel controlPanel,
				JTextField iterationField, JTextField percentField,
				JCheckBox snowflakeBox, JCheckBox randomBox) {
			this.controlPanel = controlPanel;
			this.iterationField = iterationField;
			this.percentField = percentField;
			this.snowflakeBox = snowflakeBox;
			this.randomBox = randomBox;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int iterations = 0;
			boolean snowflake = this.snowflakeBox.isSelected();
			boolean random = this.randomBox.isSelected();
			int percentLeft = 50;

			try {
				iterations = Integer.parseInt(this.iterationField.getText());
			} catch (NumberFormatException e) {
				System.out.println("Bad iteration input.");
			}

			try {
				percentLeft = Integer.parseInt(this.percentField.getText());
			} catch (NumberFormatException e) {
				System.out.println("Bad percent input.");
			}

			this.controlPanel.renderKoch(iterations, snowflake, random,
					percentLeft);
		}
	}

	private void addComponents() {
		this.add(new JLabel("Iterations:"));
		this.setUpIterationField();
		this.setUpSnowflakeBox();
		this.setUpRandomBox();
		this.add(new JLabel("Percent Left:"));
		this.setUpPercentField();
		this.setUpRenderButton();
	}

	private void setUpIterationField() {
		this.iterationField = new JTextField();
		this.iterationField.setSize(this.height, 50);
		this.iterationField.setText("4");
		this.add(this.iterationField);
	}

	private void setUpSnowflakeBox() {
		this.snowflakeBox = new JCheckBox();
		this.snowflakeBox.setText("Snowflake");
		this.snowflakeBox.setSelected(true);
		this.add(this.snowflakeBox);
	}

	private void setUpRandomBox() {
		this.randomBox = new JCheckBox();
		this.randomBox.setText("Random");
		this.randomBox.setSelected(true);
		this.add(this.randomBox);
	}

	private void setUpPercentField() {
		this.percentField = new JTextField();
		this.percentField.setSize(this.height, 50);
		this.percentField.setText("50");
		this.add(this.percentField);
	}

	private void setUpRenderButton() {
		this.renderButton = new JButton();
		this.renderButton.setText("Render");
		this.add(this.renderButton);
		this.renderButton.addActionListener(new RenderListener(this,
				this.iterationField, this.percentField, this.snowflakeBox,
				this.randomBox));
	}
}
