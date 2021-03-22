import java.io.FileNotFoundException;
import java.util.List;

/**
 * Class that implements a MyGraph with a type Location, holds two graph
 * instances
 * 
 *
 */
public class ProgramMethods extends MyGraph<Location> {

	public MyGraph<Location> price;
	public MyGraph<Location> duration;
	public Data loader;

	/**
	 * Constructor that initializes the two MyGraph<Location> instances with data
	 * from CSV files by using the LoadFile class
	 * 
	 * @throws FileNotFoundException
	 */
	public ProgramMethods() throws FileNotFoundException {

		price = new MyGraph<Location>();
		duration = new MyGraph<Location>();
		loader = new Data();

		loader.flightPrice(price); // mechanism to load price data

		loader.flightTimes(duration);
		; // mechanism to load duration data

	}

	/**
	 * Method that prints both: best path by price and best path by duration along
	 * with respective path costs and a custom score
	 * 
	 * @param start
	 * @param end
	 */
	public void printBothPaths(Location start, Location end) {

		Path pricePath = price.dijkstrasShortestPath(start, end);
		Path durationPath = duration.dijkstrasShortestPath(start, end);

		System.out.println("Best path by price:\n");
		printPath(pricePath);
		System.out.println("\tPrice:\t\t$" + pricePath.distance);
		System.out.println("\tScore:\t\t" + getScore(pricePath) + "/10\n");
		System.out.println("Best path by duration:\n");
		printPath(durationPath);
		int mins = durationPath.distance;
		int hours = mins / 60;
		int remMins = mins % 60;
		if (hours < 1) {
			System.out.println("\tDuration:\t" + remMins + " mins");
		} else if (hours == 1) {
			if (remMins != 0) {
				System.out.println("\tDuration:\t" + hours + " hour " + remMins + " mins");
			} else {
				System.out.println("\tDuration:\t" + hours + " hour ");
			}
		} else {
			if (remMins != 0) {
				System.out.println("\tDuration:\t" + hours + " hours " + remMins + " mins");
			} else {
				System.out.println("\tDuration:\t" + hours + " hours ");
			}
		}
		System.out.println("\tScore:\t\t" + getScore(durationPath) + "/10");
	}

	/**
	 * Private helper method used to decide the score of a particular path
	 * 
	 * @param path
	 * @return
	 */
	private int getScore(Path path) {
		// decides score based on number of stop-overs
		return 11 - path.dataSequence.size();
	}

	/**
	 * Private helper method used to print the path passed to it
	 * 
	 * @param path
	 */
	private void printPath(Path path) {
		List<Location> list = path.dataSequence;
		Location loc = list.remove(list.size() - 1);
		for (Location l : list)
			System.out.print(l.getName() + " -> ");
		System.out.print(loc.getName() + "\n\n");
	}
}
