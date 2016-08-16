package Tree;

import processing.core.*;

public class Branch {
	PApplet p;
	Branch parent;
	int age, growTime, height, greening, greenAge;
	
	int MAX_GREEN = 100;
	PVector start, end;
	Branch branches[];
	boolean branched, notified;
	int maxHeight = 15;
	public Branch(PApplet p, PVector start, PVector end, int growTime, Branch parent) {
		this.p = p;
		this.start = start;
		this.end = end;
		this.branches = new Branch[2];
		this.growTime = growTime;
		this.parent = parent;
		if(parent != null) {
			height = parent.height + 1;
		} else {
			height = 0;
		}
	}
	
	public void draw() {
		age++;
		PVector delta;
		if(age > growTime && !branched && height < maxHeight) {
			branched = true;
			branch();
		} 
		if(age > growTime && height >= maxHeight) {
			if(greening < MAX_GREEN) {
				green();
			} else {
				if(parent != null) {
					parent.green();
				}
			}
		}
		if(branched) {
			delta = end;
		}
		else {
			int grown = Math.min(age, growTime);
			delta = PVector.sub(end, start);
//			System.out.println(delta);
//			System.out.println(grown + ":" + age + "," + growTime);
			delta = PVector.div(delta, growTime);
			delta = PVector.mult(delta, grown);
			delta = PVector.add(start, delta);
		}
		p.strokeWeight((maxHeight - height) + 1);
		if(height > 5)
			p.stroke((255/MAX_GREEN) * (MAX_GREEN - greening), 255, (255/MAX_GREEN) * (MAX_GREEN - greening));
		else {
			int[] rgb = getBrown();
			p.stroke(rgb[0], rgb[1], rgb[2]);
		}
		p.line(start.x, start.y, delta.x, delta.y);
		if(branched) {
			for(int i = 0; i < branches.length; i++) {
				branches[i].draw();
			}
		}
	}
	
	public int[] getBrown() {
		int r = (int) PApplet.map(MAX_GREEN - greening, 0, MAX_GREEN, 102, 255);
		int g = (int) PApplet.map(MAX_GREEN - greening, 0, MAX_GREEN, 51, 255);
		int b = (int) PApplet.map(MAX_GREEN - greening, 0, MAX_GREEN, 0, 255);
		
		return new int[]{r,g,b};
	}
	
	public void notifyGreening() {
		if(!notified) {
			System.out.println("Greening height: " + height);
			notified = true;
		}
	}
	
	
	public void green() {
		if(greening >= MAX_GREEN) {
			if(parent != null) {
				parent.green();
			}
		} else {
			if(++greenAge >= 10) {
				greening++;
				greenAge = 0;
			}
		}
	}
	
	public void branch() {
		PVector branchEnd = PVector.sub(end, start);
		branchEnd.rotate(PConstants.PI / 6);
		branchEnd.mult(0.8f);
		branchEnd = PVector.add(branchEnd, end);
		int growTime = PApplet.floor(p.random(30, 50));
		branches[0] = new Branch(p, end, branchEnd, growTime, this);
		branchEnd = PVector.sub(end, start);
		branchEnd.rotate(-(PConstants.PI / 6));
		branchEnd.mult(0.8f);
		branchEnd = PVector.add(branchEnd, end);
		growTime = PApplet.floor(p.random(30, 50));
		branches[1] = new Branch(p, end, branchEnd, growTime, this);
	}
}
