/**
 * 
 */
package maxisTestPack.inventory;

import java.awt.image.BufferedImage;

import maxisTestPack.GameField;

/**
 * @author Joschido
 *
 */
public abstract class Item {
	

	private BufferedImage image;
	private String path;
	public String name;
	
	public Item(String name, String path){
		this.path = path;
		this.name = name;
		image = GameField.setImage(this.path);
	}
	
	public BufferedImage getImage(){
		return image;
	}

}
