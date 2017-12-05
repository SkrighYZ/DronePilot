import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Class that draws the lives.
 */
public class Heart extends JPanel{

	protected int lives;
	protected int x;
	protected int y;
	protected boolean dead;
	
	/**
	 * Constructor.
	 * Sets original lives to 3 and dead to false;
	 */
	public Heart() {
		lives = 3;
		x = 20;
		y = 20;
		dead = false;
	}
	
	/**
	 * Draws the hearts.
	 * @param g Graphics object.
	 */
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

	/**
	 * Loads image from res folder.
	 * @param FileName.
	 * @return BufferedImage.
	 */
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
