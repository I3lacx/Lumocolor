/**
 * 
 */
package joschkasTestPack;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Joschido
 *
 */
public class Tile{
	
	public static int TILESIZE = 32;
	BufferedImage img;
	
	/**
	 * Standard is Grass.
	 */
	public Tile() {
		new Grass();
	}
	
	public Tile(String url) {
		try {
			img = ImageIO.read(Precious.class.getResourceAsStream(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getBufferedImage() {
		return img;
	}

}
