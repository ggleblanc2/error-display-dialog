package com.ggl.error.display;

import javax.swing.SwingUtilities;

import com.ggl.error.display.view.ErrorDisplayFrame;

public class ErrorDisplay implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ErrorDisplay());
	}

	@Override
	public void run() {
		new ErrorDisplayFrame();
	}

}
