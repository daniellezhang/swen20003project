/**written by Danielle Zhang. 26/9/18**/
import org.newdawn.slick.SlickException;


public class Racecar extends Obstacle implements Collidable{
	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/racecar.png";
	
	public Racecar(float x, float y, boolean moveRight) throws SlickException {
		super(IMG_SRC, x, y, moveRight, 0.5);
	}
	
	@Override
	public void collide(Player player) {
		//check whether the player intersects with the Racecar
		if(super.contactSprite(player.getBoundingBox())) {
			//player does intersect with the Racecar. Player lose a life.
			player.loseLives();
		}
	}
}