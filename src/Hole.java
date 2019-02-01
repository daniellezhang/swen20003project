/**written by Danielle Zhang. 27/9/18**/
import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Hole extends Sprite {
	
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/frog.png";
	private boolean isFilled = false;
	
	public Hole(float x, float y) throws SlickException {
		super(IMG_SRC, x, y);
		
		//set the boundingBox to the size of the hole
		super.setBoundingBox(new BoundingBox(x, y, World.HOLE_WIDTH, World.TILE_SIZE));
		
	}
	

	public void render() {
		//only render the sprite if the hole is filled
		if(isFilled) {
			super.render();
		}
	}
	
	/** get whether the Hole has been filled or not
	 * @return <code>true</code> if the Hole has been filled, <code>false</code> otherwise.
	 */
	public boolean getIsFilled() {
		return isFilled;
	}
	/** set the Hole's isFilled value
	 * 
	 * @param isFilled the new boolean value for the Hole's isFilled
	 */
	public void setIsFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
}
