package pro2_flappy.game;

/**
 * 
 * @author plasido1
 *
 */
public interface TickAware {
	
	/**
	 * èas od zaèátku spuštìní
	 * @param ticksSinceStart
	 */
	void tick(long ticksSinceStart);

}
