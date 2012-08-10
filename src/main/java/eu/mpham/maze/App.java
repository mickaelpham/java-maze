package eu.mpham.maze;

public class App {

	public static void main(String[] args) {
		Maze maze = new Maze(5, 9);
		for (int row = 1; row <= maze.getRows(); row ++) {
			for (int column = 1; column <= maze.getColumns(); column++) {
				if (column == maze.getColumns())
					System.out.println(maze.getType(row, column));
				else
					System.out.print(maze.getType(row, column) + ", ");
			}
		}
	}

}
