package eu.mpham.maze;

import eu.mpham.maze.gui.MainFrame;

/**
 * This class represents the main entry point for the Maze application.
 * @author Mickael Pham <mickael.pham@outlook.com>
 */
public class App {
	
	/** Size of the maze (height) */
	public static final int ROWS = 10;
	
	/** Size of the maze (width) */
	public static final int COLUMNS = 20;
	
	private Maze maze;
	
	public App() {
		this.maze = new Maze(ROWS, COLUMNS);
	}
	
	public void proceed() {
		new MainFrame(maze);
		
//		for (int row = 1; row <= maze.getRows(); row ++) {
//			for (int column = 1; column <= maze.getColumns(); column++) {
//				if (column == maze.getColumns())
//					System.out.println(maze.getType(row, column));
//				else
//					System.out.print(maze.getType(row, column) + ", ");
//			}
//		}
	}

	public static void main(String[] args) {
		new App().proceed();
	}

}
