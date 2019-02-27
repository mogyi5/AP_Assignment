import java.util.*;

public class Creator extends Thread {

	// Variables.
	private Random r = new Random();
	private RailwayComponent firstStation;

	// Constructor.
	public Creator(RailwayComponent firstStation) {
		this.firstStation = firstStation;
	}

	// Run method.
	public void run() {
		while (true) {
			try {
				/*
				 * Sleep for an interval, create a random train
				 * and place it on the first station segment.
				 */
				Thread.sleep(intervalGenerator());
				trainPlacer(firstStation, trainGenerator());
				
			} catch (InterruptedException e) {
				System.out.println("Insomnia");
			}
		}
	}

	// Generate a random time between 0 and 10s.
	private int intervalGenerator() {
		int interval = r.nextInt(10000);
		return interval;
	}

	// Create each train with probability 0.5.
	private Train trainGenerator() {
		int outcome = r.nextInt(100);
		Train t;
			if (outcome < 50) {
				t = new Train(10);
			} else {
				t = new Train(500);
			}
			return t;
	}

	// Place a train by the first station.
	private void trainPlacer(RailwayComponent rc, Train t) {
		rc.addTrain(t);
		t.start();
	}

}
