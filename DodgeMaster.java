package dodge;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DodgeMaster implements ActionListener, KeyListener {

	// Properties
	JFrame frame;
	DodgeMasterPanel panel;
	Timer timer;

	// Methods
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == timer) {
			panel.repaint();
		}
	}

	public void keyReleased(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case 37: 
			panel.playerLeft = false;
			break;		
		case 39: 
			panel.playerRight = false; 
			break;
		case 38: 
			panel.playerUp = false;
			break;
		case 40: 
			panel.playerDown = false;
			break;
		}

	}

	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case 37: 
			panel.playerLeft = true;
			break;		
		case 39: 
			panel.playerRight = true; 
			break;
		case 38: 
			panel.playerUp = true;
			break;
		case 40: 
			panel.playerDown = true;
			break;
		}

	}

	public void keyTyped(KeyEvent evt) {

	}



	// Constructor
	public DodgeMaster() {
		frame = new JFrame("Dodge Master - James Cahyadi");
		panel = new DodgeMasterPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1280, 600));

		frame.addKeyListener(this);

		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		timer = new Timer(1000/60, this);
		timer.start();
	}



	// Main Method
	public static void main(String[] args) {
		new DodgeMaster();
	}

}
