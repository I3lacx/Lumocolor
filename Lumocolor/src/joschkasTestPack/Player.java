/**
 * 
 */
package joschkasTestPack;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;


/**
 * @author Joschido
 *
 */
public class Player extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static String UPMODEL = "/images/playerUp1.png";
	private static String DOWNMODEL = "/images/playerDown1.png";
	private static String RIGHTMODEL = "/images/playerRight1.png";
	private static String LEFTMODEL = "/images/playerLeft1.png";
	
	private BufferedImage pModel;
	private int posX;
	private int posY;
	private char facing;
	private TileMap map;
	private long lastKeyPress;
	
	
	public Player(TileMap tM) {
		setFocusable(true);
		setBufferedImage(DOWNMODEL);
		posX = 0;
		posY = 0;
		facing = 'd';
		map = tM;
		addKeyListener(new PlayerEvents(this));
	}
	
	public void movePlayer(String direction) {
		System.out.println("hi");

		int x = 0;
		int y = 0;

		// Choose direction
		switch (direction) {
		case "Up":
			if (facing == 'u')
				y = -1;
			facing = 'u';
			setBufferedImage(UPMODEL);
			break;
		case "Down":
			if (facing == 'd')
				y = 1;
			facing = 'd';
			setBufferedImage(DOWNMODEL);
			break;
		case "Left":
			if (facing == 'l')
				x = -1;
			facing = 'l';
			setBufferedImage(LEFTMODEL);
			break;
		case "Right":
			if (facing == 'r')
				x = 1;
			facing = 'r';
			setBufferedImage(RIGHTMODEL);
			break;
		case "Stay":
			break;
		default:
			System.err.println("DIRECTION NOT FOUND");
			break;
		}
		
		lastKeyPress = System.currentTimeMillis();
		 //calculate newX,newY and newPosX,newPosY
		int newPosX = posX + x;
		int newPosY = posY + y;
		// checks if player is allowed to move (out of map)
		if (newPosX < 0 || newPosX >= TileMap.MAPWIDTH || newPosY < 0 || newPosY >= TileMap.MAPHEIGHT) {
			System.out.println("Can't move: Out of world");
			return;
		}

		if (map.getTile(newPosX, newPosY).getClass() != Grass.class) {
			System.out.println("Can't move: Object in the way");
			return;
		}

		// save the new location and move there
		posX = newPosX;
		posY = newPosY;
		System.out.println("posX: " + posX + " ,posY: " + posY);
//		this.setLocation(posX * TILE_SIZE, posY * TILE_SIZE);
//		repaint();
	
	
	}
	
	private void setBufferedImage(String url) {
		try {
			pModel = ImageIO.read(Precious.class.getResourceAsStream(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public BufferedImage getBufferedImage() {
		return pModel;
	}

}
