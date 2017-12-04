import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fuel extends JPanel implements ActionListener {

	protected int x = 1200;
	protected int y = 30;
	protected int length = 150;
	protected boolean dead = false;
	protected static double TIME_DELAY;
	
	public Fuel(double TIME_DELAY) {
		this.TIME_DELAY = TIME_DELAY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		length -= 1;
		if(length <= 0) {
			dead = true;
		}
	}
	
	public void draw(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, length, 20);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
