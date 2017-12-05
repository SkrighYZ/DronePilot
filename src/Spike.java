import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that draws the first spike trap.
 */
public class Spike extends JPanel implements ActionListener {

	protected int num;
	protected int length;
	protected int height;
	protected int rtXPos;
	protected int upYPos;
	protected boolean dead;
	protected static double TIME_DELAY;
	
	/**
	 * Constructor.
	 * @param TIME_DELAY in seconds.
	 */
	public Spike(double TIME_DELAY) {
		num = 8;  	// Number of spikes times 2
		length = 40;
		height = 80;
		rtXPos = 920;
		upYPos = 200;
		dead = false;
		this.TIME_DELAY = TIME_DELAY;
	}

	/**
	 * The spikes moving towards the left.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		rtXPos -= 15;
	}
	
	/**
	 * Draws the spikes
	 * @param g Graphics object.
	 */
	public void draw(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < num; i++) {
			if(i % 2 == 0) {
				g.drawLine(rtXPos, upYPos + i * height / num, rtXPos - length, upYPos + (i + 1) * height / num);
			}
			else {
				g.drawLine(rtXPos - length, upYPos + i * height / num, rtXPos, upYPos + (i + 1) * height / num);
			}
		}
		
		g.drawLine(rtXPos, upYPos, rtXPos, upYPos + height);
		
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
