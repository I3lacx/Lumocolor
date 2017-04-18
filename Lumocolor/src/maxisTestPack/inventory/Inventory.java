/**
 * 
 */
package maxisTestPack.inventory;

/**
 * @author Joschido
 *
 */
public class Inventory {

	/**
	 * 
	 * 
	 */
	private int[][] slotCount = new int[8][8];
	private Item[][] inv = new Item[8][8];
	private int activeSlot;

	public Inventory() {
		activeSlot = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				getSlotCount()[i][j] = 0;
	}

	public void setActiveSlot(int s) {
		activeSlot = s;
	}

	public int getActiveSlot() {
		return activeSlot;
	}

	public int getSlotCount(int x, int y) {
		return slotCount[x][y];
	}

	/**
	 * Get the item of the active slot
	 * 
	 * @return Item of the active slot with the highest index.
	 */
	public Item getItem() {
		if (getInv()[activeSlot][0] == null) {
			System.err.println("Empty slot");
			return null;
		} else {
			return getInv()[activeSlot][0];
		}
	}

	/**
	 * returns true if item is in Players inventory
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkItem(Item item) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (getInv()[j][i] != null && getInv()[j][i].getClass() == item.getClass())
					return true;

		return false;
	}

	/**
	 * adds an item to the lowest possible (free) index of the active slot
	 * 
	 * @param item
	 *            the item to add
	 */
	public void addItem(Item item) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getInv()[j][i] == null) {
					getInv()[j][i] = item;
					getSlotCount()[j][i] = 1;
					return;
				} else if (getInv()[j][i] != null && getInv()[j][i].getClass() == item.getClass()) {
					getSlotCount()[j][i]++;
					return;
				}
			}
		}
		System.err.println("No Inventory space");
	}

	/**
	 * Removes the item of the active slot with the higest index.
	 */
	public void removeItem(Item item) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getInv()[j][i] != null && getInv()[j][i].getClass() == item.getClass()) {
					getSlotCount()[j][i]--;

					if (getSlotCount()[j][i] == 0) {
						getInv()[j][i] = null;
					}
					return;
				}
			}
		}
		System.err.println("Item to delete not found");
	}

	public int getCount(Item item) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getInv()[j][i] != null && getInv()[j][i].getClass() == item.getClass()) {
					return getSlotCount()[j][i];
				}
			}
		}
		return 0;
	}

	/**
	 * checks for item at specific slot for drawing the items
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Item checkSlot(int x, int y) {
		if (x > 7 || y > 7 || getInv()[x][y] == null) {
			return null;
		}
		return getInv()[x][y];

	}

	/**
	 * saves the Inv into a String array
	 */
	public String[][] saveInv() {
		String[][] out = new String[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(getInv()[i][j] == null)
					out[i][j] = "";
				else 
					out[i][j] = getInv()[i][j].name;
			}
		}

		return out;
	}
	

	/**
	 * If Specific Item is equipped return true
	 * 
	 * @param item
	 * @return
	 */
	public boolean itemEquipped(Item item) {
		if (getInv()[activeSlot][0] != null && getInv()[activeSlot][0].getClass() == item.getClass()) {
			return true;
		}
		return false;
	}

	public Item[][] getInv() {
		return inv;
	}

	public void setInv(Item[][] inv) {
		this.inv = inv;
	}

	public int[][] getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(int[][] slotCount) {
		this.slotCount = slotCount;
	}
	

}
