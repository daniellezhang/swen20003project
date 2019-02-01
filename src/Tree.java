/**written by Danielle Zhang. 23/9/18**/
import org.newdawn.slick.*;

public class Tree extends Sprite{

	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/tree.png";

	public Tree(float x, float y) throws SlickException {
		super(IMG_SRC, x, y);
		super.setIsSolid(true);
	}
	

}
