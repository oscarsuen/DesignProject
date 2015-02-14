import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class display extends JPanel implements MouseListener {
	boolean[][] points = new boolean[120][120];

	public display() {
		addMouseListener(this);
		setVisible(true);

		// for testing purposes
		for (int x = 0; x < 120; x++) {
			for (int y = 0; y < 120; y++) {
				if ((x + y) % 7 == 2) {
					points[x][y] = true;
				}
			}

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.pink);
		for (int x = 0; x < 120; x++) {
			for (int y = 0; y < 120; y++) {
				if (points[x][y])
					g.fillRect(5 * x, 5 * y, 5, 5);
			}
		}
	}

	public void updateArray(boolean[][] s) {
		points = s;
		paintComponent(getGraphics());
	}

	public void mouseReleased(MouseEvent e) {
		points[e.getX() / 5][e.getY() / 5] = !points[e.getX() / 5][e.getY() / 5];
		repaint();
	}

	public boolean[][] getArray() {
		return points;
	}

	// we don't need to change these
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
