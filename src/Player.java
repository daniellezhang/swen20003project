/**written by Danielle Zhang. 24/8/18**/
import java.util.ArrayList;
import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Player extends Sprite {
	
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/frog.png";
	
	private int lives = 3;
	private boolean isRiding = false;

	public Player(float x, float y) throws SlickException {
		super(IMG_SRC, x, y);
	}
	

	/** update the position and status of the player
	 * 
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
	 * @param world the World object that contains this Player
	 * @throws SlickException
	 */
	public void update(Input input, int delta, World world) throws SlickException {
		float newX = super.getX(), newY = super.getY();
		boolean isPushed = false;
		BoundingBox newLoc;

		ArrayList<Sprite> sprites = world.getSprites();
		
		
		//reset the isRiding value
		isRiding = false;
		
		//new x and y coordinate from keyboard input
		
		if(input.isKeyDown(Input.KEY_LEFT) & input.isKeyPressed(Input.KEY_LEFT)) {
			//The player has not reach the left boundary yet. Decrease its x coordinate by tile size
			if(super.getX() - World.TILE_SIZE >= 0) {
			newX = (super.getX() - World.TILE_SIZE);
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)& input.isKeyPressed(Input.KEY_RIGHT)) {
			//The player has not reach the right boundary yet. Increase its x coordinate by tile size
			if(super.getX() < App.SCREEN_WIDTH - World.TILE_SIZE) {
			newX = (super.getX() + World.TILE_SIZE);
			}
		}
		if(input.isKeyDown(Input.KEY_UP)& input.isKeyPressed(Input.KEY_UP)) {
			//The player has not reach the top boundary yet. Decrease its y coordinate by tile size
			if(super.getY() > 0) {
			newY = (super.getY() - World.TILE_SIZE);
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)& input.isKeyPressed(Input.KEY_DOWN)) {
			//The player has not reach the bottom boundary yet. increase its y coordinate by tile size
			if(super.getY() < App.SCREEN_HEIGHT) {
				newY = (super.getY() + World.TILE_SIZE);
			}
		}
		
		//create a BoundingBox for the new coordinates
		newLoc = super.getBoundingBox();
		newLoc.setX(newX);
		newLoc.setY(newY);
		
		for(Sprite sprite:sprites) {
			//check if the new coordinates are at the holes
			if(sprite instanceof Hole) {
				if(super.contactSprite(((Hole) sprite).getBoundingBox())) {
					//the hole has been filled, player loses a live
					if(((Hole) sprite).getIsFilled()) {
					loseLives();
					}
					//hole hasn't been filed. Player returns to initial position.
					else {
						newX = World.FROG_X;
						newY = World.FROG_Y;
						//update the hole status and the number of holes filled
						((Hole) sprite).setIsFilled(true);
						world.setHolesFilled(world.getHolesFilled()+1);
					}

				}
				
			}
			
			//check if the new coordinates are in contact with any solid object
			if(sprite.getIsSolid()) {
				if(sprite.contactSprite(newLoc)) {
					//if player is right on the side of the bulldozer that the bulldozer is moving to, player get pushed 
					//by the bulldozer
					if(sprite instanceof Bulldozer) {
						if(((Bulldozer) sprite).isPlayerInTheWay(newX, newY)) {
							isPushed = true;
							newX = ((Bulldozer)sprite).ride(super.getX(),delta);
						}
						//player's new position is on top of the bulldozer. stay at the old position
						else {
							newX = super.getX();
							newY = super.getY();
							newLoc = super.getBoundingBox();
						}
					}

					//player cannot move to new position. Stay at the old position.
					else {
						newX = super.getX();
						newY = super.getY();
						newLoc = super.getBoundingBox();
					}
				}
			}
			
			//check if the player will be in contact with any WaterObject
			if(sprite instanceof WaterObject) {

				if(sprite.contactSprite(newLoc)) {
					//the WaterObject is on screen. Player can ride this WaterObject
					if(((WaterObject)sprite).getIsOnScreen()) {
						isRiding = true;
						//the player ride with the WaterObject if no key is pressed
						if(newX == super.getX() && newY == super.getY()) {
							newX = ((WaterObject)sprite).ride(super.getX(),delta);
						}
					}
				}
			}
			
		}
		
		//check if the Player is going to be off the screen
		if(newX <= 0 || newX >= App.SCREEN_WIDTH){
			if(isPushed) {
				//Player is going to be pushed off the screen by bulldozer, Player loses a life
				loseLives();
			}
		}
		//player isn't pushed off the screen and the new position is within the screen boundary, update the coordinate
		else {
			super.setX(newX);
			super.setY(newY);
			super.boundingBoxUpdate();
		}
	}
	
	
	/**
	 * get the number of lives of this player
	 * @return the number of lives of this player
	 */
	public int getLives() {
		return lives;
	}
	
	/** set the number of lives of this Player
	 * 
	 * @param lives the new number of lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**reset the player's position and update the number of lives after losing a life
	 */
	public void loseLives() {
		lives --;
		//no lives left. Terminate the game.
		if(lives==0) {
			System.exit(0);
		}
		//still have lives left. reset the position
		super.setX(World.FROG_X);
		super.setY(World.FROG_Y);
		boundingBoxUpdate();
	}
	
	/**get whether the Player is riding a WaterObject
	 * 
	 * @return <code>true</code> if the Player is riding a WaterObject, <code>false</code> otherwise.
	 */
	public boolean getIsRiding() {
		return isRiding;
	}
	
}