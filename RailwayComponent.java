import java.util.*;
import java.util.concurrent.locks.*;

abstract class RailwayComponent {

	// Variables.
	private int length;
	protected int capacity;
	protected int trainNumber;
	protected ArrayList<Train> trainsHere = new ArrayList<>();

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	// Getters.
	public int getLength() {
		return this.length;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	// Setters.
	protected void setLength(int length) {
		this.length = length;
	}

	protected void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Methods.
	public void addTrain(Train train) {
		lock.lock();
		try {
			while (capacity == trainNumber) { // If full, wait.
				condition.await();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		trainNumber++;
		trainsHere.add(train); // Add train to this segment.
		train.setCurrent(this);
		train.movedOn = true; // Signal that the train is in new segment.
	}

	// Remove a train.
	public void trainLeft(Train train) {
		lock.lock();
		try {
			trainNumber--;
			trainsHere.remove(train);
			condition.signalAll();  // Tell all waiting trains.
		} finally {
			lock.unlock();
		}
	}

}
