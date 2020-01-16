package testSpace;

public class bridgeMain {

	public static void main(String[] args) {
		bridgeShape redTriangle = new bridgeTriangle(new bridgeRed());
		bridgeShape blueTriangle = new bridgeTriangle(new bridgeBlue());
		
		redTriangle.draw();
		blueTriangle.draw();
	}

}
