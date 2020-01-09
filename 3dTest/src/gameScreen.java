import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

public class gameScreen extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		gameScreen mainGame = new gameScreen();
		mainGame.setVisible(true);
	}
	
	display mainDisplay = new display();
	
	public gameScreen() {
		setSize(1080,720);
		setLayout(null);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(mainDisplay);
		mainDisplay.setLocation(0,0);
		mainDisplay.addMouseListener(this);
		mainDisplay.addMouseMotionListener(this);
		addKeyListener(this);
		mainDisplay.repaint();
	}
	
	boolean clicked = false;
	
	int mX = 0, mY = 0;
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mX = e.getX();
		mY = e.getY();
		if (clicked) {
			clicked = false;
		}
		else {
			clicked = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		/*mainDisplay.horRot -= (e.getX() - mX);
		mainDisplay.verRot -= (e.getY() - mY);
		mainDisplay.update();
		mX = e.getX();
		mY = e.getY();*/
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (clicked) {
			mainDisplay.horRot -= 0.25*(e.getX() - mX);
			mainDisplay.verRot -= 0.25*(e.getY() - mY);
			mainDisplay.update();
			
			mX = e.getX();
			mY = e.getY();
			if (e.getX()<=10||e.getX()>=1070) {
				try {
					Robot robot = new Robot();
					robot.mouseMove(540, e.getY());
					mX = 540;
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getY()<=10||e.getY()>=710) {
				try {
					Robot robot = new Robot();
					robot.mouseMove(e.getX(), 360);
					mY = 360;
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==87) {
			mainDisplay.y+=Math.cos(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.x-=Math.sin(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.update();
		}
		else if (e.getKeyCode()==83) {
			mainDisplay.y-=Math.cos(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.x+=Math.sin(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.update();
		}
		if (e.getKeyCode()==68) {
			mainDisplay.x+=Math.cos(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.y+=Math.sin(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.update();
		}
		else if (e.getKeyCode()==65) {
			mainDisplay.x-=Math.cos(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.y-=Math.sin(mainDisplay.horRot*Math.PI/180)*0.25;
			mainDisplay.update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
