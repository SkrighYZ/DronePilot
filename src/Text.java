import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Class that draws the end game message.
 */
public class Text extends JPanel implements ActionListener{

	protected int x1, y1, x2, y2;
	protected boolean win;
	protected int font = 0;
	
	/**
	 * Constructor.
	 * @param x coordinate of the baseline of the leftmost character of the texts.
	 * @param y coordinate of the baseline of the leftmost character of the texts.
	 */
	public Text(int x, int y) {
		this.x1 = x2 = x;
		this.y1 = y2 = y;
		win = false;
	}

	/**
	 * Increases the size and leftmost point of the texts.
	 * @param e ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		font += 10;
		x1 -= 30;
		x2 -= 17;
		y2 += 5;
	}

	/**
	 * Draws the message.
	 * @param g Graphics object.
	 */
	public void draw(Graphics g) {
		
		super.paintComponent(g);
		g.setFont(new Font("default", Font.BOLD, font));
		
		if(win) {
			g.drawString(" YOU WIN!", x1, y1);
		}
		else {
			g.drawString("YOU LOSE!", x1, y1);
		}
		g.setFont(new Font("default", Font.ITALIC, font / 3));
		g.drawString("Press space to restart", x2, y2);
		
	}

}
