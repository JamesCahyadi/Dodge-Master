package dodge;

import java.awt.Color;
import java.awt.Graphics;

public class Food {

	int FOOD_WIDTH, FOOD_HEIGHT, foodX, foodY;
	boolean invalidStart = true;

	public Food() {
		this.FOOD_WIDTH = 20;
		this.FOOD_HEIGHT = 20;
		
		// Prevents blocks from spawning inside safe zone
		while(invalidStart) {
			this.foodX = (int) (Math.random() * (1280 - FOOD_WIDTH) + 1);
			this.foodY = (int) (Math.random() * (720 - FOOD_HEIGHT) + 1);
			if(this.foodX < 540 || this.foodX > 660 || this.foodY < 270 || this.foodY > 370) {
				invalidStart = false;
			}
		}
	}

	public int newX() {
		return this.foodX = (int) (Math.random() * (1280 - FOOD_WIDTH) + 1); 
	}

	public int newY() {
		return this.foodY = (int) (Math.random() * (720 - FOOD_HEIGHT) + 1);
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(foodX, foodY, FOOD_WIDTH, FOOD_HEIGHT);
	}

	public boolean topLeftCorner(int playerX, int playerY) {
		return playerX > this.foodX && playerX < this.foodX + this.FOOD_WIDTH 
				&& playerY > this.foodY && playerY < this.foodY + this.FOOD_HEIGHT;
	}

	public boolean topRightCorner(int playerX, int playerY, int playerWidth) {
		return playerX + playerWidth > this.foodX && playerX + playerWidth < this.foodX + this.FOOD_WIDTH 
				&& playerY > this.foodY && playerY < this.foodY + this.FOOD_HEIGHT;
	}

	public boolean bottomLeftCorner(int playerX, int playerY, int playerHeight) {
		return playerX > this.foodX && playerX < this.foodX + this.FOOD_WIDTH && playerY + playerHeight > this.foodY 
				&& playerY + playerHeight < this.foodY + this.FOOD_HEIGHT;
	}

	public boolean bottomRightCorner(int playerX, int playerY, int playerWidth, int playerHeight) {
		return playerX + playerWidth > this.foodX && playerX + playerWidth < this.foodX + this.FOOD_WIDTH 
				&& playerY + playerHeight > this.foodY && playerY + playerHeight < this.foodY + this.FOOD_HEIGHT;
	}
}
