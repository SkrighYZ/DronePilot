import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Class that draws the second spike trap.
 */
public class Spike2 extends JPanel implements ActionListener {

	protected int num;
	protected int length;
	protected int height;
	protected int ltXPos;
	protected int upYPos;
	protected boolean dead;
	protected boolean back; 	// Stores whether the spikes are returning back.
	protected boolean go;		// Controls when the spikes stop changing.
	
	protected static double TIME_DELAY;
	
	/**
	 * Constructor.
	 * @param TIME_DELAY in seconds.
	 */
	public Spike2(double TIME_DELAY) {
		num = 16;  		// Number of spikes times 2
		length = 220;
		height = 0;
		ltXPos = 700;
		upYPos = 50;
		dead = false;
		back = false;
		go = true;
		this.TIME_DELAY = TIME_DELAY;
	}

	/**
	 * Stretches the spikes.
	 * @param e ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!back && go) {
			height += 20;
		} else if(back && go) {
			height -= 20;
		}
		
		if(height >= 400) {
			back = true;
		} 
		if(height <= 10) {
			go = false;
		}
		
	}
	
	/**
	 * Draws the spikes.
	 * @param g Graphics object.
	 */
	public void draw(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < num; i++) {
			if(i % 2 == 0) {
				g.drawLine(ltXPos + i * length / num, upYPos, ltXPos + (i + 1) * length / num, upYPos + height);
			} else {
				g.drawLine(ltXPos + i * length / num, upYPos + height, ltXPos + (i + 1) * length / num, upYPos);
			}
		}
		
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
