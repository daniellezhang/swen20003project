/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.*;

import utilities.BoundingBox;


public class Bulldozer extends Obstacle implements Ridable {
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/bulldozer.png";

	
	public Bulldozer(float x, float y, boolean moveRight) throws SlickException {
		super(IMG_SRC, x, y, moveRight, 0.05);
		super.setIsSolid(true);
	}
	
	public boolean solidContactCheck(BoundingBox boundingBox) {
		return super.getBoundingBox().intersects(boundingBox);
	}
	
    /**check if the given position is in the way of the direction that the bulldozer is moving to
     * @param x the x coordinate of the object to be checked
     * @ param y the y coordinate of the object to be checked
     * @return <code>true</code> if the given x coordinate is within the half tile size distance with the Bulldozer and the
     * given y coordinate equals to the Bulldozer's y coordinate.
     * <code>false</code> otherwise*/
	public boolean isPlayerInTheWay(float x, float y) {
		//check if the y coordinates are equal
		if(y == super.getY()) {
			//check if the x coordinate is within the half tile size distance with the Bulldozer
			if(super.getMoveRight()) {
				if(x >super.getX() && x - super.getX() > World.TILE_SIZE/2) {
					return true;
				}
			}
			else {
				if(x< super.getX() && super.getX()-x > World.TILE_SIZE/2) {
					return true;
				}
			}
		}

		return false;
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