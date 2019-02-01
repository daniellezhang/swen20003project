/**written by Danielle Zhang. 28/9/18**/
import java.util.ArrayList;
import org.newdawn.slick.*;
import utilities.BoundingBox;
import java.util.Random;
public class ExtraLife extends Sprite{
	
	/** the minimum time that the ExtraLife need to wait before being on screen*/
	public static final int MIN_WAIT_TIME = 25*World.SECOND;
	/** the maximum time that the ExtraLife need to wait before being on screen*/
	public static final int MAX_WAIT_TIME = 35*World.SECOND;
	/** the time that the ExtraLife can be on screen*/
	public static final int ON_SCREEN_TIME = 14*World.SECOND;
	/** the time that the ExtraLife need to make a move*/
	public static final int MOVE_TIME = 2*World.SECOND;
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/extralife.png";
	
	private float randomWaitTime;
	private float time = 0;
	private boolean isOnScreen = false;
	private int randomLog;
	private float logX;
	private boolean moveRight;

	
	public ExtraLife() throws SlickException {
		
		//set the ExtraLife to an arbitrary location since it isn't on screen yet
		super(IMG_SRC, 0, 0);
		
		Random random = new Random();
		
		//choose a random wait time is between 25 and 35 secs
		randomWaitTime = random.nextInt(MAX_WAIT_TIME - MIN_WAIT_TIME + 1)+ MIN_WAIT_TIME;


	}
	
	/** set the value of isOnScreen
	 * @param isOnScreen the new value of isOnScreen
	 */
	public void setIsOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}
	/** get whether the ExtraLife is on screen or not
	 * 
	 * @return <code>true</code> if the ExtraLife is on screen, <code>false</code> otherwise.
	 */
	public boolean getIsOnScreen() {
		return isOnScreen;
	}
	
	/** get the time that the ExtraLife has been on screen
	 * 
	 * @return the time that the Extralife has been on screen
	 */
	public float getOnScreenTime() {
		return time - randomWaitTime;
	}
	
	/** update the position and status of the ExtraLife
	 * 
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
	 * @param sprites an ArrayList of the sprites of the game
	 * @throws SlickException
	 */
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {

		time += delta;		
		BoundingBox newLocRight, newLocLeft;
		float moveRightX, moveLeftX;
		Random random = new Random();
		
		//check if the wait time has passed and the object hasn't appeared on screen yet
		if(time>=randomWaitTime && (!isOnScreen)) {
			isOnScreen = true;
			//choose a random index from arraylist Sprites until the object store at the random index is log/longLog
			
			do {
				randomLog = random.nextInt(sprites.size());
			}
			while(!((sprites.get(randomLog) instanceof Log)||(sprites.get(randomLog) instanceof LongLog)));
			
			//set the position of the extra life to the position of the log/longlog chosen
			super.setX((sprites.get(randomLog).getX()));
			super.setY((sprites.get(randomLog).getY()));
			
			//set the x position that is relative to the log/longlog's centre
			logX = 0;
			
		}
		
		//move the extra life
		if(isOnScreen) {
			//check if 2 seonds has passed since the last time the extra life moved
			if((int)time/MOVE_TIME > (int)(time-delta)/MOVE_TIME) {
				//calculate the x positions for moving left or right
				moveRightX = super.getX() + World.TILE_SIZE;
				moveLeftX = super.getX()-World.TILE_SIZE;
				newLocRight = super.getBoundingBox();
				newLocLeft = super.getBoundingBox();
				newLocRight.setX(moveRightX);
				newLocLeft.setX(moveLeftX);
				
				//check if the extra life is able to move to the right
				if((moveRight && sprites.get(randomLog).contactSprite(newLocRight))||
						((!moveRight)&&(!sprites.get(randomLog).contactSprite(newLocLeft)))) {
					super.setX(moveRightX);
					moveRight = true;
					logX += World.TILE_SIZE;
				}
				//move to the left
				else {
					moveRight = false;
					super.setX(moveLeftX);
					logX -=World.TILE_SIZE;
				}
			}
			
			//set the x position of the extra life using its relative position on the log/longlog
			super.setX(logX+sprites.get(randomLog).getX());
			super.boundingBoxUpdate();

		}
		
		
	}
	/*reset the extraLife to its initial state*/
	public void reset() {
		Random random = new Random();
		
		//choose a random wait time is between 25 and 35 secs
		randomWaitTime = random.nextInt(MAX_WAIT_TIME - MIN_WAIT_TIME + 1)+ MIN_WAIT_TIME;
		isOnScreen = false;
		//reset time count
		time = 0;
	}
	
	@Override
	public void render() {
		//render the extra life if the extra life is on screen
		if(isOnScreen) {
			super.render();
		}
	}
}
