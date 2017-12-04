import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Heart extends JPanel{

	protected int lives = 3;
	protected int x = 20;
	protected int y = 20;
	protected boolean dead = false;
	
	public Heart() {
		
	}
	
	public void draw(Graphics g) {
		BufferedImage heart = loadImage("/heart.png");
		if(lives == 1) {
			g.drawImage(heart, x, y, 30, 30, null);
		} else if(lives == 2) {
			g.drawImage(heart, x, y, 30, 30, null);
			g.drawImage(heart, x + 30, y, 30, 30, null);
		} else if(lives == 3) {
			g.drawImage(heart, x, y, 30, 30, null);
			g.drawImage(heart, x + 30, y, 30, 30, null);
			g.drawImage(heart, x + 60, y, 30, 30, null);
		} else if(lives == 4) {
			g.drawImage(heart, x, y, 30, 30, null);
			g.drawImage(heart, x + 30, y, 30, 30, null);
			g.drawImage(heart, x + 60, y, 30, 30, null);
			g.drawImage(heart, x + 90, y, 30, 30, null);
		} else {
			dead = true;
		}
	}

	BufferedImage loadImage(String FileName) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream(FileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
