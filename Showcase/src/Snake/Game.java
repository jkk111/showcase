package Snake;

import processing.core.PApplet;

public class Game extends PApplet {
	public int T_SIZE = 16;
	Snake snek;
	Food food;
	int score;
	boolean ended = false;
	int frame;
	public static void main(String args[]) {
		PApplet.main("Snake.Game");
	}
	
	public void settings() {
		size(640, 640, P3D);
	}
	
	public void setup() {
		snek = new Snake(this);
		food = new Food(this);
		snek.init();
	}
	
	public void draw() {
		fill(0, 255, 0);
		if(++frame % 100 == 0) {
			snek.move();
		}
		background(0);
		text("Score: " + score, T_SIZE, T_SIZE);
		if(ended) {
		
		}
		snek.draw();
		food.draw();
		if(food.eat(snek.head())) {
			score++;
			food = new Food(this);
			snek.eat();
		};
	}
	
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP && snek.dir != 'D') {
		    	snek.dir('U');
		    } else if (keyCode == DOWN && snek.dir != 'U') {
		    	snek.dir('D');
		    } else if (keyCode == LEFT && snek.dir != 'R') {
		    	snek.dir('L');
		    } else if (keyCode == RIGHT  && snek.dir != 'L') {
		    	snek.dir('R');
		    } 
		 } else if (key == 'r') {
			 snek.init();
			 ended = false;
			 score = 0;
		 } else if(key == 'q') {
			 snek.eat();
		 }
	}
	
	public void end() {
		this.ended = true;
	}
}
