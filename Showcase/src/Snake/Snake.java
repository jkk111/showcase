package Snake;

import java.util.ArrayList;

import processing.core.*;

public class Snake {
	public static int MOVE_RATE = 5;
	Game game;
	SnakeBody body;
	int frames;
	char dir = 'R';
	boolean dirChanged;
	public Snake(Game g) {
		this.game = g;
		body = new SnakeBody();
	}
	
	public void eat() {
		System.out.println("Eating");
		body.lengthen();
	}
	
	public PVector head() {
		ArrayList<PVector> tmp = body.history();
		return tmp.get(tmp.size() - 1);
	}
	
	public void dir(char dir) {
		if(!dirChanged)
		  this.dir = dir;
		dirChanged = true;
	}
	
	public void move() {
		if(game.ended) {
			return;
		}
		dirChanged = false;
		frames = 0;
		ArrayList<PVector> history = body.history();
		PVector head = history.get(history.size() - 1);
		if(dir == 'U') {
			int destY = (int) (head.y - 1);
			if(destY < 0) destY += game.height / game.T_SIZE;
			body.push(new PVector(head.x, destY));
		} else if(dir == 'R') {
			int destX = (int) (head.x + 1);
			if(destX >= game.width / game.T_SIZE) destX = 0;
			body.push(new PVector(destX, head.y));
		} else if(dir == 'D') {
			int destY = (int) (head.y + 1);
			if(destY >= game.height / game.T_SIZE) destY = 0;
			body.push(new PVector(head.x, destY));
		} else {
			int destX = (int) (head.x - 1);
			if(destX < 0) destX += game.width / game.T_SIZE;	
			body.push(new PVector(destX, head.y));
		}
	}
	
	public void init() {
		int wtiles = game.width / game.T_SIZE;
		int htiles = game.height / game.T_SIZE;
		body.clear();
		int x = game.floor(game.random(1, wtiles - 2));
		int y = game.floor(game.random(1, htiles - 2));
		PVector start = new PVector(x, y);
		if(x > wtiles / 2 && y > htiles / 2) {
			initTiles(start, new PVector(x - 4, y - 4));
		} else if(x > wtiles / 2) {
			int endY = Math.random() > 0.5 ? y - 4 : y + 4;
			initTiles(start, new PVector(x - 4, endY));
		} else if(y > htiles / 2) {
			int endX = Math.random() > 0.5 ? y - 4 : y + 4;
			initTiles(start, new PVector(endX, y - 4));
		} else {
			initTiles(start, new PVector(x + 4, y + 4));
		}
	}
	
	private void initTiles(PVector head, PVector tail) {
		boolean horizontal = Math.random() > 0.5;
		if(horizontal) {
			if(head.x > tail.x) {
				// Going right
				dir = 'R';
				for(int i = 4; i >= 0; i--) {
					body.push(new PVector(head.x - i, head.y));
				}
			} else {
				// Going left
				dir = 'L';
				for(int i = 4; i >= 0; i--) {
					body.push(new PVector(tail.x + i, tail.y));
				}
			}
		} else {
			if(head.y > tail.y) {
				// Going down
				dir = 'D';
				for(int i = 4; i >= 0; i--) {
					body.push(new PVector(tail.x, tail.y - i));
				}
			} else {
				// Going up
				dir = 'U';
				for(int i = 4; i >= 0; i--) {
					body.push(new PVector(head.x, head.y + i));
				}
			}
		}
	}
	
	public void draw() {
		frames++;
		if(frames >= MOVE_RATE) {
			move();
		}
		ArrayList<PVector> history = body.history();
		int wtiles = game.width / game.T_SIZE;
		int htiles = game.height / game.T_SIZE;
		boolean collided = false;
		boolean wall = false;
		PVector head = history.get(history.size() - 1);
		if(head.x < 0 || head.x >= wtiles ||
		   head.y < 0 || head.y >= htiles) {
			game.end();
			collided = true;
			wall = true;
		}
		// Check collisions
		for(int i = history.size() - 1; i >= 0; i--) {
			PVector bodyPart = history.get(i);
			if(i < history.size() - 4 && !collided) {
				if(head.equals(bodyPart)) {
					collided = true;
				}
			}
			if(collided) {
				game.end();
			}
			int x = (int) (bodyPart.x * game.T_SIZE);
			int y = (int) (bodyPart.y * game.T_SIZE);
			game.fill(255);
			game.rect(x, y, game.T_SIZE, game.T_SIZE);
			// do the draw stuff
			
		}
	}

}
