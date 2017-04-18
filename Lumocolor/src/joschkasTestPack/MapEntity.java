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
public class MapEntity {
	BufferedImage img;
	
	public MapEntity(String url) {
		try {
			img = ImageIO.read(Precious.class.getResourceAsStream(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public MapEntity() {
		
	}
	
	public BufferedImage getBufferedImage() {
		return img;
	}

}
