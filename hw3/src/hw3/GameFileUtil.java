package hw3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 * @author clab22
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			// TODO: write to file, can use writer.write()
			Grid grid = game.getGrid();
			int height = grid.getHeight();
			int width = grid.getWidth();
			int minTileLevel = game.getMinTileLevel();
			int maxTileLevel = game.getMaxTileLevel();
			long score = game.getScore();
			
			// write game data
			writer.write(width + " " + height + " " + minTileLevel + " " + maxTileLevel + " " + score  + "\n");
			
			
			// write game grid
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					String space = " ";
					if(j == width - 1) {
						space = "";
					}
					writer.write(grid.getTile(j, i).getLevel() + space);
				}
				if(i != height - 1) {
					writer.write("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, ConnectGame game) {
		File file = new File(filePath);
		Scanner scnr;
		try {
			scnr = new Scanner(file);
			int width = Integer.parseInt(scnr.next());
			int height = Integer.parseInt(scnr.next());
			int min = Integer.parseInt(scnr.next());
			int max = Integer.parseInt(scnr.next());
			long score = Long.parseLong(scnr.next());
			game.setGrid(new Grid(width, height));
			game.setMinTileLevel(min);
			game.setMaxTileLevel(max);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
