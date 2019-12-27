package dodge;
import javax.swing.*;
import java.awt.*;

public class DodgeMasterPanel extends JPanel{

	// Properties

	// Dimensions
	int WIN_WIDTH = 1280;
	int WIN_HEIGHT = 720;

	// Safe Zone
	int ZONE_WIDTH = 60;
	int ZONE_HEIGHT = 60;
	int ZONE_X = (int) (WIN_WIDTH/2) - ZONE_WIDTH;
	int ZONE_Y = (int) (WIN_HEIGHT/2) - ZONE_HEIGHT;

	//Character
	int PLAYER_WIDTH = 10;
	int PLAYER_HEIGHT = 10;
	int playerX = ZONE_X + (int) ZONE_WIDTH/2 - (int) PLAYER_WIDTH/2;
	int playerY = ZONE_Y + (int) ZONE_HEIGHT/2 - (int) PLAYER_HEIGHT/2;
	int playerVel = 2;
	boolean playerLeft = false;
	boolean playerRight = false;
	boolean playerUp = false;
	boolean playerDown = false;
	boolean gameOver = false;

	// Bouncing Blocks
	Block[] bouncingBlocks = new Block[180];

	// Food
	Food f = new Food();
	int points = 0;

	// Methods
	public void paintComponent(Graphics g) {

		// Background Rectangle
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);

		// Safe Zone
		g.setColor(Color.WHITE);
		g.drawRect(ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT);

		// CHARACTER
		g.setColor(Color.BLUE);
		g.fillRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);

		// Movement
		if(playerLeft) {
			playerX = playerX - playerVel;
		}
		if(playerRight) {
			playerX = playerX + playerVel;
		}
		if(playerUp) {
			playerY = playerY - playerVel;
		}
		if(playerDown) {
			playerY = playerY + playerVel;
		}

		// Borders
		if(playerY <= 0) {
			playerY = 0;
			playerUp = false;
		}
		if(playerY >= WIN_HEIGHT - PLAYER_HEIGHT) {
			playerY = WIN_HEIGHT - PLAYER_HEIGHT;
			playerDown = false;
		}
		if(playerX <=0) {
			playerX = 0;
			playerLeft = false;
		}
		if(playerX >= WIN_WIDTH - PLAYER_WIDTH) {
			playerX = WIN_WIDTH - PLAYER_WIDTH;
			playerRight = false;
		}

		// FOOD
		f.draw(g);

		if(f.topLeftCorner(playerX, playerY)
				|| f.topRightCorner(playerX, playerY, PLAYER_WIDTH) 
				|| f.bottomLeftCorner(playerX, playerY, PLAYER_HEIGHT) 
				|| f.bottomRightCorner(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT)) {
			f.newX();
			f.newY();
			points = points + 1;
		}		

		// BLOCK	
		for(Block b : bouncingBlocks) {
			b.move(g, WIN_WIDTH, WIN_HEIGHT, ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT);
			// Collision
			if(b.topLeftCorner(playerX, playerY)
					|| b.topRightCorner(playerX, playerY, PLAYER_WIDTH) 
					|| b.bottomLeftCorner(playerX, playerY, PLAYER_HEIGHT) 
					|| b.bottomRightCorner(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT)) {
				gameOver= true;
				playerVel = 0;
			}
		}

		// Score
		g.setColor(Color.WHITE);
		g.drawString(points+"", ZONE_X + 3, ZONE_Y + 10);
	}

	// Constructor
	public DodgeMasterPanel() {
		super();

		for(int i = 0; i < bouncingBlocks.length; i++) {
			bouncingBlocks[i] = new Block();
		}
	}
}
