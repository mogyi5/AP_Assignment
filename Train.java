
public class Train extends Thread {

	// Variables.
	private int speed;
	private int number;  // ID number.
	private static int nextNumber = 0; // Incrementing number.
	
	protected static RailwayComponent[] route;
	private RailwayComponent current; // Current station.
	protected boolean movedOn = false; // Train recently changed segment?

	// Constructor.
	public Train(int speed) {
		this.speed = speed;
		this.number = nextNumber;
		Train.nextNumber++;
	}

	// Getters.
	public int getSpeed() {
		return this.speed;
	}

	public int getNumber() {
		return this.number;
	}

	public RailwayComponent getCurrent() {
		return this.current;
	}

	// Setters.
	public void setCurrent(RailwayComponent current) {
		this.current = current;
	}

	// Run method.
	public void run() {
		try {
			int j = 0;
			boolean running = true;
			while (running) {
				if (movedOn) {
					j++;   // Counter.
					
					Thread.sleep((int) (1000 * countdown(current)));
					movedOn = false;

					// If passed 5 segments, leave last segment and die.
					if (j == 5) {
						current.trainLeft(this);
						running = false;
					}

					// Transfer train from one segment to the other.
					for (int t = 0; t < route.length - 1; t++) {
						if (route[t].equals(current)) {
							route[t + 1].addTrain(this);
							route[t].trainLeft(this);
							break;
						}
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Train can't sleep.");
		}

	}

	// Methods.
	public String toString() {
		return number + ",";
	}

	// Calculating transit time in segment.
	public double countdown(RailwayComponent rc) {
		// t = l/s.
		double time = (double) rc.getLength() / (double) this.getSpeed();
		if (rc instanceof Station) {
			time += 5.0;  // If station, add 5s.
		}
		return time;
	}

}
