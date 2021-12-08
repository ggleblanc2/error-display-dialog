package com.ggl.error.display.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This {@code JDialog} displays a Java {@code Exception}. The format of the
 * output is the same as the output provided by the {@code printStackTrace}
 * method.
 * 
 * @author Gilbert G. Le Blanc - 8 December 2021
 *
 */
public class ErrorDisplayDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	
	/**
	 * This constructor creates and displays the error display dialog.
	 * 
	 * @param frame - The parent component of the dialog.
	 * @param title - The title of the dialog.
	 * @param e     - The {@code Exception being thrown}
	 */
	public ErrorDisplayDialog(JFrame frame, String title, Exception e) {
		super(frame, title, true);
		add(createMainPanel(e), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}
	
	private JPanel createMainPanel(Exception e) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		textArea = new JTextArea(24, 90);
		textArea.setEditable(false);
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setText(generateException(e));
		textArea.setCaretPosition(0);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JButton button = new JButton("Cancel");
		button.addActionListener(event -> ErrorDisplayDialog.this.dispose());
		panel.add(button);
		
		return panel;
	}
	
	private String generateException(Exception e) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(bytes);
		e.printStackTrace(stream);
		stream.close();
		
		return new String(bytes.toByteArray());
	}

}
