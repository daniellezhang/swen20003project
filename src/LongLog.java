/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.*;
public class LongLog extends WaterObject {
	
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/longlog.png";
    public LongLog(float x, float y, boolean moveRight) throws SlickException{
		super(IMG_SRC, x, y, moveRight, 0.07);
	}
	
}
