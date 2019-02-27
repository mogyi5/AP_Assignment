public class Printer extends Thread {

	// Variables.
	private RailwayComponent[] route;

	// Constructor.
	public Printer(RailwayComponent[] route) {
		this.route = route;
	}

	// Methods. Print out an update of the route every 2s.
	public void run() {
		while (true) {
			Printing(this.route);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Prints out the route with trains at each station respectively.
	public static void Printing(RailwayComponent[] route) {
		for (int i = 0; i < route.length; i++) {
			System.out.print(route[i]);
		}
		System.out.println();
	}
}
