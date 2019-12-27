package dodge;

import java.awt.Color;
import java.awt.Graphics;

public class Block {

	int BLOCK_WIDTH, BLOCK_HEIGHT, blockX, blockY, blockVelX, blockVelY, randStartX, randStartY;
	double randVelX, randVelY;
	boolean invalidStart = true;

	public Block() {

		this.randVelX = Math.random();
		this.randVelY = Math.random();

		this.BLOCK_WIDTH = 20;
		this.BLOCK_HEIGHT = 20;

		// Prevent blocks from spawning inside safe zone
		while(invalidStart) {
			randStartX = (int) (Math.random() * (1280 - this.BLOCK_WIDTH) + 1);
			randStartY = (int) (Math.random() * (720 - this.BLOCK_HEIGHT) + 1);
			if(randStartX < 550 || randStartX > 650 || randStartY < 270 || randStartY > 370) {
				invalidStart = false;
			}
		}

		this.blockX = randStartX;
		this.blockY = randStartY;
		this.blockVelX = 1;
		this.blockVelY = 1;

		if(randVelX > 0.5) {
			this.blockVelX = -this.blockVelX;
		}
		if(randVelY > 0.5) {
			this.blockVelY = -this.blockVelY;
		}
	}

	public void move(Graphics g, int WIN_WIDTH, int WIN_HEIGHT, int ZONE_X, int ZONE_Y, int ZONE_WIDTH, int ZONE_HEIGHT) {

		g.setColor(Color.RED);
		g.fillRect(this.blockX, this.blockY, this.BLOCK_WIDTH, this.BLOCK_HEIGHT);

		// Movement
		this.blockX = this.blockX + this.blockVelX;
		this.blockY = this.blockY + this.blockVelY;

		// Borders
		if(this.blockY <= 0 
				|| this.blockY >= WIN_HEIGHT - this.BLOCK_HEIGHT
				|| topEdgeZone(ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT)
				|| bottomEdgeZone(ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT)) {
			this.blockVelY = -this.blockVelY;
		} else if(this.blockX <= 0 
				|| this.blockX >= WIN_WIDTH - this.BLOCK_WIDTH
				|| rightEdgeZone(ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT)
				|| leftEdgeZone(ZONE_X, ZONE_Y, ZONE_WIDTH, ZONE_HEIGHT)) {
			this.blockVelX = -this.blockVelX;
		}
	}
	

	// Hit box to detect block hitting safe zone

	// Check if bottom left and right corners of block hit the top edge of safe zone, respectively
	public boolean topEdgeZone(int ZONE_X, int ZONE_Y, int ZONE_WIDTH, int ZONE_HEIGHT){
		return (this.blockX > ZONE_X && this.blockX < ZONE_X + ZONE_WIDTH
				&& this.blockY + this.BLOCK_HEIGHT >= ZONE_Y && this.blockY + this.BLOCK_HEIGHT <= ZONE_Y + ZONE_HEIGHT) // block bottom left corner
				|| (this.blockX + BLOCK_WIDTH > ZONE_X && this.blockX + BLOCK_WIDTH < ZONE_X + ZONE_WIDTH
						&& this.blockY + this.BLOCK_HEIGHT >= ZONE_Y && this.blockY + this.BLOCK_HEIGHT <= ZONE_Y + ZONE_HEIGHT); // block bottom right corner
	}

	// Check if top left and right corners of block hit the bottom edge of safe zone, respectively
	public boolean bottomEdgeZone(int ZONE_X, int ZONE_Y, int ZONE_WIDTH, int ZONE_HEIGHT) {
		return (this.blockX > ZONE_X && this.blockX < ZONE_X + ZONE_WIDTH
				&& this.blockY >= ZONE_Y && this.blockY <= ZONE_Y + ZONE_HEIGHT) // block top left corner
				|| (this.blockX + BLOCK_WIDTH > ZONE_X && this.blockX + BLOCK_WIDTH < ZONE_X + ZONE_WIDTH
						&& this.blockY >= ZONE_Y && this.blockY <= ZONE_Y + ZONE_HEIGHT); // block top right corner
	}

	// Check if top left and bottom left corner of block hit the right edge of safe zone, respectively
	public boolean rightEdgeZone(int ZONE_X, int ZONE_Y, int ZONE_WIDTH, int ZONE_HEIGHT) {
		return (this.blockY >= ZONE_Y && this.blockY <= ZONE_Y + ZONE_HEIGHT 
				&& this.blockX <= ZONE_X + ZONE_WIDTH && this.blockX >= ZONE_X) // block top left corner
				|| (this.blockY + this.BLOCK_HEIGHT >= ZONE_Y && this.blockY + this.BLOCK_HEIGHT <= ZONE_Y + ZONE_HEIGHT
				&& this.blockX <= ZONE_X + ZONE_WIDTH && this.blockX >= ZONE_X); // block bottom left corner
	}

	// Check if top right and bottom right corner of block hit the left edge of safe zone, respectively
	public boolean leftEdgeZone(int ZONE_X, int ZONE_Y, int ZONE_WIDTH, int ZONE_HEIGHT) {
		return (this.blockY >= ZONE_Y && this.blockY <= ZONE_Y + ZONE_HEIGHT 
				&& this.blockX + this.BLOCK_WIDTH <= ZONE_X + ZONE_WIDTH && this.blockX + this.BLOCK_WIDTH >= ZONE_X) // block top right corner
				|| (this.blockY + this.BLOCK_HEIGHT >= ZONE_Y && this.blockY + this.BLOCK_HEIGHT <= ZONE_Y + ZONE_HEIGHT
				&& this.blockX + this.BLOCK_WIDTH <= ZONE_X + ZONE_WIDTH && this.blockX + this.BLOCK_WIDTH  >= ZONE_X); // block top right corner
	}

	// Player and block collision hit box
	// Hit box to detect player's top left corner hitting a block
	public boolean topLeftCorner(int playerX, int playerY) {
		return playerX > this.blockX && playerX < this.blockX + this.BLOCK_WIDTH 
				&& playerY > this.blockY && playerY < this.blockY + this.BLOCK_HEIGHT;
	}

	// Hit box to detect player's top right corner hitting a block
	public boolean topRightCorner(int playerX, int playerY, int playerWidth) {
		return playerX + playerWidth > this.blockX && playerX + playerWidth < this.blockX + this.BLOCK_WIDTH 
				&& playerY > this.blockY && playerY < this.blockY + this.BLOCK_HEIGHT;
	}

	// Hit box to detect player's bottom left corner hitting a block
	public boolean bottomLeftCorner(int playerX, int playerY, int playerHeight) {
		return playerX > this.blockX && playerX < this.blockX + this.BLOCK_WIDTH && playerY + playerHeight > this.blockY 
				&& playerY + playerHeight < this.blockY + this.BLOCK_HEIGHT;
	}

	// Hit box to detect player's bottom right corner hitting a block
	public boolean bottomRightCorner(int playerX, int playerY, int playerWidth, int playerHeight) {
		return playerX + playerWidth > this.blockX && playerX + playerWidth < this.blockX + this.BLOCK_WIDTH 
				&& playerY + playerHeight > this.blockY && playerY + playerHeight < this.blockY + this.BLOCK_HEIGHT;
	}
}
