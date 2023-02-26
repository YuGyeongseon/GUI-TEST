package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 3;
		direction = "down";
	}
	public void outLineUpdate() {
		if(x > gp.screenWidthMax) {
			x = gp.screenWidthMax;
		} else if(x < gp.screenWidthMin) {
			x = gp.screenWidthMin;
		}
		if(y > gp.screenHeightMax) {
			y = gp.screenHeightMax;
		} else if(y < gp.screenHeightMin) {
			y = gp.screenHeightMin;
		}
	}
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player-7.png.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player-8.png.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player-1.png.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player-2.png.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player-3.png.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player-4.png.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player-5.png.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player-6.png.png"));
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyH.upPressed == true) {
			direction ="up";
			y -= speed;
		} 
		else if(keyH.downPressed == true) {
			direction ="down";
			y += speed;
		}
		else if(keyH.leftPressed == true) {
			direction ="left";
			x -= speed;
		} 
		else if(keyH.rightPressed == true) {
			direction ="right";
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter >10) {
			if(spriteNum == 1) {
				spriteNum =2;
			}
			else if(spriteNum ==2) {
				spriteNum =1;
			}
			spriteCounter = 0;
		}
		outLineUpdate();
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
	}
	
}
