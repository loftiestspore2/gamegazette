package board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class Gamepanel extends JPanel implements Runnable {
	public static final int WIDTH=1100;
	public static final int HEIGHT=800;
	final int FPS=60;
	Thread gameThread;
	
	
	
	
	public Gamepanel() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.WHITE);
	}
	public void launchGame() {
		gameThread=new Thread(this);
		gameThread.start();
	}
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		
		
		while(gameThread !=null) {
			currentTime = System.nanoTime();
			
			delta +=(currentTime - lastTime)/drawInterval;
			lastTime=currentTime;
			
			if (delta>=1) {
				update();
				repaint();
				delta --;
			}
		}
		
	
	}
	private void update() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 1;i<=9; i++) {
			g.drawLine(80,80*i,720,80*i);  //horizontal
			g.drawLine(80*i,80,80*i,720);  // vertical line
		}
		
		for(int i = 1; i<=8;i++) {
			
			for(int j= 1; j<=8;j++) {
				if(i%2==0) {
					if(j%2==0) {
						g.setColor(Color.WHITE);
						g.fillRect(80*j,80*i,80,80);
					}else {
						g.setColor(Color.BLACK);
						g.fillRect(80*j,80*i,80,80);
					}
				}else {
					if(j%2==1) {
						g.setColor(Color.WHITE);
						g.fillRect(80*j,80*i,80,80);
					}else {
						g.setColor(Color.BLACK);
						g.fillRect(80*j,80*i,80,80);
					}
				}
			}
		}
	}
}


