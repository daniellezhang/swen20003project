/**written by Danielle Zhang. 24/8/18**/
import org.newdawn.slick.*;

public class Bus extends Obstacle implements Collidable {
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/bus.png";
	

	public Bus(float x, float y, boolean moveRight) throws SlickException {
		super(IMG_SRC, x, y, moveRight, 0.15);
	}
	
	@Override
	public void collide(Player player) {
		//check whether the player intersects with the Bus
		if(super.contactSprite(player.getBoundingBox())) {
			//player does intersect with the Bus. Player lose a life.
			player.loseLives();
		}
	}
	
	
}