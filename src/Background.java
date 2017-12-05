import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Background extends JPanel{

	protected ArrayList<Integer> xPts = new ArrayList<Integer>();
	protected ArrayList<Integer> yPts = new ArrayList<Integer>();
	protected ArrayList<Integer> xPts2 = new ArrayList<Integer>();
	protected ArrayList<Integer> yPts2 = new ArrayList<Integer>();
	protected int[] triX = {420, 450, 435};
	protected int[] triY = {560, 560, 600};
	protected boolean triggered;
	
	public Background() {
		xPts.add(0);
		xPts.add(340);
		xPts.add(340);
		xPts.add(480);
		xPts.add(480);
		xPts.add(720);
		xPts.add(720);
		xPts.add(920);
		xPts.add(920);
		xPts.add(960);
		xPts.add(960);
		xPts.add(1400);
		
		yPts.add(600);
		yPts.add(600);
		yPts.add(300);
		yPts.add(300);
		yPts.add(700);
		yPts.add(700);
		yPts.add(400);
		yPts.add(400);
		yPts.add(200);
		yPts.add(200);
		yPts.add(600);
		yPts.add(600);
		
		xPts2.add(0);
		xPts2.add(700);
		xPts2.add(700);
		xPts2.add(920);
		xPts2.add(920);
		xPts2.add(1400);
		
		yPts2.add(50);
		yPts2.add(100);
		yPts2.add(50);
		yPts2.add(50);
		yPts2.add(80);
		yPts2.add(50);
		
		triggered = false;
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < xPts.size() - 1; i++) {
			g.drawLine(xPts.get(i), yPts.get(i), xPts.get(i + 1), yPts.get(i + 1));
		}
		
		for(int i = 0; i < xPts2.size() - 1; i++) {
			g.drawLine(xPts2.get(i), yPts2.get(i), xPts2.get(i + 1), yPts2.get(i + 1));
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(480, 500, 240, 200);
		
		if(!triggered) {
			g.setColor(Color.GRAY);
			g.setFont(new Font("default", Font.BOLD, 18));
			g.drawString("THIS WAY", 380, 490);
			g.fillRect(430, 500, 10, 60);
			g.fillPolygon(triX, triY, 3);
		}
		else {
			g.setColor(Color.GRAY);
			g.setFont(new Font("default", Font.BOLD, 18));
			g.drawString("GOTCHA!", 380, 490);
		}
		
		
	}
	
}

