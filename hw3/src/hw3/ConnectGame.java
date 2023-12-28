package hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 * @author clab22
 */
public class ConnectGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	/**
	 * grabs the width of the grid
	 */
	private int width;
	/**
	 * grabs the height of the grid
	 */
	private int height;
	/**
	 * stores the minimum level of the tiles
	 */
	private int min;
	/**
	 * stores the max level of tiles
	 */
	private int max;
	/**
	 * stores the score variable
	 */
	private long score=0;
	/**
	 * creates the rand var to hold
	 */
	private Random rand;
	/**
	 * creates the grid object in the connect game class
	 */
	private Grid grid;
	/**
	 * sees if the first tile has been selected
	 */
	private boolean started;
	/**
	 * holds all of the selected tiles in an array list
	 */
	private List<Tile> selected = new ArrayList<Tile>();

	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		// TODO
		this.width = width;
		this.height = height;
		this.min = min;
		this.max = max;
		this.rand = rand;
		grid = new Grid(width,height);
		
		
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		int randomNum = rand.nextInt(max - min) + min;
		Tile tile = new Tile(randomNum);
		return tile;
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		for(int i = 0;i < width; i++) {
			for(int j = 0; j < height;j++) {
				grid.setTile(getRandomTile(),i,j);
			}
		}
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		int t1x = t1.getX();
		int t1y = t1.getY();
		int t2x = t2.getX();
		int t2y = t2.getY();
		if(t1x == (t2x - 1) && t1y == (t2y + 1)) {
			return true;
		}
		if(t1x == (t2x - 1) && t1y == t2y) {
			return true;
		}
		if(t1x == (t2x - 1) && t1y == (t2y - 1)) {
			return true;
		}
		if(t1x == t2x && t1y == (t2y - 1)) {
			return true;
		}
		if(t1x == (t2x + 1) && t1y == (t2y - 1)) {
			return true;
		}
		if(t1x == (t2x + 1) && t1y == t2y) {
			return true;
		}
		if(t1x == (t2x +1 ) && t1y == (t2y + 1)) {
			return true;
		}
		if(t1x == t2x && t1y == (t2y + 1)) {
			return true;
		}
		return false;
	}
	
	

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		if(grid.getTile(x, y).isSelected() == false && started == false) {
			Tile tile = grid.getTile(x, y);
			tile.setSelect(true);
			grid.setTile(tile, x, y);
			selected.add(tile);
			started = true;
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * 2 2 2 2 4 4 8
	 */
	public void tryContinueSelect(int x, int y) {
		// TODO
		Tile tile = grid.getTile(x, y);
		if(started == true) {
		if(isAdjacent(tile, selected.get(selected.size() -1))) {
		if(tile.getLevel() == selected.get(selected.size()-1).getLevel()) {
			selected.add(tile);
			tile.setSelect(true);
			return;
		}
		if(selected.size() !=1) {
		if(tile.getLevel() - 1 == selected.get(selected.size()-1).getLevel() && tile.getLevel()-1 == selected.get(selected.size()-2).getLevel()-1) {
			selected.add(tile);
			tile.setSelect(true);
		}else {
			return;
		}}
		}
	}}
	

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		//Tile[] selected = getSelectedAsArray();
		if(selected.get(selected.size()-1) == grid.getTile(x, y)) {
			if(selected.size() == 1) {
				grid.getTile(x, y).setSelect(false);
			}else {
				for(Tile t: selected) {
					score += 2*t.getValue();
					t.setSelect(false);
					scoreListener.updateScore(score);
				}
				scoreListener.updateScore(score);
				upgradeLastSelectedTile();
				dropSelected();
				selected.clear();
		
				
			}
			return true;
		}
		return false;
	}


	
	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		Tile[] selected = getSelectedAsArray();
		selected[selected.length - 1].setLevel(selected[selected.length-1].getLevel() + 1);
		if(selected[selected.length-1].getLevel() == max) {
			max++;
			dialogListener.showDialog("New block " + (int)Math.pow(2,max) + ", removing blocks " + (int)Math.pow(2,min));
			dropLevel(min);
			min++;
		}
		selected[selected.length -1].setSelect(false);
		started = false;
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
		Tile[] arr = new Tile[selected.size()];
		for (int i = 0; i < selected.size(); i++)
            arr[i] = selected.get(i);
		return arr;
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	
	public void dropLevel(int level) {
		// drops all tiles down leaving all the tiles of particular level on top.
		for(int x = 0; x < width; x++) {
			int swapIndex = 0; // this is used to tell where to swap the values to.
			for(int y = 0; y < height; y++) {
				if(grid.getTile(x, y).getLevel() == level) {
					grid.getTile(x, y).setLevel(grid.getTile(x, swapIndex).getLevel());
					grid.getTile(x, swapIndex).setLevel(level);
					swapIndex++;
				} 
			}
		}
		// randomizes all tiles of particular level.
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(grid.getTile(x, y).getLevel() == level) {
					grid.getTile(x, y).setLevel(getRandomTile().getLevel());
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		for (int row = 0; row <  width; row++) {
	        for (int col = 0; col < height; col++) {
	            if (grid.getTile(row,col).isSelected()) {
	                // Remove the tile and move tiles above it down
	                for (int i = row; i > 0; i--) {
	                	Tile temp = grid.getTile(i-1, col);
	                	grid.setTile(temp, i, col);
	                }
	                grid.setTile(getRandomTile(), 0, col);// Add new random tile at top
	            }
	        }
	    }
	}
	


	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		// TODO
		selected.remove(grid.getTile(x, y));
		grid.getTile(x,y).setSelect(false);
		
			
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		// TODO
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		// TODO
		return grid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		// TODO
		return min;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		// TODO
		return max;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		// TODO
		this.score = score;
		
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		// TODO
		min = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		// TODO
		max = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}
}
