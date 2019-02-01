/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.*;
public class Turtle extends WaterObject {
	/**turtle's on screen time in milliseconds */
	private static final int ON_SCREEN_TIME = 7*World.SECOND;
	/**turtle's off screen time in milliseconds */
	private static final int OFF_SCREEN_TIME = 2*World.SECOND;
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/turtles.png";
	
	private float time = 0;

    public Turtle(float x, float y, boolean moveRight) throws SlickException{
		
		super(IMG_SRC, x, y, moveRight, 0.085);
	}
	
    @Override
    public void update(Input input, int delta) {
    	//update the location and time
    	super.update(input, delta);  
    	time += delta;
    	//check whether the turtle should be on screen or not
    	if(time >= ON_SCREEN_TIME) {
    		super.setIsOnScreen(false);

    	}
    	if(time >=OFF_SCREEN_TIME + ON_SCREEN_TIME) {
    		super.setIsOnScreen(true);
    		//turtle has done one round of on and off screen, reset the time
    		time = 0;
    	}
    }
}
