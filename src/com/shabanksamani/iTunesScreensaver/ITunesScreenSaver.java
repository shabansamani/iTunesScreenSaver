package com.shabanksamani.iTunesScreensaver;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class ITunesScreenSaver {
	
	public static void createAndShowGUI() throws IOException {
		JFrame frame = new JFrame("iTunes Screensaver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		JPanel panel = new ITunesPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
	} // createAndShowGUI
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				createAndShowGUI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	} // main
} // iTunesScreenSaver
