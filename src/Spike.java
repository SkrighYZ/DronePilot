import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spike extends JPanel implements ActionListener {

	protected int num = 8;
	protected int length = 40;
	protected int height = 80;
	protected int rtXPos = 920;
	protected int upYPos = 200;
	protected boolean dead = false;
	
	protected static double TIME_DELAY;
	
	public Spike(double TIME_DELAY) {
		this.TIME_DELAY = TIME_DELAY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		rtXPos -= 15;
	}
	
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
