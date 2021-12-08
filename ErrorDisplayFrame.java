package com.ggl.error.display.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ErrorDisplayFrame {
	
	private final JFrame frame;

	public ErrorDisplayFrame() {
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Error Display Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(createMainPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 100));
		
		JButton button = new JButton("Generate Exception");
		button.addActionListener(event -> {
			try {
				throwException();
			} catch (SQLException e) {
				new ErrorDisplayDialog(frame, "Error Message Dialog", e);
			}			
		});
		
		panel.add(button, BorderLayout.CENTER);
		
		return panel;
	}
	
	private void throwException() throws SQLException {
		throw new SQLException("This is a test SQL Exception");
	}

}
