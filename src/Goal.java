import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Goal extends JPanel{

	protected int x, y;
	
	public Goal(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < 50; i++) {
			if(i < 10 || (i > 19 && i < 30 || (i > 39 && i < 50))) {
				g.setColor(Color.BLUE);
			}else {
				g.setColor(Color.YELLOW);
			}
			g.drawOval(x - i * 1, y - i * 1, i * 2, i * 2);
		}

	}

}