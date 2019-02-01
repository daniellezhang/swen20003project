/**written by Danielle Zhang. 9/10/18**/


public interface Collidable {
	
	/**check if the given player will collide with this object and lose a life
	 * 
	 * @param player the player to be checked whether this object will collide to
	 */
	public default void collide(Player player) {
		
	}
}
