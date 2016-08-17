package Snake;

import java.util.ArrayList;
import processing.core.*;

public class SnakeBody {
	public static int DEFAULT_LENGTH = 5;
	private int length = DEFAULT_LENGTH;
	private ArrayList<PVector> history;
	
	public SnakeBody() {
		history = new ArrayList<PVector>();
	}
	
	public void push(PVector item) {
		history.add(item);
		while(history.size() > length) {
			history.remove(0);
		}
	}
	
	public ArrayList<PVector> history() {
		return history;
	}
	
	public void lengthen() {
		length++;
	}
	
	public void clear() {
		history.clear();
		length = DEFAULT_LENGTH;
	}
	
	public void length(int length) {
		this.length = length;
	}
}
