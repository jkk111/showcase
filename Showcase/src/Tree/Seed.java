package Tree;

import processing.core.*;

public class Seed {
	PApplet p;
	PVector pos;
	Branch root;
	int ageToGrow, maxHeight, age;
	boolean grown = false;
	public Seed(PApplet p, int x, int y) {
		this.p = p;
		pos = new PVector(x, y);
		ageToGrow = 100;
		this.root = null;
	}
	
	public void draw() {
		if(pos.y >= p.height) {
			age++;
		} else {
			pos.y = Math.min(pos.y + 5, p.height);
		}
		if(age > ageToGrow && !grown) {
			grown = true;
			grow();
		} else if(!grown) {
			p.noStroke();
			p.fill(255, 0, 0, 100);
			p.ellipse(pos.x, pos.y, 32, 32);
		}
		if(root != null) {
			root.draw();
		}
	}
	
	public void grow() {
		PVector end = new PVector(pos.x, pos.y - 100);
		root = new Branch(p, pos, end, 10, null);
	}
}
