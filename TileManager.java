package structure;

import static javax.imageio.ImageIO.read;

import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager {

		GamePanel gp;
		Structure[] tile;
		
		public TileManager(GamePanel gp) {
			this.gp = gp;
			
			tile = new Structure[10];
			getTileImage();
			
		}
		public void getTileImage() {
			try {
				tile[0] = new Structure();
				tile[0].texture = read(getClass().getResourceAsStream("/structure/Stone-1.png.png"));
				
				tile[1] = new Structure();
				tile[1].texture = read(getClass().getResourceAsStream("/structure/New Piskel-1.png.png"));
				tile[2] = new Structure();
				tile[2].texture = read(getClass().getResourceAsStream("/structure/New Piskel-1.png.png"));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		public void draw(Graphics2D g2) {
			for(int i=0; i<48*16;i+=48) {
				for(int j=0; j<48*16;j+=48) {
					g2.drawImage(tile[0].texture,i,j,gp.tileSize,gp.tileSize,null);
				}
			}
			
		}
}
