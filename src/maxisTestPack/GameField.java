package maxisTestPack;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public interface GameField {
	
	/**
	 * View range of player -> how many blocks will be visible, 
	 * VIEW_RANGE in every direction -> always (VIEW_RANGE*2+1)^2
	 */
	public static final int VIEW_RANGE = 10;
	public static final int TILE_SIZE = 32;
	
	public static final int LAYERS = 3;
	public static final int DEFAULT_WIDTH = 1024;
	public static final int DEFAULT_HEIGHT = 1024;
	
	public static final int DISPLAY_SIZE = VIEW_RANGE * TILE_SIZE * 2;
	
	
	public static final int HUD_HEIGHT = 32;
	public static final int WORLD_WIDTH = 16;
	public static final int WORLD_HEIGHT = 16;


	
	/**
	 * Anzahl von Tiles auf der x Achse
	 */
	public static final int GRID_WIDTH = DEFAULT_WIDTH/TILE_SIZE;
	/**
	 * Anzahl von Tiles auf der y Achse
	 */
	public static final int GRID_HEIGHT = DEFAULT_HEIGHT/TILE_SIZE;
	
	
	Dimension defaultDim = new Dimension(DISPLAY_SIZE, DISPLAY_SIZE + HUD_HEIGHT);

	public static BufferedImage setImage(String path) {
		BufferedImage image;
		
		try {
			image = ImageIO.read(Main.class.getResourceAsStream(path));
			
		} catch (IOException e) {
			System.out.println("Coudn't find file with path: " + path);
			e.printStackTrace();
			return null;
		}
		
		return image;
	}
}
