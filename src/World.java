/**written by Danielle Zhang. 24/8/18**/
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

	/**dimension of tile, in pixels*/
	public static final float TILE_SIZE = 48;
    /**frog's starting x position*/
    public static final int FROG_X = 512;
    /**frog's starting y position*/
    public static final int FROG_Y = 720;
	/**maximum level*/
	private static final int MAX_LEVEL = 2;
	/**the first live frog's x position*/
	public static final int LIVES_X = 24;
	/**the first live frog's y position*/
	public static final int LIVES_Y = 744;
	/**dimension of the lives, in pixels*/
	public static final int LIVES_SIZE = 32;
	/**total number of holes*/
	public static final int N_HOLES = 5;
	/**y position of the holes*/
	public static final int HOLE_Y = 48;
	/**width of holes*/
	public static final float HOLE_WIDTH = 2*TILE_SIZE;
	/**distance of the two trees at each side of the hole*/
	public static final float HOLE_DIST = TILE_SIZE*3;
	/**second, in terms of millisecond*/
	public static final int SECOND = 1000;


	private int level = 0;
	private int holesFilled;
	
	private Player player;
	private ExtraLife extraLife;

	private ArrayList<Life> lives;		
	private ArrayList<Sprite> sprites;

	
	
	public World() throws SlickException {
		
		//initialise the player
		player = new Player(FROG_X,FROG_Y);
		// Perform initialisation logic
		levelUpdate();


	}
	
	
	public ArrayList<Sprite> getSprites(){
		return(ArrayList<Sprite>) sprites;
	}
	
	/** get the number of holes filled
	 * @return the number of holes filled
	 */
	public int getHolesFilled() {
		return holesFilled;
	}
	
	/** set the number of holes that have filled
	 * @param holesFilled the new number of holes filled
	 */
	public void setHolesFilled(int holesFilled) {
		this.holesFilled = holesFilled;
	}
	
	
	
	public void update(Input input, int delta) throws SlickException {
		int i;
		
		//update sprites
		for(Sprite sprite: sprites) {
			sprite.update(input, delta);

			//check if the Player intersects with a Collidable object
			if(sprite instanceof Collidable) {
				((Collidable) sprite).collide(player);
			}

		}
		
		//check if all holes have been filled
		if(holesFilled == N_HOLES) {
			//holes have been filled, next level
			level++;
			levelUpdate();
		}
		
		//update the player
		player.update(input, delta, this);
		//update the extra life
		extraLife.update(input, delta, this.sprites);

		
		//check if the extra life and the player are in contact
		if(extraLife.getIsOnScreen()) {
			if(player.contactSprite(extraLife.getBoundingBox())) {
				//increase player's lives by 1
				player.setLives(player.getLives()+1);
				//reset extraLife
				extraLife.reset();
			}
		}
		
		//check if the extra life has reached the maximum on screen time
		if(extraLife.getOnScreenTime() >= ExtraLife.ON_SCREEN_TIME)
		{
			//reset extralife
			extraLife.reset();
		}
		
		/*reach the maximum level of the game, exit*/
		if(level == MAX_LEVEL) {
			System.exit(0);
		}
		
		//update the number of lives of the player that displays on the screen
		lives = new ArrayList<Life>();
		for(i = 0; i < player.getLives();i++) {
			lives.add(new Life(LIVES_X + i*LIVES_SIZE, LIVES_Y));
		}
	}
	
	public void render(Graphics g) throws SlickException {
		
		//Draw the sprites
		for(Sprite sprite:sprites) {
			sprite.render();
		}
		

		//Draw the frog
		player.render();
		
		//Draw the extra life
		extraLife.render();
		
		//Draw the number of lives of the frog
		for(Life life: lives) {
			life.render();
		}
		
		
	}
	
	/**reinitialise all the Sprites for the next level
	 */
	public void levelUpdate() {
		String file;
		float x, y;
		int i;
		
		/*based on the level, determined which file is going to be read*/
		file = new String("assets/levels/"+level+".lvl");
		//level exceeds the maximum level. Cannot update level.
		if(level >= MAX_LEVEL) {
			return;
		}
		
		/*read the level file to initialise the sprites*/
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
		String text;
		
		
		float lastTreeX = 0;
		
		
		//reset the number of holes filled
		holesFilled = 0;
		
		/*initialise all the sprites except Player*/
		extraLife = new ExtraLife();
		sprites = new ArrayList<Sprite>();
		lives = new ArrayList<Life>();
		for(i = 0; i < player.getLives();i++) {
			lives.add(new Life(LIVES_X + i*LIVES_SIZE, LIVES_Y));
		};
		
		//create corresponding sprite
		while((text = br.readLine())!= null) {
			x = Float.parseFloat((text.split(","))[1]);
			y = Float.parseFloat((text.split(","))[2]);

			if((text.split(","))[0].equals("water")) {
				sprites.add(new Water(x, y));
			}
			
			if((text.split(","))[0].equals("grass")) {
				sprites.add(new Grass(x, y));
			}
			if((text.split(","))[0].equals("tree")) {
				sprites.add(new Tree(x, y));
				
				//check if there is a hole inbetween this tree and the last tree
				if((x-lastTreeX)== HOLE_DIST) {
					sprites.add(new Hole(lastTreeX + HOLE_DIST/2, y));
				}
				lastTreeX = x;
			}
			if((text.split(","))[0].equals("bus")) {
				sprites.add(new Bus(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("racecar")) {
				sprites.add(new Racecar(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("bike")) {
				sprites.add(new Bike(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("bulldozer")) {
				sprites.add(new Bulldozer(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("log")) {
				sprites.add(new Log(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("longLog")) {
				sprites.add(new LongLog(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
			if((text.split(","))[0].equals("turtle")) {
				sprites.add(new Turtle(x, y, Boolean.parseBoolean((text.split(","))[3])));
			}
		}
		
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
}