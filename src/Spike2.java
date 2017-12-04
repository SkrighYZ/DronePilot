import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spike2 extends JPanel implements ActionListener {

	protected int num = 16;
	protected int length = 220;
	protected int height = 0;
	protected int ltXPos = 700;
	protected int upYPos = 50;
	protected boolean dead = false;
	protected boolean back = false;
	protected boolean go = true;
	
	protected static double TIME_DELAY;
	
	public Spike2(double TIME_DELAY) {
		this.TIME_DELAY = TIME_DELAY;
	}

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
