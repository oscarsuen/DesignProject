import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JPanel implements ActionListener, ChangeListener {
	Display myDisplay;
	JTextArea coding;
	JButton runMe;
	JSlider fps;
	JSlider runs;
	JTextArea programmable;
	boolean runningLoop;
	JLabel expectedTime;

	boolean[][] grid = new boolean[120][120];

	public Options(Display d) {

		myDisplay = d;
		setBackground(Color.yellow);
		this.setLayout(new FlowLayout());
		runningLoop = false;

		JLabel s = new JLabel("fps");
		this.add(s);

		fps = new JSlider(1, 10);
		fps.setVisible(true);
		fps.setMajorTickSpacing(5);
		fps.setMinorTickSpacing(1);
		fps.setPaintTicks(true);
		fps.setPaintLabels(true);
		fps.setValue(1);
		fps.addChangeListener(this);
		this.add(fps);

		JLabel a = new JLabel("cycles");
		this.add(a);

		runs = new JSlider(1, 500);
		runs.setVisible(true);
		runs.setMajorTickSpacing(100);
		runs.setMinorTickSpacing(50);
		runs.setPaintTicks(true);
		runs.setPaintLabels(true);
		runs.setValue(1);
		runs.addChangeListener(this);
		this.add(runs);

		runMe = new JButton("Run!");
		this.add(runMe);
		runMe.addActionListener(this);

		expectedTime = new JLabel("Expected Time: " + 1 + " sec");
		this.add(expectedTime);

		programmable = new JTextArea(5, 20);
		programmable.setEditable(true);
		programmable.setVisible(true);

		programmable.setBackground(Color.white);
		programmable.setText("Oscar is mean");
		this.add(programmable);

	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == runMe) {
			String code = programmable.getText();
			runningLoop = true;
			runLoop();
		}

	}

	// this function will keep running until stop is pressed.
	public void runLoop() {
		for (int i = 0; i < runs.getValue(); i++) {
			// process the code, make changes to boolean array
			// and pass it to display
			myDisplay.getGame().step();
			myDisplay.paint();

			if (i != runs.getValue() - 1) {
				try {
					Thread.sleep((int) (1000 / fps.getValue()));
				} catch (InterruptedException e) {
				}
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		expectedTime.setText("Expected Time: "
				+ ((int) (runs.getValue() / fps.getValue())) + " sec ");
	}
}
