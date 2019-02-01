/**written by Danielle Zhang. 24/8/18**/
import utilities.BoundingBox;
import org.newdawn.slick.*;

//a class for water tile
public class Water extends Sprite implements Collidable{

	/** path to the image of the Sprite */
	public static final String IMG_SRC = "assets/water.png";
	//initialise the image and the position of the water
	public Water(float x, float y)throws SlickException {
		super(IMG_SRC, x, y);
	}
	
	@Override
	public void collide(Player player) {
		if(super.contactSprite(player.getBoundingBox())){
			//player intersects with Water and isn't riding any WaterObject. Player loses a life
			if(!player.getIsRiding()) {
				player.loseLives();
			}
		}
	}
}