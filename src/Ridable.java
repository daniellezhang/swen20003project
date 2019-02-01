/** Written by Danielle Zhang on 7/10/18**/
public interface Ridable {
	
	/** calculate the new x position of the object that is riding an Ridable. Default assume it is implemented on Obstacle.
	 * @param x the current x position of the object that is riding an Ridable
	 * @param delta the milliseconds that has passed since the last update
	 * @return the new x position of the object.
	 */
	public default float ride(float x, int delta) {

		return x;
	}
}
