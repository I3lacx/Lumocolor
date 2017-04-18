/**
 * 
 */
package maxisTestPack.inventory;

/**
 * @author Joschka
 *
 */
public class SwordItem extends Item {
	private int damage;

	public SwordItem() {
		super("Sword", "/sword.png");
	}
	
	//for special swords
	public SwordItem(String name, String path) {
		super(name, path);
	}
}
