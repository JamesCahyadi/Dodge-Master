package dodge;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DodgeMaster implements ActionListener, KeyListener {

	// Properties
	JFrame frame = new JFrame("Dodge Master - James Cahyadi");;
	DodgeMasterPanel panel = new DodgeMasterPanel();
	JButton playAgain = new JButton("Play Again");
	JButton quit = new JButton("Quit"); 
	Timer timer;

	// Methods
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == timer) {
			panel.repaint();
		}

		if(panel.gameOver) {
			playAgain.setSize(200,50);
			playAgain.setLocation(520,600);
			playAgain.addActionListener(this);
			panel.add(playAgain);
			
			quit.setSize(100,50);
			quit.setLocation(1180, 670);
			quit.addActionListener(this);
			panel.add(quit);
		}
		
		if(evt.getSource() == playAgain) {
			panel.remove(playAgain);
			panel.remove(quit);
			panel.gameOver = false;
			panel.playerX = panel.ZONE_X + (int) panel.ZONE_WIDTH/2 - (int) panel.PLAYER_WIDTH/2;
			panel.playerY = panel.ZONE_Y + (int) panel.ZONE_HEIGHT/2 - (int) panel.PLAYER_HEIGHT/2;
			panel.playerVel = 2; 
			panel.points = 0;
			panel.f.newX();
			panel.f.newY();
			frame.requestFocus(); 
		}
		
		if(evt.getSource() == quit) {
			System.exit(0);
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

	public void keyTyped(KeyEvent evt) {}

	// Constructor
	public DodgeMaster() {
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1280, 720));

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
