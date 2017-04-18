/**
 * 
 */
package maxisTestPack.headUpDisplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import maxisTestPack.GameField;
import maxisTestPack.GamePanel;
import maxisTestPack.Main;
import maxisTestPack.entity.Player;
import maxisTestPack.inventory.AxeItem;
import maxisTestPack.inventory.Inventory;
import maxisTestPack.inventory.Item;
import maxisTestPack.inventory.RockItem;
import maxisTestPack.inventory.WoodItem;

/**
 * @author Joschido
 *
 */
public class HUD {
	
	private Inventory inv;
	private BufferedImage hud;
	private Player player;
	private final int HEALTH_BAR_WIDTH = 160;
	private final int HEALTH_BAR_HEIGHT = 16;
	
	private BufferedImage buffImg;
	private String healthBarPath = "/AlternativeHB.png";
	
	public HUD(Player player) {
		hud = GameField.setImage("/PlayerHUD.png");
		inv = player.getInv();
		this.player = player;
	}

	/**
	 * the draw function which will be called
	 * from higher places
	 * @param graph what you see in the end
	 */
	public void draw(Graphics graph) {
		
		graph.drawImage(
				hud, 0,
				GameField.DISPLAY_SIZE, null);

		drawItems(graph);
		drawHealth(graph);
		
		int active = inv.getActiveSlot();
		graph.setColor(Color.RED);
		graph.drawRect(HEALTH_BAR_WIDTH + active*32 + 1 , GameField.DISPLAY_SIZE + 1, 30, 30);
		graph.drawRect(HEALTH_BAR_WIDTH + active*32 + 2 , GameField.DISPLAY_SIZE + 2, 28, 28);
	}
	
	/**
	 * draws the items
	 * @param graph
	 */
	private void drawItems(Graphics graph){
		Item item;
		BufferedImage itemImage;
		
		for(int i = 0; i<8; i++){
			item = inv.checkSlot(i, 0);
			if(item != null) {
				itemImage = item.getImage();
				graph.drawImage(
						itemImage, i*32 + HEALTH_BAR_WIDTH ,
						GameField.DISPLAY_SIZE, null);
				if(inv.getSlotCount(i, 0) > 1)
					graph.drawString("" + inv.getSlotCount(i, 0), i*32 + HEALTH_BAR_WIDTH + 3, GameField.DISPLAY_SIZE + 27);
			}
		}
	}
	
	/**
	 * draw health bar with player health
	 * @param g
	 */
	private void drawHealth(Graphics g){
		
		int health = player.getHealth();
		int maxHealth = player.getMaxHealth();
		BufferedImage graphicImg;
		int healthWidth = health * HEALTH_BAR_WIDTH / maxHealth;
		if (healthWidth < 1) healthWidth = 1;
		
		try {
			graphicImg = ImageIO.read(GamePanel.class.getResourceAsStream(healthBarPath));
//			graphicImg = graphicImg.getSubimage(0, health * HEALTH_BAR_HEIGHT, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
			graphicImg = graphicImg.getSubimage(0, 0, healthWidth, HEALTH_BAR_HEIGHT);

			g.drawImage(graphicImg, 0 ,GameField.DISPLAY_SIZE, null);

		} catch (Exception e) {
			System.out.println("Coudn't find file with path: " +healthBarPath);
			e.printStackTrace();
		}
	}
	
}
