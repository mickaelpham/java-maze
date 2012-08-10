package eu.mpham.maze;


public class Maze {
	
	public enum Type {
		EMPTY, CROSS, HORIZONTAL, VERTICAL, UPPER_LEFT_CORNER,
		UPPER_RIGHT_CORNER, LOWER_LEFT_CORNER, LOWER_RIGHT_CORNER,
		VERTICAL_HALF_LEFT, VERTICAL_HALF_RIGHT, HORIZONTAL_HALF_TOP,
		HORIZONTAL_HALF_BOTTTOM, HALF_TOP, HALF_RIGHT, HALF_BOTTOM, HALF_LEFT
	}

	// Dimensions of the maze
	private int columns;
	private int rows;
	
	// Horizontal and vertical interior walls (each is true if the wall exists)
	private boolean[][] hWalls;
	private boolean[][] vWalls;
	
	// Object for generating random numbers
//	private Random random = new Random();
	
	public Maze(int rows, int columns) {
		this.columns = columns;
		this.rows = rows;
		this.hWalls = new boolean[rows + 2][columns + 2];
		this.vWalls = new boolean[rows + 2][columns + 2];
		emptyMaze();
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	private void emptyMaze() {
		// Include the extra boundaries
		for (int row = 0; row <= (rows + 1); row++) {
			for (int column = 0; column <= (columns + 1); column++) {
				// First or last row -> horizontal wall
				if (row == 1 || row == rows)
					hWalls[row][column] = true;
				else
					hWalls[row][column] = false;
				
				// First or last column -> vertical wall
				if (column == 1 || column == columns)
					vWalls[row][column] = true;
				else
					vWalls[row][column] = false;
			}
		}
		// Correcting the boundaries of first/last row
		hWalls[1][0] = false;
		hWalls[1][columns + 1] = false;
		hWalls[rows][0] = false;
		hWalls[rows][columns + 1] = false;
		
		// Correcting the boundaries of first/last column
		vWalls[0][1] = false;
		vWalls[rows + 1][1] = false;
		vWalls[0][columns] = false;
		vWalls[rows + 1][columns] = false;
	}
	
	public Type getType(int row, int column) {
		if (hWalls[row][column] && vWalls[row][column]) {
			// Determine what kind of cross is this
			if (hWalls[row][column - 1] && hWalls[row][column + 1]
					&& vWalls[row - 1][column] && vWalls[row + 1][column])
				return Type.CROSS;
			else if (hWalls[row][column - 1] && hWalls[row][column + 1]
					&& vWalls[row - 1][column])
				return Type.HORIZONTAL_HALF_TOP;
			else if (hWalls[row][column - 1] && hWalls[row][column + 1]
					&& vWalls[row + 1][column])
				return Type.HORIZONTAL_HALF_BOTTTOM;
			else if (hWalls[row][column - 1] && hWalls[row][column + 1])
				return Type.HORIZONTAL;
			else if (vWalls[row - 1][column] && vWalls[row + 1][column]
					&& hWalls[row][column - 1])
				return Type.VERTICAL_HALF_LEFT;
			else if (vWalls[row - 1][column] && vWalls[row + 1][column]
					&& hWalls[row][column+ 1])
				return Type.VERTICAL_HALF_RIGHT;
			else if (vWalls[row - 1][column] && vWalls[row + 1][column])
				return Type.VERTICAL;
			else if (hWalls[row][column - 1] && vWalls[row - 1][column])
				return Type.UPPER_LEFT_CORNER;
			else if (hWalls[row][column - 1] && vWalls[row + 1][column])
				return Type.LOWER_LEFT_CORNER;
			else if (hWalls[row][column + 1] && vWalls[row - 1][column])
				return Type.UPPER_RIGHT_CORNER;
			else if (hWalls[row][column + 1] && vWalls[row + 1][column])
				return Type.LOWER_RIGHT_CORNER;
			else
				return Type.EMPTY;
			
		} else if (hWalls[row][column]) {
			// Determine what kind of horizontal line is this
			if (hWalls[row][column - 1] && hWalls[row][columns + 1])
				return Type.HORIZONTAL;
			else if (hWalls[row][column - 1])
				return Type.HALF_LEFT;
			else if (hWalls[row][column + 1])
				return Type.HALF_RIGHT;
			else
				return Type.EMPTY;
			
		} else if (vWalls[row][column]) {
			// Determine what kind of vertical line is this
			if (vWalls[row - 1][column] && vWalls[row + 1][column])
				return Type.VERTICAL;
			else if (vWalls[row - 1][column])
				return Type.HALF_TOP;
			else if (vWalls[row + 1][column])
				return Type.HALF_BOTTOM;
			else
				return Type.EMPTY;
			
		} else {
			// Empty case
			return Type.EMPTY;
		}
	}

}
