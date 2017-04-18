/**
 * 
 */
package joschkasTestPack;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * @author Joschido
 *
 * Most important stuff happens here. (Thats why its called "Precious")
 */
public class Precious extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 32*16;
	public static final int HEIGHT = WIDTH;
	public static final int SCALE = 2;
	public static final String NAME = "Precious";
	
	private JFrame frame;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private TileMap map;
	private Player player;
	
	
	public Precious() {
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		map = new TileMap();
		player = new Player(map);
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(player);
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		new Precious().start();
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			frames++;
			render();
			
			
			if(System.currentTimeMillis()-lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("Ticks:" + ticks + ", " + "FPS:" + frames);
				frames = 0;
				ticks = 0;
			}
			
//			System.out.println(frames+","+ticks);
		}
	}
	
	public void tick() {
		tickCount++;
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		BufferedImage temp;

		for(int i=0; i<TileMap.MAPHEIGHT; i++) {
			for(int j=0; j<TileMap.MAPWIDTH; j++) {
				temp = map.getTile(i, j).getBufferedImage();
				g.drawImage(temp, null, i*Tile.TILESIZE, j*Tile.TILESIZE);
			}
		}
		temp = player.getBufferedImage();
		g.drawImage(temp, null, player.getPosX()*Tile.TILESIZE, player.getPosY()*Tile.TILESIZE);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
//		g.setColor(Color.BLUE);
//		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
		
	}

}
