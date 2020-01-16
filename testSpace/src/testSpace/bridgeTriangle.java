package testSpace;

public class bridgeTriangle extends bridgeShape {
	
	public bridgeTriangle(bridgeColor color) {
		super(color);
	}

	@Override
	public void draw() {
		System.out.println("Drawing a triangle");
		color.applyColor();	
	}
}
