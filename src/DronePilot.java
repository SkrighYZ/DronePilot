import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DronePilot extends JPanel implements KeyListener{
	
	protected Rocket rc;
	protected Goal gl;
	protected Heart ht;
	protected Fuel fl;
	protected Background bg;
	protected Spike sp;
	protected Spike2 sp2;
	protected Text t;
	protected int count1, count2, count3;
	protected boolean stopUp, stopDown, stopLeft, stopRight;
	protected boolean touchedSp, touchedSp2, touchedWater;
	protected Timer timerRc, timerFl, timerSp, timerSp2, timerT;
	protected boolean finished;
	protected static double TIME_DELAY_RC = 0.05;
	protected static double TIME_DELAY_FL = 0.3;
	protected static double TIME_DELAY_SP = 0.01;
	
	public DronePilot() {
		start();
	}
	
	public void start() {
		rc =  new Rocket(30, 500, TIME_DELAY_RC);
		add(rc);
		gl = new Goal(1200, 500);
		add(gl);
		ht = new Heart();
		add(ht);
		fl = new Fuel(TIME_DELAY_FL);
		add(fl);
		bg = new Background();
		add(bg);
		sp = new Spike(TIME_DELAY_SP);
		add(sp);
		sp2 = new Spike2(TIME_DELAY_SP);
		add(sp2);
		t = new Text(700, 400);
		add(t);
		
		count1 = count2 = count3 = 0;
		finished = false;
		
		stopUp = stopDown = stopLeft = stopRight = false;
		touchedSp = touchedSp2 = touchedWater = false;
		
		timerRc = new Timer((int)(TIME_DELAY_RC * 1000), new TimerCallbackRc());
		timerRc.start();
		timerFl = new Timer((int)(TIME_DELAY_FL * 1000), new TimerCallbackFl());
		timerFl.start(); 
		timerSp = new Timer((int)(TIME_DELAY_SP * 1000), new TimerCallbackSp());
		timerSp2 = new Timer((int)(TIME_DELAY_SP * 1000), new TimerCallbackSp2());
		timerT = new Timer(100, new TimerCallbackT());
		
		addKeyListener(this);
		setFocusable(true);
		
	}
	
	protected class TimerCallbackRc implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			rc.actionPerformed(e);
			
			if(touchedDown()) {
				rc.stopDown();
			}
			
			if(touchedUp()) {
				rc.stopUp();
			}
			
			if(touchedRight()) {
				rc.stopRight();
			}
			
			if(touchedLeft()) {
				rc.stopLeft();
			}
			
			if(count1 >= 1 && count1 <= 10) {
				rc.hurt = true;
				count1++;
			}
			
			if(waterTouched() && !(touchedWater)) {
				ht.lives--;
				touchedWater = true;
				rc.hurt = true;
				count2++;
			}
			
			if(count2 >= 1 && count2 <= 10) {
				rc.hurt = true;
				count2++;
			}
			
			if(count3 >= 1 && count3 <= 10) {
				rc.hurt = true;
				count3++;
			}
			
			repaint();
		}
		
	}
	
	protected class TimerCallbackFl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fl.actionPerformed(e);
			repaint();
		}
		
	}
	
	protected class TimerCallbackSp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sp.actionPerformed(e);
			if(spTouched() && !(touchedSp)) {
				ht.lives--;
				touchedSp = true;
				rc.hurt = true;
				count1++;
			}
			repaint();
		}
		
	}
	
	protected class TimerCallbackSp2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sp2.actionPerformed(e);
			if(sp2Touched() && !(touchedSp2)) {
				ht.lives--;
				touchedSp2 = true;
				rc.hurt = true;
				count3++;
			}
			repaint();
		}
		
	}
	
	protected class TimerCallbackT implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			t.actionPerformed(e);
			repaint();
			if(t.font >= 150) {
				timerT.stop();
				finished = true;
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		rc.draw(g);
		fl.draw(g);
		gl.draw(g);
		ht.draw(g);
		bg.draw(g);
		sp.draw(g);
		sp2.draw(g);
		t.draw(g);
		trapTriggered();
		if(t.font < 150) {
			finish();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		rc.keyPressed(e);
		if(finished && (e.getKeyCode() == KeyEvent.VK_SPACE)) {
			System.out.println(1);
			start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		rc.keyReleased(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void trapTriggered() {
		if(rc.y <= 350 && rc.x < 400) {
			timerSp.start();
		}
		if(sp.rtXPos < 0) {
			timerSp.stop();
		}
		
		if(rc.x >= 540 && rc.x < 600 && (rc.y <= 380)) {
			timerSp2.start();
		}
		if(!(sp2.go)) {
			timerSp2.stop();
		}
		
		if(rc.x >= 1000) {
			gl.x = 800;
			gl.y = 300;
			repaint();
		}
	}
	
	public boolean touchedDown() {
		
		int position = 0;
		int x = rc.x + 90;
		int y = rc.y + 80;
		for(int i = 0; i < bg.xPts.size() - 1; i++) {
			if(x > bg.xPts.get(i) && x < bg.xPts.get(i + 1)) {
				position = i;
			}
		}
		
		if(RightOfLine(bg.xPts.get(position), bg.yPts.get(position), bg.xPts.get(position + 1), bg.yPts.get(position + 1), x, y)) {
			return true;
		}
		
		return false;
	}
	
	public boolean touchedUp() {
		
		int position = 0;
		int x = rc.x + 90;
		int y = rc.y - 20;
		for(int i = 0; i < bg.xPts2.size() - 1; i++) {
			if(x > bg.xPts2.get(i) && x < bg.xPts2.get(i + 1)) {
				position = i;
			}
		}
		
		if(!(RightOfLine(bg.xPts2.get(position), bg.yPts2.get(position), bg.xPts2.get(position + 1), bg.yPts2.get(position + 1), x, y))) {
			return true;
		}
		
		return false;
	}
	
	public boolean touchedRight() {
		
		int position = 0;
		int x = rc.x + 150;
		int y = rc.y;
		for(int i = 0; i < bg.xPts.size() - 1; i++) {
			if(x > bg.xPts.get(i) && x < bg.xPts.get(i + 1)) {
				position = i;
			}
		}
		
		if(RightOfLine(bg.xPts.get(position), bg.yPts.get(position), bg.xPts.get(position + 1), bg.yPts.get(position + 1), x, y)) {
			if(y > bg.yPts.get(position) && bg.yPts.get(position + 1) <= bg.yPts.get(position)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean touchedLeft() {
		
		int position = 0;
		int x = rc.x + 20;
		int y = rc.y;
		for(int i = 0; i < bg.xPts.size() - 1; i++) {
			if(x > bg.xPts.get(i) && x < bg.xPts.get(i + 1)) {
				position = i;
			}
		}
		
		if(!(RightOfLine(bg.xPts.get(position + 1), bg.yPts.get(position + 1), bg.xPts.get(position), bg.yPts.get(position), x, y))) {
			if(y > bg.yPts.get(position) && bg.yPts.get(position + 1) >= bg.yPts.get(position)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean RightOfLine(int x1, int y1, int x2, int y2, int x, int y) {
		
		y1 = -y1;
		y2 = -y2;
		y = -y;
		
		int d = (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1);
		
		if(d > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean spTouched() {
	
		int y = sp.upYPos + sp.height;
		
		if((rc.y - 35) <= y) {
			return true;
		}
		
		return false;
	}
	
	public boolean sp2Touched() {
		
		int x = sp2.ltXPos;
		
		if((rc.x + 150) >= x && ((rc.y - 50) <= (sp2.upYPos + sp2.height))) {
			return true;
		}
		
		return false;
	}
	
	public boolean waterTouched() {
		
		if(rc.x >= 480 && rc.x < 720) {
			if((rc.y + 100) >= 500){
				bg.triggered = true;
				return true;
			}
		}
		
		return false;
	}
	
	public void finish() {
		if(ht.isDead() || fl.isDead()) {
			t.win = false;
			timerT.start();
			timerRc.stop();
			timerFl.stop();
		}else if((Math.abs(rc.x - gl.x) <= 100) && (Math.abs(rc.y - gl.y) <= 100)) {
			t.win = true;
			timerT.start();
			timerRc.stop();
			timerFl.stop();
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		DronePilot dp =  new DronePilot();
		f.add(dp);
		
		f.setSize(1400, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

