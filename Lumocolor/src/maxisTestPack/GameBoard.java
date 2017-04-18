package maxisTestPack;

import javax.swing.JFrame;

/**
 * 
 * @author Maximilian Otte
 * Game Board where all the stuff happens
 */
public class GameBoard extends JFrame implements GameField {
	GamePanel myGamePanel;
	
	public GameBoard() {
		super("Da supa");
		myGamePanel = new GamePanel();

		this.add(myGamePanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start();
	}
	
	public void start() {
		setVisible(true);
		buildGUI();
		
	}
	
	private void buildGUI(){
		this.add(myGamePanel);
		pack(); //compact everything!
		//sets the size of the JFrame dependend of the GUI
	}
	
	
	
}
