package maxisTestPack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import maxisTestPack.entity.*;

/**
 * Handles the Key Actions for the Player
 * 
 * @author Maximilian Otte
 *
 */
public class PlayerEvents implements KeyListener, GameField {

	Player player;

	public PlayerEvents(Player player) {
		this.player = player;
		System.out.println(player);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(player.getDead())
			return;
		
		
		// Left
		if (e.getKeyCode() == 37) {
			player.turn('l');
		}
		// Up
		if (e.getKeyCode() == 38) {
			player.turn('u');
		}
		// Right
		if (e.getKeyCode() == 39) {
			player.turn('r');
		}
		// Down
		if (e.getKeyCode() == 40) {
			player.turn('d');
		}

		//Spacebar
		if (e.getKeyCode() == 32) {
			player.action();
		}
		
		//sets the numbers 1-8 to set the Active Slot to the number
		if(e.getKeyCode() >= 49 && e.getKeyCode() <= 56) {
			player.getInv().setActiveSlot(e.getKeyCode()-49);
		}
		
		//S for save
		if(e.getKeyCode() == 83){
			player.save();
		}
		
		//L for save
		if(e.getKeyCode() == 76){
			player.load();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

}
