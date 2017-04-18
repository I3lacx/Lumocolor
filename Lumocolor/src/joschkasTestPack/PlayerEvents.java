/**
 * 
 */
package joschkasTestPack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Joschido
 *
 */
public class PlayerEvents implements KeyListener {
	Player player;
	
	public PlayerEvents(Player p) {
		player = p;
	}

	@Override
	public void keyPressed(KeyEvent e) {
//
//		// Left
//		if (e.getKeyCode() == 37) {
//			player.movePlayer("Left");
//		}
//		// Up
//		if (e.getKeyCode() == 38) {
//			player.movePlayer("Up");
//		}
//		// Right
//		if (e.getKeyCode() == 39) {
//			player.movePlayer("Right");
//		}
//		// Down
//		if (e.getKeyCode() == 40) {
//			player.movePlayer("Down");
//		}
		
//		if (e.getKeyCode() == 32) {
//			player.smashRock();
//		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {


		// Left
		if (e.getKeyCode() == 37) {
			player.movePlayer("Left");
		}
		// Up
		if (e.getKeyCode() == 38) {
			player.movePlayer("Up");
		}
		// Right
		if (e.getKeyCode() == 39) {
			player.movePlayer("Right");
		}
		// Down
		if (e.getKeyCode() == 40) {
			player.movePlayer("Down");
		}
//		
////		if (e.getKeyCode() == 32) {
////			player.smashRock();
////		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
//
//		// Left
//		if (e.getKeyCode() == 37) {
//			player.movePlayer("Left");
//		}
//		// Up
//		if (e.getKeyCode() == 38) {
//			player.movePlayer("Up");
//		}
//		// Right
//		if (e.getKeyCode() == 39) {
//			player.movePlayer("Right");
//		}
//		// Down
//		if (e.getKeyCode() == 40) {
//			player.movePlayer("Down");
//		}
		
	}

}
