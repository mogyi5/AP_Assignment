
public class Track extends RailwayComponent {

	// Capacity
	public Track() {
		this.setCapacity(1);
		this.setLength(1000);
	}

	// Methods.
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("|---");
		for (int i = 0; i < trainsHere.size(); i++) {
			bld.append(trainsHere.get(i));
		}
		bld.append("---|");
		return bld.toString();
	}

}
