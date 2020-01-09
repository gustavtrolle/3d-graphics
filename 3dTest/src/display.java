import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class display extends JComponent {
	private static final long serialVersionUID = 1L;
	
	//points are x, y, z
	public double x=0, y=0, z=2;
	public double horRot=0, verRot=0;
	public double horFov = 50, verFov = 30;
	public double screenStretchx = 540, screenStretchy = 360;
	
	ArrayList<double[]> lines = new ArrayList<double[]>();
	
	double[] line1 = {-6,6,0, -6,6,6};
	double[] line2 = {-6,6,0, -6,-6,0};
	double[] line3 = {-6,6,0, 6,6,0};
	double[] line4 = {6,-6,0, 6,-6,6};
	double[] line5 = {6,-6,0, -6,-6,0};
	double[] line6 = {6,-6,0, 6,6,0};
	double[] line7 = {6,6,6, 6,6,0};
	double[] line8 = {6,6,6, -6,6,6};
	double[] line9 = {6,6,6, 6,-6,6};
	double[] line10 = {-6,-6,6, -6,-6,0};
	double[] line11 = {-6,-6,6, 6,-6,6};
	double[] line12 = {-6,-6,6, -6,6,6};
	
	void fillLines() {
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);
		lines.add(line4);
		lines.add(line5);
		lines.add(line6);
		lines.add(line7);
		lines.add(line8);
		lines.add(line9);
		lines.add(line10);
		lines.add(line11);
		lines.add(line12);
	}
	
	public display() {
		fillLines();
		update();
		this.setSize(1080,720);
	}
	
	public void update() {
		updatePanels();
		repaint();
	}
	
	ArrayList<double[]> modLines = new ArrayList<double[]>();
	void updatePanels() {
		modLines.clear();
		for (double[] line : lines) {
			double[] modLine = new double[6];
			for (int i=0; i<6; i+=3) {	
				modLine[i] = (line[i+1]-y)*Math.sin(horRot*Math.PI/180) + (line[i]-x)*Math.cos(horRot*Math.PI/180);
				modLine[i+1] = (line[i+1]-y)*Math.cos(horRot*Math.PI/180) - (line[i]-x)*Math.sin(horRot*Math.PI/180);
				modLine[i+2] = (line[i+2]-z)*Math.cos(verRot*Math.PI/180) - modLine[i+1]*Math.sin(verRot*Math.PI/180);
				modLine[i+1] = (line[i+2]-z)*Math.sin(verRot*Math.PI/180) + modLine[i+1]*Math.cos(verRot*Math.PI/180);
			}
			double bound = 0.1;
			if (modLine[1]>=bound||modLine[4]>=bound) { 
				if (modLine[1]<=bound||modLine[4]<=bound) {
					double slopex = (modLine[1]-modLine[4])/(modLine[0]-modLine[3]);
					double slopez = (modLine[1]-modLine[4])/(modLine[2]-modLine[5]);
					if (modLine[1]<=bound) {
						modLine[0] = modLine[0]+(bound-modLine[1])/slopex;
						modLine[1] = bound;
						modLine[2] = modLine[2]+(bound-modLine[1])/slopez;
					}
					else if (modLine[4]<=bound) {
						modLine[3] = modLine[3]+(bound-modLine[5])/slopex;
						modLine[4] = bound;
						modLine[5] = modLine[5]+(bound-modLine[5])/slopez;
					}
				}
				
				modLine[0] = modLine[0]*Math.abs(screenStretchx/(modLine[1]*Math.tan(horFov*Math.PI/180)));
				modLine[2] = modLine[2]*Math.abs(screenStretchy/(modLine[1]*Math.tan(verFov*Math.PI/180)));
				modLine[3] = modLine[3]*Math.abs(screenStretchx/(modLine[4]*Math.tan(horFov*Math.PI/180)));
				modLine[5] = modLine[5]*Math.abs(screenStretchy/(modLine[4]*Math.tan(verFov*Math.PI/180)));
				
				modLines.add(modLine);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		for (double[] line : modLines) {
			g.drawLine((int)line[0]+540, 360-(int)line[2], (int)line[3]+540, 360-(int)line[5]);
		}
	}
}
