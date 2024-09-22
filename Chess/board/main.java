package board;

import javax.swing.JFrame;

import piece.*;

public class main {
	
	public static void main(String[] args) {
		JFrame window=new JFrame("Chess2");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		Gamepanel gp=new Gamepanel();   
		window.add(gp);
		window.pack();
		window.requestFocus();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.launchGame();
		
	}

}
