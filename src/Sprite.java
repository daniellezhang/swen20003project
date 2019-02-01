/**written by Danielle Zhang. 24/8/18**/
import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Sprite {
	

	/*dimension of tile, in pixels*/
	public static final int TILE_SIZE = 48;
	
	private Image img;
	private float x;
	private float y; 
	private BoundingBox boundingBox;
	private boolean isSolid = false;
	
	/**initialise the image and the position of the new sprite
	 * 
	 * @param imageSrc the address of the image file
	 * @param x the x position of the new sprite
	 * @param y the y positon of the sprite
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		img = new Image(imageSrc);
		this.x = x;
		this.y = y;
		boundingBox = new BoundingBox(img, x,y);
		
	}
	
	/** get the x position of the Sprite
	 * 
	 * @return the x position of the Sprite
	 */
	public float getX() {
		return x;
	}
	/** get the y position of the Sprite
	 * 
	 * @return the y position of the Sprite
	 */
	public float getY() {
		return y;
	}
	/** get the width of the Sprite
	 * 
	 * @return the width of the Sprite
	 */
	public float getWidth() {
		return img.getWidth();
	}
	/** get the boundingBox of the Sprite
	 * 
	 * @return the copy of this Sprite's boundingBox
	 */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(img,x,y);
	}
	/** set this object's x position
	 * 
	 * @param x the new x position of this object
	 */
	public void setX(float x) {
		this.x = x;
	}
	/** set this Sprite's y position
	 * 
	 * @param y the new y position of this Sprite
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**get whether the Sprite is solid or not
	 * 
	 * @return <code>true</code> if the Sprite is solid, otherwise <code>false</code>
	 */
	public boolean getIsSolid() {
		return isSolid;
	}
	/** set the value of isSolid
	 * 
	 * @param isSolid the new isSolid value
	 */
	public void setIsSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}
	
	/** update this object's boundingBox with its own location
	 * 
	 */
	public void boundingBoxUpdate(){
	boundingBox.setX(x);
	boundingBox.setY(y);
	}
	
	/** set the boundingBox of this object
	 * 
	 * @param boundingBox the new BoundingBox object that this Sprite has
	 */
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	/** update the position and status of the Sprite. To be overridden
	 * 
	 * @param input gc The Slick game container object.
	 * @param delta delta Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) {
		
	}
	

	public void render() {
		img.drawCentered(x, y);
	}
	
	/** check if other BoundingBox intersect with this Sprite
	 * 
	 * @param other the BoundingBox of the Sprite that need to be checked
	 * @return <code>true</code> if the other Sprite intersects with this Sprite. return <code>false</code> otherwise.
	 */
	public boolean contactSprite(BoundingBox other) {
		return this.boundingBox.intersects(other);
	}
	
	
}
