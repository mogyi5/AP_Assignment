
public class Runme {

	public static void main(String[] args) {

		// Route components.
		RailwayComponent[] route = new RailwayComponent[5];
		route[0] = new Station(2, "Partick");
		route[1] = new Track();
		route[2] = new Station(2, "Charing Cross");
		route[3] = new Track();
		route[4] = new Station(5, "Queen Street");
		Train.route = route;

		// Start creating trains.
		Creator trainMaker = new Creator(route[0]);
		trainMaker.start();

		// Printer thread.
		Printer printer = new Printer(route);
		printer.start();

		// Join everything up.
		try {
			trainMaker.join();
			printer.join();
		} catch (InterruptedException e) {
			System.out.println("Couldn't join!");
		}
	}
}
