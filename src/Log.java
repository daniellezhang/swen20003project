/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.*;
public class Log extends WaterObject{
	
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/log.png";
	
	public Log(float x, float y, boolean moveRight) throws SlickException{
		super(IMG_SRC, x, y, moveRight, 0.1);
	}
	
	
	
}
