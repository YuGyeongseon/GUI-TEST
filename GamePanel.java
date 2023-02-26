package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import structure.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize =  originalTileSize * scale ; // 48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 48x16
	final int screenHeight = tileSize * maxScreenRow; // 48x12
	
	public final int screenWidthMax = screenWidth-48; 
	public final int screenWidthMin = 0;
	public final int screenHeightMin =0;
	public final int screenHeightMax =screenHeight-48;
	
	
	int playerPosition=0;
	int bounceNum;
	// FPS
	
	int FPS = 60;
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	Player player =  new Player(this,keyH);
	
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime () + drawInterval;
		
		
		while(gameThread != null) {
			
			//1.UPDATE: update information such as character positions
			update();
			bounce();
			//2.DRAW: draw the screen with the updated information
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				if(remainingTime<0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void bounce() {
		
		int bounceScale = 3;
		int bounceScaleMax = tileSize*4;
		int bounceScaleMin = 0;
		
	
		if(bounceNum%2 != 0) {
			playerY -=bounceScale;
			playerPosition +=bounceScale;
		} else if(bounceNum%2 == 0) {
			playerY +=bounceScale;
			playerPosition +=bounceScale;
		}
		if(playerPosition == bounceScaleMax) {
			bounceNum++;
			playerPosition = bounceScaleMin;
		} 
		
	}
	public void update() {
		player.update();
	}
	
	
	
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
