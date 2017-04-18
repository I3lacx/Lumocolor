package tomTestPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Tom Freckem
 *
 */
public class GUITomTest extends JPanel implements KeyListener{

	Timer time;
	JLabel focus = new JLabel();
	Image img;
	int key;
	int lauf;
	int x_Koordinate;
	TimerTask gameTask = new TimerTask() {
		public void run() {
			//bewegen();
			repaint();
		}
	};

	public GUITomTest() {
		key = 0;
		lauf = -5;
		this.setPreferredSize(new Dimension(400, 400));
		
		setFocusable(true);
		setVisible(true);
		ImageIcon TestImage = new ImageIcon("C:\\Users\\Tom Freckem\\Pictures\\Spielfeld.png");
		img = TestImage.getImage();
		
		
		this.setBackground(Color.WHITE);
		focus.setText("ALLALAL");
		focus.setLocation(100, 100);
		focus.setVisible(true);
		focus.setFocusable(true);
		focus.addKeyListener(this);
		
		add(focus);
		
		time = new Timer();
		// task, start after ms, schedule at ms
		time.scheduleAtFixedRate(gameTask, 1000, 1000);
	}

	public void bewegen() {
		
		x_Koordinate += lauf;
		System.out.println("lauf: " + lauf + " ; x: "+x_Koordinate);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D f2 = (Graphics2D) g;
		f2.drawImage(img, x_Koordinate, 0, null);
		g.setColor(Color.WHITE);
		System.out.println("alal");
	}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Eror");
			key = e.getKeyCode();

			System.out.println(key +"  fds");
			if (key == KeyEvent.VK_LEFT) {

				lauf = 1;
				System.out.println("links");
			}
			if (key == KeyEvent.VK_RIGHT) {
				
				lauf = -1;
				System.out.println("rechts");
			}
		}

		public void keyReleased(KeyEvent e) {

			key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
				lauf = 0;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	

}
