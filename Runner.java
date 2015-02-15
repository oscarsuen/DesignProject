import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Runner {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Slider Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(900, 600);
		frame.setResizable(false);

		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

		Display d = new Display();
		Options o = new Options(d);
		o.setPreferredSize(new Dimension(300, 600));
		d.setPreferredSize(new Dimension(600, 600));

		main.add(o);
		main.add(d);

		frame.setContentPane(main);
		frame.setVisible(true);
	}

}
