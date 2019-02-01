/**written by Danielle Zhang. 23/9/18**/
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;
public class Grass extends Sprite{

	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/grass.png";
	/*initialise the image and the position of the water*/
	public Grass(float x, float y) throws SlickException {
		super(IMG_SRC, x, y);
	}
}
