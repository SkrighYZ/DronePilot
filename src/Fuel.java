import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Class that draws the fuel bar of the rocket.
 */
public class Fuel extends JPanel implements ActionListener {

	protected int x = 1200;
	protected int y = 30;
	protected int length = 150;
	protected boolean dead = false;
	protected static double TIME_DELAY;
	
	/**
	 * Constructor.
	 * @param TIME_DELAY in seconds.
	 */
	public Fuel(double TIME_DELAY) {
		this.TIME_DELAY = TIME_DELAY;
	}

	/**
	 * Decreases the length of the fuel bar.
	 * @param e ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		length -= 1;
		if(length <= 0) {
			dead = true;
		}
	}
	
	/**
	 * Draws the fuel bar.
	 * @param g Graphics object.
	 */
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
