package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 * @author clab22
 */
public class Grid {
	/**
	 * grabs the width of the grid
	 */
	private int width = 0;
	/**
	 * grabs the height of the grid
	 */
	private int height = 0;
	/**
	 * creates the grid object
	 */
	private Tile[][] grid;
	
	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height) {
		// TODO
		this.width = width;
		this.height = height;
		grid = new Tile[width][height];
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y) {
		// TODO
		return grid[x][y];
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
		// TODO
		grid[x][y] = tile;
		tile.setLocation(x, y);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}
