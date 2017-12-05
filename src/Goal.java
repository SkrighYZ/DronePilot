import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Class that draws the goal.
 */
public class Goal extends JPanel{

	protected int x, y;
	
	/**
	 * Constructor.
	 * @param x coordinate of the origin.
	 * @param y coordinate of the origin.
	 */
	public Goal(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws the goal.
	 * @param g Graphics object.
	 */
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
