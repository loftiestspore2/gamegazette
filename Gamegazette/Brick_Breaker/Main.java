package Brick_Breaker;

import javax.swing.*;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		GamePlay gamePlay = new GamePlay();
		frame.setBounds(10,10,700,700);
		frame.setTitle("Breakout Ball");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePlay);

	}

}
