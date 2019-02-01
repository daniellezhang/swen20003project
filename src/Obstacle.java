/*written by Danielle Zhang on 26/9/18*/

import org.newdawn.slick.*;

public class Obstacle extends Sprite{
	
	private boolean moveRight;
	private double speed; 
	private float width;
	
	public Obstacle(String imageSrc, float x, float y, boolean moveRight, double speed) throws SlickException {
		super(imageSrc, x, y);
		this.moveRight = moveRight;
		this.speed = speed;
		this.width = new Image(imageSrc).getWidth();
	}
	
	/** get whether the Obstacle is moving right
	 * @return <code>true</code> if the Obstacle is moving right, <code>false</code> otherwise.
	 */
	public boolean getMoveRight() {
		return moveRight;
	}
	
	
	/** set the Obstacle to move right or left
	 * 
	 * @param the new boolean value for moveRight. 
	 */
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	/** get the speed of the Obstacle
	 * 
	 * @return the speed of the Obstacle
	 */
	public double getSpeed() {
		return speed;
	}
	
	@Override
	public void update(Input input, int delta) {
		
		//obstacle is moving from left to right
		if(moveRight) {
			if(super.getX() < App.SCREEN_WIDTH + width/2) {

				super.setX((float)(super.getX()+ delta*speed));
			}
			//obstacle has reached the right boundary. reappear on the left.
			else {
				super.setX(0 - width/2);
			}
			
		}
		//obstacle is moving from right to left
		else {
			if(super.getX() > 0 - width/2) {
				super.setX((float)(super.getX() - delta*speed));
			}
			//obstacle has reached the left boundary. reappear on the right
			else {
				super.setX(App.SCREEN_WIDTH + width/2);
			}
		}
		
		//update the boundingBox with new coordinates
		super.boundingBoxUpdate();
	}

}
