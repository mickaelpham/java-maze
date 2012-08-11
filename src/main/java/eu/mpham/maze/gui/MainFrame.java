package eu.mpham.maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.mpham.maze.App;
import eu.mpham.maze.Maze;

public class MainFrame extends JFrame {

	// Dimension for the images that represents the different cases (in px)
	private static final int IMG_WIDTH = 40;
	
	// Idem for the height (in px)
	private static final int IMG_HEIGHT = 40;
	
	private Maze maze;
	
	// Serial ID
	private static final long serialVersionUID = 1L;

	private Map<Maze.Type, BufferedImage> images;
	
	public MainFrame(Maze maze) {
		
		this.maze = maze;
		
		// Main characteristics for the frame
		int width = App.COLUMNS * IMG_WIDTH + 50;
		int height = App.ROWS * IMG_HEIGHT + 100;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setTitle("Maze 4 Java");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		loadImages();
		
		this.setContentPane(new ImagePanel());
		this.setVisible(true);
	}
	
	private void loadImages() {
		// Create the HashMap
		this.images = new HashMap<Maze.Type, BufferedImage>();

		for (Maze.Type type : Maze.Type.values()) {
			try {
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/" + type));
				this.images.put(type, image);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g) {
			for (int row = 1; row <= maze.getRows(); row++) {
				for (int column = 1; column <= maze.getColumns(); column++) {
					int x = (column - 1) * IMG_WIDTH;
					int y = (row - 1) * IMG_HEIGHT;
					BufferedImage image = images.get(maze.getType(row, column));
					g.drawImage(image, x, y, IMG_WIDTH, IMG_HEIGHT, this);
				}
			}
			
		}
		
	}

}
