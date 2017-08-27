package com.shabanksamani.iTunesScreensaver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GraphicsEnvironment;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.io.FilenameFilter;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.Timer;

public class ITunesPanel extends JPanel {
	
	static final File DIR = new File("res/Album Art");
	static final String[] EXTENSION = new String[]{"jpg", "jpeg"};
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(final File dir, final String name) {
			for(final String ext : EXTENSION) {
				if(name.endsWith("." + ext))
					return true;
			}
			return false;
		}
	};
	static final int MAX_WIDTH = 500;
	static final int MAX_HEIGHT = 500;
	static final int SCREEN_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	static final int SCREEN_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	
	ArrayList<BufferedImage> imgList;
	ArrayList<JLabel> labelList;
	static Timer timer;
	
	
	public ITunesPanel() throws IOException {
		super(new GridLayout(SCREEN_WIDTH/MAX_WIDTH, SCREEN_HEIGHT/MAX_HEIGHT, 0, 0));
		
		this.setBackground(Color.black);
		imgList = new ArrayList<BufferedImage>();
		labelList = new ArrayList<JLabel>();
		if(DIR.isDirectory()) {
			for(final File f : DIR.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;
				
				try {
					img = ImageIO.read(f);
					Image resizedImg = img.getScaledInstance(MAX_WIDTH, MAX_HEIGHT, Image.SCALE_SMOOTH);
					BufferedImage dimg = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2 = dimg.createGraphics();
					
					//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g2.drawImage(resizedImg, 0, 0, null);
					g2.dispose();
					imgList.add(dimg);
				} catch(final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		int labelIndex = 0;
		if(imgList.isEmpty()) System.out.println("Image Directory not found!");
		else {
			for(final BufferedImage img : imgList) {
				JLabel imageLabel = new JLabel(new ImageIcon(img), JLabel.CENTER);
				labelList.add(imageLabel);
				this.add(labelList.get(labelIndex));
				labelIndex++;
			}
		}
		
		int sizeOfDirectory = imgList.size() - 1;
		int sizeofLabelList = labelList.size() - 1;
		
		
		Timer timer = new Timer(5000, event -> {
			double randomSource = Math.random() * sizeOfDirectory;
			double randomTarget = Math.random() * sizeofLabelList;
			JLabel toReplace = labelList.get((int) randomTarget);
			BufferedImage img = imgList.get((int) randomSource);
			toReplace.setIcon(new ImageIcon(img));
		});
		
		timer.start();
		
		

//		while(!stopRunning) {
//			double randomSource = Math.random() * sizeOfDirectory;
//			double randomTarget = Math.random() * sizeofLabelList;
//			
//			Timer timer = new Timer(500, event -> {
//				JLabel toReplace = labelList.get((int) randomTarget);
//				BufferedImage img = imgList.get((int) randomSource);
//				this.remove(toReplace);
//				toReplace.setIcon(new ImageIcon(img));
//				this.add(toReplace);
//			});
//		}
	}
	
	

}
