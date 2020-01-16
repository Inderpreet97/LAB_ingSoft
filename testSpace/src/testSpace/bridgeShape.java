package testSpace;

public abstract class bridgeShape {
	protected bridgeColor color;
	
	protected bridgeShape(bridgeColor color) {
		this.color = color;
	}
	
	public abstract void draw();
}
