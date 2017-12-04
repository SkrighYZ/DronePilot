
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Rocket extends JPanel implements KeyListener, ActionListener{

	protected boolean top = false;
	protected boolean mid = false;
	protected boolean bot = false;
	protected boolean hurt = false;
	
	protected int x;
	protected int y;
	protected int angle = 0;
	protected static final double G = 9.8;
	protected static final double SPEED = 100;
	protected static double TIME_DELAY;
	
	public Rocket(int x, int y, double TIME_DELAY) {
		this.x = x;
		this.y = y;
		this.TIME_DELAY = TIME_DELAY;
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void draw(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage img = null;
		
		if(!top && !mid && !bot) {
			img = loadImage("/0.png");
		} else if(top && !mid && !bot) {
			img = loadImage("/2.png");
		} else if(!top && mid && !bot) {
			img = loadImage("/1.png");
		} else if(!top && !mid && bot) {
			img = loadImage("/3.png");
		} else if(top && mid && !bot) {
			img = loadImage("/12.png");
		} else if(!top && mid && bot) {
			img = loadImage("/13.png");
		} else if(top && !mid && bot) {
			img = loadImage("/23.png");
		} else if(top && mid && bot) {
			img = loadImage("/123.png");
		}    
		
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(-angle), img.getWidth() / 2, img.getHeight() / 2);
		
		Graphics2D g2D = (Graphics2D)g;
		if(!hurt) {
			g2D.drawImage(img, at, null);
		}else {
			hurt = false;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		y += 0.5 * G * TIME_DELAY * 10; 
		
		if(top) {
			angle -= 5;
		}
		
		if(bot) {
			angle += 5;
		}
		
		if(mid) {
			x += SPEED * Math.cos(Math.toRadians(angle)) * TIME_DELAY;
			y -= SPEED * Math.sin(Math.toRadians(angle)) * TIME_DELAY;
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			mid = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			bot = true;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			top = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			mid = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			bot = false;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			top = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
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

	public void stopUp() {
		y += 5;
	}
	
	public void stopDown() {
		y -= 5;
	}
	
	public void stopLeft() {
		x += 5;
	}
	
	public void stopRight() {
		x -= 5;
	}
}
