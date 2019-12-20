package dodge;
import javax.swing.*;
import java.awt.*;

public class DodgeMasterPanel extends JPanel{
	
	// Properties
	
	// Dimensions
	int WIN_WIDTH = 1280;
	int WIN_HEIGHT = 600;
	
	//Character
	int PLAYER_WIDTH = 15;
	int PLAYER_HEIGHT = 15;
	int playerX = 0;
	int playerY = 0;
	int playerVel = 5;
	boolean playerLeft = false;
	boolean playerRight = false;
	boolean playerUp = false;
	boolean playerDown = false;
	
	// Block
	int BLOCK_WIDTH = 20;
	int BLOCK_HEIGHT = 20;
	int blockX = 500;
	int blockY = 250;
	int blockVelX = 10;
	int blockVelY = 10;
	
	// Methods
	public void paintComponent(Graphics g) {
		
		// Background Rectangle
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
			
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
		if(playerY > WIN_HEIGHT - BLOCK_HEIGHT) {
			playerY = WIN_HEIGHT - BLOCK_HEIGHT;
			playerDown = false;
		}
		if(playerX <=0) {
			playerX = 0;
			playerLeft = false;
		}
		if(playerX > WIN_WIDTH - BLOCK_WIDTH) {
			playerX = WIN_WIDTH - BLOCK_WIDTH;
			playerRight = false;
		}
		
		// BLOCK
		g.setColor(Color.RED);
		g.fillRect(blockX, blockY, BLOCK_WIDTH, BLOCK_HEIGHT);
		
		// Movement
		blockX = blockX + blockVelX;
		blockY = blockY + blockVelY;
		
		// Borders
		if(blockY <= 0 || blockY > WIN_HEIGHT - BLOCK_HEIGHT) {
			blockVelY = blockVelY * -1;
		}
		
		if(blockX <= 0 || blockX > WIN_WIDTH - BLOCK_WIDTH) {
			blockVelX = blockVelX * -1;
		}
				
	}
	
	
	
	// Constructor
	public DodgeMasterPanel() {
		super();
		
	}

}
