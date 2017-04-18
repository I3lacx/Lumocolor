package tomTestPackage;

import javax.swing.JFrame;

public class TomTestClass {

	public static void main(String[] args) {

		JFrame testFenster =new JFrame("Test");
		testFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFenster.setSize(400, 400);
		testFenster.setVisible(true);
		GUITomTest panel = new GUITomTest();
		
		panel.addKeyListener(panel);
		panel.setFocusable(true);
		
		testFenster.add(panel);
	}

}
