package Snake;

import processing.core.*;

public class Food {
	PVector pos;
	Game game;
	public Food(Game p) {
		this.game = p;
		int x = PApplet.floor(game.random(0, game.width / game.T_SIZE));
		int y = PApplet.floor(game.random(0, game.height / game.T_SIZE)); 
		pos = new PVector(x, y);
	}
	
	public void draw() {
		game.fill(255, 0, 0);
		game.rect(pos.x * game.T_SIZE, pos.y * game.T_SIZE, game.T_SIZE, game.T_SIZE);
	}
	
	public boolean eat(PVector player) {
		return pos.x == player.x && pos.y == player.y;
	}
}
