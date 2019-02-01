/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Bike extends Obstacle implements Collidable{
	/** The x position where the Bike change its direction */
	public static final int[] REVERSE_X_LOC = new int[] {24, 1000};
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/bike.png";
	
	public Bike(float x, float y, boolean moveRight) throws SlickException {
		super(IMG_SRC, x, y, moveRight, 0.2);
	}
	
	@Override
	public void update(Input input, int delta) {
		int i;
		float newX;
		
		//the potential new x position
		if(super.getMoveRight()) {
			newX = (float) (super.getX() + delta*super.getSpeed());
		}
		else {
			newX = (float) (super.getX()-delta*super.getSpeed());
		}
		
		//check if the new x position has reach the x location to reverse the moving
		for(i = 0; i <REVERSE_X_LOC.length;i ++) {
			if(super.getMoveRight()) {
				if(super.getX()<REVERSE_X_LOC[i] && newX>= REVERSE_X_LOC[i]) {
					//bike has reached the x location for reverse from the left, reverse the location
					super.setMoveRight(!super.getMoveRight());
				}
			}
			else {
				if(super.getX()>REVERSE_X_LOC[i] && newX<= REVERSE_X_LOC[i]) {
					//bike has reached the x location for reverse from the right, reverse the location
					super.setMoveRight(!super.getMoveRight());
				}
			}
		}
		
		//update the position
		super.update(input, delta);
		
	}
	
	@Override
	public void collide(Player player) {
		//check whether the player intersects with the Bike
		if(super.contactSprite(player.getBoundingBox())) {
			//player does intersect with the Bike. Player lose a life.
			player.loseLives();
		}
	}
	
}