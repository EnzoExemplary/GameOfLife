package main;

public class Grid {
	private char[][] grid;
	private int size;
	private final char DEAD_CELL = '.';
	private final char LIVE_CELL = 'x';
	
	public Grid(int size) {
		this.size = size;
		grid = new char[size][size];
		
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if((int)Math.round(Math.random()) == 1){
					grid[i][j] = LIVE_CELL;
				}else {
					grid[i][j] = DEAD_CELL;
				}
			}
		}
	}
	
	public String toString() {
		String string = "";
		
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				string += grid[i][j] + "    ";
			}
			string += "\n";
		}
		
		return string;
	}
	
	public void newGeneration() {
		char[][] tempGrid = new char[size][size];
		
		//For every cell in grid
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				//Count living cells surrounding current cell
				int num_live_cells = 0;
				for(int k = -1; k < 2; k++) {
					for(int l = -1; l < 2; l++) {
						boolean currentCell = (k==0 && l==0);
						boolean negativeBounds = (i+k < 0 || j+l < 0);
						boolean overBounds = (i+k >= size || j+l >= size);
						if(!currentCell && !negativeBounds && !overBounds) {
							int cell = grid[i+k][j+l];
							
							if(cell == LIVE_CELL) {
								num_live_cells++;
							}
						}
					}
				}
				
				//Apply rules and put cell in tempGrid
				char cell = grid[i][j];
				
				if(cell == DEAD_CELL && num_live_cells == 3) {
					tempGrid[i][j] = LIVE_CELL;
				}else if(cell == LIVE_CELL && (num_live_cells < 2 || num_live_cells > 3)) {
					tempGrid[i][j] = DEAD_CELL;
				}else{
					tempGrid[i][j] = cell;
				}
			}
		}
		
		//Set grid to new updated grid
		grid = tempGrid;
	}
}
