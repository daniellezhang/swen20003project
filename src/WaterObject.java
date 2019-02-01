/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.*;

public class WaterObject extends Obstacle implements Ridable {
	private boolean isOnScreen = true;
	
	public WaterObject(String imageSrc, float x, float y, boolean moveRight, double speed) throws SlickException{
		super(imageSrc, x, y, moveRight, speed);
	}
	/** get whether this object is on screen
	 * 
	 * @return  <code>true</code> if this object is on screen, <code>false</code> otherwise
	 */
	public boolean getIsOnScreen() {
		return isOnScreen;
	}
	
	/**
	 * set the value of isOnScree
	 * @param isOnScreen the new isOnScreen value
	 */
	public void setIsOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}
	
	@Override
	/** render the WaterObject if it is on screen*/
	public void render() {
		if(isOnScreen) {
			super.render();
		}
	}
	
	@Override
	public float ride(float x,int delta) {
		float newX;
		if(super.getMoveRight()){
			newX = (float) (x+ delta*super.getSpeed());
		}
		else {
			newX = (float) (x- delta*super.getSpeed());
		}
		return newX;
	}
	
	
}
