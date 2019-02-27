public class Station extends RailwayComponent {

	// Variables.
	private String name;

	// Constructor.
	public Station(int capacity, String name) {
		this.capacity = capacity;
		this.name = name;
		this.setLength(100);
	}

	// Getters.
	public String getName() {
		return this.name;
	}

	// Methods.
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("|--" + name + "-");
		for (int i = 0; i < trainsHere.size(); i++) {
			bld.append(trainsHere.get(i));
		}
		bld.append("--|");
		return bld.toString();
	}
}
