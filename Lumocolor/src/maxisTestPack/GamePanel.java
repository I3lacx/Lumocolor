package maxisTestPack;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import maxisTestPack.tileMap.Map;
import maxisTestPack.tileMap.Map1;
import maxisTestPack.entity.Grumpl;
import maxisTestPack.entity.Player;
import maxisTestPack.headUpDisplay.HUD;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements GameField {
	Image backgroundImage;

	
	private Player player;

	int myTimerDelay;
	int ticks = 0;
	int keyWasPressed;
	TimerTask gameTask = new TimerTask() {
		public void run() {
			
			if(player != null && player.getMap() != null)
				player.getMap().update();
			redraw();
			ticks++;	
			
		}
	};
	Timer myTimer;

	public GamePanel() {
		super();
		this.setPreferredSize(defaultDim);
		this.setMinimumSize(defaultDim);
		this.setVisible(true);
		this.setFocusable(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		myTimerDelay = 35; // 1 sec delay
		myTimer = new Timer();
		myTimer.scheduleAtFixedRate(gameTask, 35, myTimerDelay);

		Map map = new Map1();
		player = new Player(0, 0, map);
		player.getMap().add(player);
//		hud = new HUD(player);
		addKeyListener(new PlayerEvents(player));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//draw the background
		Graphics2D graph = (Graphics2D) g;
		
//		graph.drawImage(backgroundImage, 0, 0, null);
		
		player.getMap().draw(graph, VIEW_RANGE, player.getPosX(), player.getPosY(), player.getDrawX(), player.getDrawY());
		player.getHud().draw(graph);
//		malphite.draw(graph);
	}

	public void redraw() {
		this.repaint();
	}

}
