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



public class options extends JPanel implements ActionListener, ChangeListener{
	display myDisplay;
	JTextArea coding;
	JButton runMe;
	JSlider fps;
	JSlider runs;
	JTextArea programmable;
	boolean runningLoop;
	JLabel expectedTime;

	boolean[][] grid= new boolean [120][120];

	public options(display d) {

		myDisplay = d;
		setBackground(Color.yellow);
		this.setLayout(new FlowLayout());
		runningLoop = false;

		JLabel s = new JLabel("fps");
		this.add(s);

		fps = new JSlider(1, 30);
		fps.setVisible(true);
		fps.setMajorTickSpacing(10);
		fps.setMinorTickSpacing(5);
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
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == runMe){
			String code = programmable.getText();
			runningLoop = true;
			runLoop();
		}

	}
	
	//this function will keep running until stop is pressed.
	public void runLoop(){
		for(int i = 0; i < runs.getValue(); i ++){
			//process the code, make changes to boolean array
			//and pass it to display
			myDisplay.updateArray(step());
			
			if(i != runs.getValue() - 1){
				try {
					Thread.sleep((int)(1000/fps.getValue()));
				} catch (InterruptedException e) {}
			}
		}
	}
	public boolean[][] step() {
		boolean[][] orig = copy();
		boolean[][] after = copy();
		for (int i = 0; i < 120; i++) {
			for (int j = 0; j < 120; j++) {
				after[i][j] = decide(orig, i, j);
			}
		}
		return after;
	}
	private boolean[][] copy() {
		boolean[][] copy= new boolean[120][120];
		for (int i = 0; i < 120; i++) {
			for (int j = 0; j < 120; j++) {
				copy[i][j] = myDisplay.getArray()[i][j];
			}
		}
		return copy;
	}
	private boolean decide(boolean[][] orig, int i, int j) {
		if (orig[i][j]) {
			if (neighbors(orig, i, j) < 2) {
				return false;
			} else if (neighbors(orig, i, j) == 2 || neighbors(orig, i, j) == 3) {
				return  true;
			} else{
				return false;
			}
		}
		else{
			if (neighbors(orig, i, j) == 3) {
				return true;
			}
			else{
				return false;
			}
		}
	}
	public int neighbors(boolean[][] orig, int i, int j) {
		int rtn = 0;
		if (i > 0 && i < 120-1 && j > 0 && j < 120-1) {
			rtn += i(orig[i-1][j-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][j+1]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[i+1][j-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][j+1]);
		}
		return rtn;
	}
	private int i(boolean b) {
		return b ? 1:0;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		expectedTime.setText("Expected Time: " + ((int)(runs.getValue()/fps.getValue())) + " sec ");
	}
}
