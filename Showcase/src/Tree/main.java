package Tree;

import java.util.ArrayList;

import processing.core.*;

public class main extends PApplet {
	ArrayList<Seed> seeds;
	public static void main(String args[]) {
		PApplet.main("Tree.main");
	}
	
	public void settings() {
		size(800, 800, P3D);
	}
	
	public void setup() {
		seeds = new ArrayList<Seed>();
		Seed s = new Seed(this, width/2, 100);
		seeds.add(s);
	}
	
	public void draw() {
		background(0);
		frame.setTitle("FPS: " + frameRate);
		for(int i = 0; i < seeds.size(); i++) {
			seeds.get(i).draw();
		}
	}
}
